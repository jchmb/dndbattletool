package nl.jchmb.dndbattle.utils;

import java.util.function.Function;
import java.util.stream.Stream;

public class Streams {
	public static <T> Stream<T> concat(Stream<T>[] streams) {
		return Stream.of(streams)
			.flatMap(Function.identity());
	}
}
