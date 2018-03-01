package nl.jchmb.dndbattle.utils;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IndexedObject<T> {
	private final int index;
	private final T object;
	
	public IndexedObject(final int index, final T object) {
		this.index = index;
		this.object = object;
	}
	
	public int getIndex() {
		return index;
	}
	
	public T getObject() {
		return object;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IndexedObject<?>)) {
			return false;
		}
		final IndexedObject<?> that = (IndexedObject<?>) o;
		return this.index == that.index &&
			this.object.equals(that.object);
	}
	
	@Override
	public int hashCode() {
		return 3 * index + 5 * object.hashCode();
	}
	
	public static <T> Stream<IndexedObject<T>> map(final Stream<T> stream) {
		return StreamSupport.stream(
			new IndexedObjectSpliterator<T>(stream),
			false
		);
	}
}
