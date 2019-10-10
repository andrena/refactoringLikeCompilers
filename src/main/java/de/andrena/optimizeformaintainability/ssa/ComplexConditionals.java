package de.andrena.optimizeformaintainability.ssa;

/**
 * A code snippet containing a branch which modifies a multiple variable for further use.
 * 
 * * create a compound type containing all modified variables
 * * initialize a variable of the compound type with the initial variable values before the conditional block
 * * replace all assignments to the modified variables with assignments to the compound type variable
 * * extract the conditional block and all definitions of the modified compound variable into a new method
 * * move returns into the branches
 * * inline all constant assignments
 */
public class ComplexConditionals {

	int area(String type, int... arguments) {
		Rect rect = rectangleFrom(type, arguments);
		return rect.width * rect.height;
	}

	private Rect rectangleFrom(String type, int[] arguments) {
		if (type.equals("square")) {
			return new Rect(arguments[0], arguments[0]);
		} else if (type.equals("rectangle")) {
			return new Rect(arguments[0], arguments[1]);
		}
		return new Rect(0, 0);
	}

	public static class Rect {
		final int width;
		final int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}
