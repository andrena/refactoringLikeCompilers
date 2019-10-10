package de.andrena.optimizeformaintainability.patterns;

import java.util.List;

/**
 * A code snippet containing a loop performing the modification of one variable
 */
public class SimpleForEachLoops {

	String join(List<String> words) {
		String joined = "";
		for (String word : words)  {
			joined += word;
		}
		return joined;
	}

}
