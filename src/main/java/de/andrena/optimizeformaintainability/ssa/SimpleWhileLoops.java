package de.andrena.optimizeformaintainability.ssa;

import java.util.stream.Stream;

/**
 * A code snippet containing a loop performing the modification of one variable
 * 
 * * initialize a variable of the container type with the initial variable value before the loop
 * * replace the modified variable by the container type variable and unwrap the wrapped after the loop into the original variable
 * * extract the loop body into a new method (returning a value of the container type)
 * * transform the initialization and the loop to an infinite stream that is filtered (such that only the last element is selected)
 * * inline all constant assignments
 */
public class SimpleWhileLoops {

	String padWithSpaces(int length, String text) {
		StringContainer padded = Stream.iterate(new StringContainer(text), this::pad)
			.filter(c -> c.str.length() == length)
			.limit(1)
			.findFirst()
			.orElseThrow(RuntimeException::new);
		return padded.str;
	}

	private StringContainer pad(StringContainer padded) {
		return new StringContainer(" " + padded.str);
	}

	private static class StringContainer {
		final String str;

		public StringContainer(String str) {
			this.str = str;
		}

	}
}
