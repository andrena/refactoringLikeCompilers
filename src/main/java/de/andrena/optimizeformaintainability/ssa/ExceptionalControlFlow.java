package de.andrena.optimizeformaintainability.ssa;

import java.util.List;

/**
 * A code snippet containing a loop or branch with control flow instruction (return, break, continue, throw).
 * * replace the modified variable by the container type variable and unwrap the wrapped after the loop into the original variable
 * * include a control flow hint in the container and pull the control flow instruction out of the loop body
 * * extract the loop body into a new method (returning a value of the container type)
 * * transform the initialization and the loop to a stream that is reduced/or a filtered infinite stream
 * * inline all constant assignments and remove duplicated branches
*/
public class ExceptionalControlFlow {

	int find(String needle, List<String> haystack) {
		IntContainer index = haystack.stream()
			.reduce(new IntContainer(0), (i, item) -> {
				if (i.control == Control.RETURN) {
					return i;
				}
				if (needle.equals(item)) {
					return new IntContainer(i.index, Control.RETURN);
				}
				return new IntContainer(i.index + 1);
			}, this::first);
		return index.index;
	}

	private IntContainer first(IntContainer c1, IntContainer c2) {
		return c1;
	}

	private static class IntContainer {
		final int index;
		final Control control;

		public IntContainer(int i) {
			this(i, Control.NONE);
		}

		public IntContainer(int i, Control control) {
			this.index = i;
			this.control = control;
		}

	}

	private enum Control {
		NONE, RETURN;
	}
}
