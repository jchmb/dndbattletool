package nl.jchmb.dndbattle.utils;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class IndexedObjectSpliterator<T> implements Spliterator<IndexedObject<T>> {
	private int index = 0;
	private final Spliterator<T> spliterator;
	
	public IndexedObjectSpliterator(final Stream<T> stream) {
		this.spliterator = stream.spliterator();
	}
	
	@Override
	public int characteristics() {
		return Spliterator.ORDERED;
	}

	@Override
	public long estimateSize() {
		return spliterator.estimateSize();
	}

	@Override
	public boolean tryAdvance(Consumer<? super IndexedObject<T>> consumer) {
		return spliterator.tryAdvance(
			o -> consumer.accept(new IndexedObject<T>(index++, o))
		);
	}

	@Override
	public Spliterator<IndexedObject<T>> trySplit() {
		return null;
	}
	
}
