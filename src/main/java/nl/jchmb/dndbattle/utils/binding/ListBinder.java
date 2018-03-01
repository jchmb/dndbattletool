package nl.jchmb.dndbattle.utils.binding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import nl.jchmb.dndbattle.utils.IndexedObject;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class ListBinder<T, ExtendedT, U> {
	private ListBinding<U> binding;
	private final ListProperty<T> list;
	private final ListProperty<U> mirrorList;
	private final List<Function<T, Property<?>>> propertyGetters;
	private final Function<Stream<T>, Stream<ExtendedT>> streamMapper;
	private final Function<ExtendedT, U> mapper;
	private final Predicate<T> filter;
	private final Comparator<T> comparator;
	private final List<Property<?>> extraProperties;
	private final Set<T> knownItems;
	private final ListChangeListener<T> listener =
		(Change<? extends T> c) -> registerItems();
	
	public ListBinder(
			final ListProperty<T> list,
			final Function<Stream<T>, Stream<ExtendedT>> streamMapper,
			final Function<ExtendedT, U> mapper,
			final Predicate<T> filter,
			final Comparator<T> comparator
	) {
		this.list = list;
		this.mirrorList = new SimpleListProperty<>(FXCollections.observableArrayList());
		this.list.addListener(listener);
		this.filter = filter;
		this.mapper = mapper;
		this.comparator = comparator;
		this.propertyGetters = new ArrayList<>();
		this.extraProperties = new ArrayList<>();
		this.streamMapper = streamMapper;
		this.knownItems = new HashSet<>();
	}
	
	public final void bind() {
		this.binding = new ListBinding<>() {
			{
				super.bind(list);
				extraProperties.forEach(super::bind);
			}
			
			@Override
			protected ObservableList<U> computeValue() {
				return FXCollections.observableArrayList(
					streamMapper.apply(
						list.stream()
							.filter(filter)
							.sorted(comparator)
					)
					.map(mapper)
					.collect(Collectors.toList())
				);
			}
				
		};
		this.mirrorList.bind(binding);
	}
	
	public final ListBinder<T, ExtendedT, U> addExtraProperty(final Property<?> property) {
		extraProperties.add(property);
		return this;
	}
	
	public final ListBinder<T, ExtendedT, U> addPropertyGetter(final Function<T, Property<?>> propertyGetter) {
		propertyGetters.add(propertyGetter);
		return this;
	}
	
	public final void registerItems() {
		for (final T item : list) {
			if (knownItems.contains(item)) {
				continue;
			}
			registerItem(item);
			knownItems.add(item);
		}
	}
	
	private final void update() {
		this.mirrorList.unbind();
		this.mirrorList.clear();
		this.bind();
	}
	
	private final void registerItem(final T item) {
		for (final Function<T, Property<?>> propertyGetter : propertyGetters) {
			propertyGetter.apply(item)
				.addListener(
					(prop, ov, nv) -> {
						update();
					}
				);
		}
	}
	
	public final ListProperty<U> mirrorProperty() {
		return mirrorList;
	}
	
	public static final <T> ListBinder<T, IndexedObject<T>, Node> indexedNodeListBinder(
			final ListProperty<T> list,
			final ObservableList<Node> nodeList,
			final Function<IndexedObject<T>, Node> mapper,
			final Predicate<T> filter,
			final Comparator<T> comparator,
			final Property<?>... extraProperties
	) {
		final ListBinder<T, IndexedObject<T>, Node> binder = new ListBinder<>(
			list,
			IndexedObject::map,
			mapper,
			filter,
			comparator
		);
		for (final Property<?> extraProperty : extraProperties) {
			binder.addExtraProperty(extraProperty);
		}
		Bindings.bindContent(
			nodeList,
			binder.mirrorProperty()
		);
		return binder;
	}
}
