package de.andrena.optimizeformaintainability.ssa;

import java.util.List;

/**
 * A code snippet containing a loop performing the modification of one variable
 * 
 * * initialize a variable of the container type with the initial variable value before the loop
 * * replace the modified variable by the container type variable and unwrap the wrapped after the loop into the original variable
 * * extract the loop body into a new method (returning a value of the container type)
 * * transform the initialization and the loop to a stream that is reduced
 * * inline all constant assignments
*/
public class SimpleForEachLoops {

	String join(List<String> words) {
		StringContainer joined = words.stream()
			.reduce(new StringContainer(""), this::join, this::first);
		return joined.str;
	}

	private StringContainer join(StringContainer joined, String word) {
		return new StringContainer(joined.str + word);
	}

	private StringContainer first(StringContainer c1, StringContainer c2) {
		return c1;
	}

	private static class StringContainer {
		final String str;

		public StringContainer(String str) {
			this.str = str;
		}

	}
}
