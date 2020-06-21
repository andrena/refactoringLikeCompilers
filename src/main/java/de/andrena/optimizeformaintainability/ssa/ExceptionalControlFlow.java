package de.andrena.optimizeformaintainability.ssa;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

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
		LoopItem<Integer> index = haystack.stream()
			.reduce(new LoopItem<>(0), find(needle), this::discard);
		return index.value();
	}

	private LoopItem<Integer> discard(LoopItem<Integer> i1, LoopItem<Integer> i2) {
		return null;
	}

	private BiFunction<LoopItem<Integer>, String, LoopItem<Integer>> find(String needle) {
		return (index, item) -> find(item, needle, index);
	}

	private LoopItem<Integer> find(String item, String needle, LoopItem<Integer> index) {
		if (needle.equals(item)) {
			return index.ret();
		} else {
			return index.map(val -> val + 1);
		}
	}

}

class ExceptionalControlFlow11 {

	int find(String needle, List<String> haystack) {
		BiFunction<LoopItem<Integer>, String, LoopItem<Integer>> body = find(needle);
		BinaryOperator<LoopItem<Integer>> discard = (i1, i2) -> null;
		LoopItem<Integer> index = haystack.stream().reduce(new LoopItem<>(0), body, discard);
		return index.value();
	}

	private BiFunction<LoopItem<Integer>, String, LoopItem<Integer>> find(String needle) {
		return (index, item) -> find(item, needle, index);
	}

	private LoopItem<Integer> find(String item, String needle, LoopItem<Integer> index) {
		if (needle.equals(item)) {
			return index.ret();
		} else {
			return index.map(val -> val + 1);
		}
	}

}

class ExceptionalControlFlow10 {

	int find(String needle, List<String> haystack) {
		@SuppressWarnings("unused")
		BiFunction<LoopItem<Integer>, String, LoopItem<Integer>> body = find(needle);
		@SuppressWarnings("unused")
		BinaryOperator<LoopItem<Integer>> discard = (i1, i2) -> null;
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			index = find(item, needle, index);
		}
		return index.value();
	}

	private BiFunction<LoopItem<Integer>, String, LoopItem<Integer>> find(String needle) {
		return (index, item) -> find(item, needle, index);
	}

	private LoopItem<Integer> find(String item, String needle, LoopItem<Integer> index) {
		if (needle.equals(item)) {
			return index.ret();
		} else {
			return index.map(val -> val + 1);
		}
	}

}

class ExceptionalControlFlow9 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			index = find(item, needle, index);
		}
		return index.value();
	}

	private LoopItem<Integer> find(String item, String needle, LoopItem<Integer> index) {
		if (needle.equals(item)) {
			return index.ret();
		} else {
			return index.map(val -> val + 1);
		}
	}

}

class ExceptionalControlFlow8 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			LoopItem<Integer> index2 = find(item, needle, index);
			index = index2;
		}
		return index.value();
	}

	private LoopItem<Integer> find(String item, String needle, LoopItem<Integer> index) {
		LoopItem<Integer> index2 = index;
		if (needle.equals(item)) {
			index2 = index.ret();
		} else {
			index2 = index.map(val -> val + 1);
		}
		return index2;
	}

}

class ExceptionalControlFlow7 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			LoopItem<Integer> index2 = index;
			if (needle.equals(item)) {
				index2 = index.ret();
			} else {
				index2 = index.map(val -> val + 1);
			}
			index = index2;
		}
		return index.value();
	}

}

class ExceptionalControlFlow6 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			LoopItem<Integer> index2 = index;
			if (needle.equals(item)) {
				index2 = index.ret();
			} else {
				index2 = index.map(val -> val + 1);
			}
			index = index2;
		}
		if (index.returns()) {
			return index.value();
		} else {
			return index.value();
		}
	}

}

class ExceptionalControlFlow5 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			LoopItem<Integer> index2 = index;
			if (needle.equals(item)) {
				index2 = index.ret();
			} else {
				index2 = index.map(val -> val + 1);
			}
			index = index2;
		}
		if (index.returns()) { //trivial here but needed in general when statements follow the loop 
			return index.value();
		}
		return index.value();
	}

}

class ExceptionalControlFlow4 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			if (needle.equals(item)) {
				return index.value();
			} else {
				index = index.map(val -> val + 1);
			}
		}
		return index.value();
	}

}

class ExceptionalControlFlow3 {

	int find(String needle, List<String> haystack) {
		LoopItem<Integer> index = new LoopItem<>(0);
		for (String item : haystack) {
			if (needle.equals(item)) {
				return index.value();
			} else {
				index = new LoopItem<>(index.value() + 1);
			}
		}
		return index.value();
	}

}

class ExceptionalControlFlow2 {

	int find(String needle, List<String> haystack) {
		int index = 0;
		for (String item : haystack) {
			if (needle.equals(item)) {
				return index;
				//index = index + 1;
			} else {
				index = index + 1;
			}
		}
		return index;
	}

}

class ExceptionalControlFlow1 {

	int find(String needle, List<String> haystack) {
		int index = 0;
		for (String item : haystack) {
			if (needle.equals(item)) {
				return index;
			}
			index = index + 1;
		}
		return index;
	}

}
