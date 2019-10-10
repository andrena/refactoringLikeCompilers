package de.andrena.optimizeformaintainability.patterns;

/**
 * A code snippet containing a branch which modifies a single variable.
 */
public class SimpleConditionals {

	public String format(Sign sign, int number) {
		String wrapped = "";
		String formattedSign = "";
		if (sign == Sign.MINUS) {
			formattedSign = "-";
		}
		wrapped += formattedSign;
		wrapped += number;
		return wrapped;
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}
