package de.andrena.optimizeformaintainability.ssa;

import java.util.function.UnaryOperator;

/**
 * Helper class to transform exceptional control flow to ssa
 * Loop item for loops with only RuntimeExceptions (use other class for checked exceptions, note that generics are not so easy here)
 */
public class LoopItem<I> {
	private final I value;
	private final RuntimeException exception;
	private final Control control;

	public LoopItem(I value) {
		this(value, null, Control.CONTINUE);
	}

	public LoopItem<I> ret() {
		if (control == Control.CONTINUE) {
			return new LoopItem<>(value, exception, Control.RETURN);
		} else {
			return this;
		}
	}

	public LoopItem<I> brk() {
		if (control == Control.CONTINUE) {
			return new LoopItem<>(value, exception, Control.BREAK);
		} else {
			return this;
		}
	}

	public I value() {
		if (exception != null) {
			throw exception;
		}
		return value;
	}

	public LoopItem(I value, RuntimeException exception, Control control) {
		this.value = value;
		this.exception = exception;
		this.control = control;
	}

	public LoopItem<I> map(UnaryOperator<I> map) {
		if (control == Control.CONTINUE) {
			try {
				return new LoopItem<>(map.apply(value), exception, Control.CONTINUE);
			} catch (RuntimeException e) {
				return new LoopItem<>(null, e, Control.THROW);
			}
		} else {
			return this;
		}
	}
}