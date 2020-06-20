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
		Rect r = rectFrom(type, arguments);

		return r.width * r.height;
	}

	private Rect rectFrom(String type, int... arguments) {
		if (type.equals("square")) {
			return new Rect(arguments[0],arguments[0]);
		} else if (type.equals("rectangle")) {
			return new Rect(arguments[0],arguments[1]);
		} else {
			return new Rect(0,0);
		}
	}

	static class Rect {
		final int width;
		final int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}

class ComplexConditionals5 {

	int area(String type, int... arguments) {
		Rect r = rectFrom(type, arguments);

		return r.width * r.height;
	}

	private Rect rectFrom(String type, int... arguments) {
		if (type.equals("square")) {
			Rect r = new Rect(0,0);
			r = new Rect(arguments[0],arguments[0]);
			return r;
		} else if (type.equals("rectangle")) {
			Rect r = new Rect(0,0);
			r = new Rect(arguments[0],arguments[1]);
			return r;
		} else {
			Rect r = new Rect(0,0);
			return r;
		}
	}

	static class Rect {
		final int width;
		final int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}

class ComplexConditionals4 {

	int area(String type, int... arguments) {
		Rect r = rectFrom(type, arguments);

		return r.width * r.height;
	}

	private Rect rectFrom(String type, int... arguments) {
		Rect r = new Rect(0,0);

		if (type.equals("square")) {
			r = new Rect(arguments[0],arguments[0]);
		} else if (type.equals("rectangle")) {
			r = new Rect(arguments[0],arguments[1]);
		}
		return r;
	}

	static class Rect {
		final int width;
		final int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}

class ComplexConditionals3 {

	int area(String type, int... arguments) {
		Rect r = new Rect(0,0);

		if (type.equals("square")) {
			r = new Rect(arguments[0],arguments[0]);
		} else if (type.equals("rectangle")) {
			r = new Rect(arguments[0],arguments[1]);
		}

		return r.width * r.height;
	}

	static class Rect {
		final int width;
		final int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}

class ComplexConditionals2 {

	int area(String type, int... arguments) {
		Rect r = new Rect(0,0);

		if (type.equals("square")) {
			r.width = arguments[0];
			r.height = arguments[0];
		} else if (type.equals("rectangle")) {
			r.width = arguments[0];
			r.height = arguments[1];
		}

		return r.width * r.height;
	}

	static class Rect {
		int width;
		int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}

class ComplexConditionals1 {

	int area(String type, int... arguments) {
		int width = 0;
		int height = 0;
		@SuppressWarnings("unused")
		Rect r = new Rect(0,0);

		if (type.equals("square")) {
			width = arguments[0];
			height = arguments[0];
		} else if (type.equals("rectangle")) {
			width = arguments[0];
			height = arguments[1];
		}

		return width * height;
	}

	static class Rect {
		int width;
		int height;

		public Rect(int width, int height) {
			this.width = width;
			this.height = height;
		}

	}
}
