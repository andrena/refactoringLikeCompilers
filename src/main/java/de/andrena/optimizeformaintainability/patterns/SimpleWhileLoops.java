package de.andrena.optimizeformaintainability.patterns;

/**
 * A code snippet containing a loop performing the modification of one variable
 */
public class SimpleWhileLoops {

	String padWithSpaces(int length, String text) {
		String padded = text;
		while (padded.length() < length) {
			padded = " " + padded;
		}
		return padded;
	}

}
