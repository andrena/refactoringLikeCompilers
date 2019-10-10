package de.andrena.optimizeformaintainability.patterns;

/**
 * A code snippet containing a branch which modifies a multiple variable for further use.
 */
public class ComplexConditionals {

	int area(String type, int... arguments) {
		int width = 0;
		int height = 0;

		if (type.equals("square")) {
			width = arguments[0];
			height = arguments[0];
		} else if (type.equals("rectangle")) {
			width = arguments[0];
			height = arguments[1];
		}

		return width * height;
	}

}
