package nl.jchmb.dndbattle.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.jchmb.dndbattle.utils.form.Form;

public class MultiFactory<T> {
	private final ListProperty<Entry<? extends T>> entries = new SimpleListProperty<>(
		FXCollections.observableArrayList()
	);

	public final <U extends T> void add(final Class<U> cls, final String label) {
		entries.add(new Entry<U>(cls, label));
	}
	
	public final ObservableList<T> produceObjects(final T currentObject) {
		final ObservableList<T> objects = FXCollections.observableArrayList();
		for (Entry<? extends T> entry : entries) {
			if (entry.getClassRepresentation().isInstance(currentObject)) {
				objects.add(currentObject);
			} else {
				objects.add(entry.produce());
			}
		}
		return objects;
	}
	
	public static class Entry<T> {
		private final Class<T> cls;
		private final String label;
		
		public Entry(final Class<T> cls, final String label) {
			this.cls = cls;
			this.label = label;
		}
		
		public final T produce() {
			try {
				return cls.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public final Class<T> getClassRepresentation() {
			return cls;
		}
		
		@Override
		public final String toString() {
			return label;
		}
	}

	public final ListProperty<Entry<? extends T>> entriesProperty() {
		return this.entries;
	}
	

	public final ObservableList<Entry<? extends T>> getEntries() {
		return this.entriesProperty().get();
	}
	

	public final void setEntries(final ObservableList<Entry<? extends T>> entries) {
		this.entriesProperty().set(entries);
	}
	
}