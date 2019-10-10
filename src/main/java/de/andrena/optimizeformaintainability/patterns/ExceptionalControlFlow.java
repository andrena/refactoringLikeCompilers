package de.andrena.optimizeformaintainability.patterns;

import java.util.List;

/**
 * A code snippet containing a loop or branch with control flow instruction (return, break, continue, throw).
 */
public class ExceptionalControlFlow {

	int find(String needle, List<String> haystack) {
		int index = 0;
		for (String item : haystack)  {
			if (needle.equals(item)) {
				return index;
			}
			index++;
		}
		return index;
	}

}
