package de.andrena.optimizeformaintainability.ssa;

import java.util.function.UnaryOperator;
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
		String padded = Stream.iterate(text, this::padSpace)
			.filter(c -> c.length() >= length)
			.limit(1)
			.findFirst()
			.orElseThrow(RuntimeException::new);
		return padded;
	}
	
	String padSpace(String s) {
		return " " + s;
	}

}

class SimpleWhileLoopsJava11 {

	String padWithSpaces(int length, String text) {
		String padded = Stream.iterate(text, this::padSpace)
			.dropWhile(s -> s.length() < length)
			.findFirst()
			.orElseThrow(RuntimeException::new);
		return padded;
	}
	
	String padSpace(String s) {
		return " " + s;
	}

}

class SimpleWhileLoops2 {

	String padWithSpaces(int length, String text) {
		UnaryOperator<String> padSpace = s -> " " + s;
		
		String padded = Stream.iterate(text, padSpace)
			.filter(c -> c.length() >= length)
			.limit(1)
			.findFirst()
			.orElseThrow(RuntimeException::new);
		return padded;
	}

}

class SimpleWhileLoops1 {

	String padWithSpaces(int length, String text) {
		@SuppressWarnings("unused")
		UnaryOperator<String> padSpace = s -> " " + s;
		String padded = text;
		while (padded.length() < length) {
			padded = " " + padded;
		}
		return padded;
	}

}

