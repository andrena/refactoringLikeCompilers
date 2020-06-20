package de.andrena.optimizeformaintainability.ssa;

import java.util.List;
import java.util.function.BinaryOperator;

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
		return words.stream()
			.reduce("", this::join);
	}

	String join(String s1, String s2) {
		return s1 + s2;
	}

}

class SimpleForEachLoops4 {

	String join(List<String> words) {
		BinaryOperator<String> join = (s1, s2) -> s1 + s2;
		String joined = words.stream().reduce("", join);
		return joined;
	}

}

class SimpleForEachLoops3 {

	String join(List<String> words) {
		BinaryOperator<String> join = (s1, s2) -> s1 + s2;
		String joined = "";
		for (String word : words) {
			joined = join.apply(joined, word);
		}
		return joined;
	}

}

class SimpleForEachLoops2 {

	String join(List<String> words) {
		String joined = "";
		@SuppressWarnings("unused")
		BinaryOperator<String> join = (s1, s2) -> s1 + s2;
		for (String word : words) {
			joined = joined + word;
		}
		return joined;
	}

}

class SimpleForEachLoops1 {

	String join(List<String> words) {
		String joined = "";
		for (String word : words) {
			joined = joined + word;
		}
		return joined;
	}

}
