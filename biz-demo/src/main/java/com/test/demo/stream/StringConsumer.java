package com.test.demo.stream;

import java.util.Objects;

/**
 * @see java.util.function.IntConsumer
 * Created on 2018/1/9.
 */
@FunctionalInterface
public interface StringConsumer {

	void accept(String value);

	default StringConsumer andThen(StringConsumer after) {
		Objects.requireNonNull(after);
		return (String t) -> { accept(t); after.accept(t); };
	}
}
