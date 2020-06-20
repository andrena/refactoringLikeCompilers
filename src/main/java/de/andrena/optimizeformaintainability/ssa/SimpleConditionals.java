package de.andrena.optimizeformaintainability.ssa;

/**
 * A code snippet containing a branch which modifies a single variable:
 * 
 * * replace all compound operators by primitive ones
 * * assign each temporary result to a new variable and this variable in future operations
 * * extract the conditional block and all definitions of the modified variable into a new method
 * * move returns into the branches
 * * inline all constant assignments
 */
public class SimpleConditionals {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = fmt(sign);
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	private String fmt(Sign sign) {
		if (sign == Sign.MINUS) {
			return "-";
		} else {
			return "";
		}
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}

class SimpleConditionals5 {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = fmt(sign);
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	private String fmt(Sign sign) {
		if (sign == Sign.MINUS) {
			String formattedSign = "";
			formattedSign = "-";
			return formattedSign;
		} else {
			String formattedSign = "";
			return formattedSign;
		}
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}

class SimpleConditionals4 {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = fmt(sign);
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	private String fmt(Sign sign) {
		String formattedSign = "";
		if (sign == Sign.MINUS) {
			formattedSign = "-";
			return formattedSign;
		} else {
			return formattedSign;
		}
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}

class SimpleConditionals3 {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = fmt(sign);
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	private String fmt(Sign sign) {
		String formattedSign = "";
		if (sign == Sign.MINUS) {
			formattedSign = "-";
		} else {
			
		}
		return formattedSign;
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}

class SimpleConditionals2 {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = fmt(sign);
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	private String fmt(Sign sign) {
		String formattedSign = "";
		if (sign == Sign.MINUS) {
			formattedSign = "-";
		}
		return formattedSign;
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}

class SimpleConditionals1 {

	public String format(Sign sign, int number) {
		String wrapped1 = "";
		String formattedSign = "";
		if (sign == Sign.MINUS) {
			formattedSign = "-";
		}
		String wrapped2 = wrapped1 + formattedSign;
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}

	public static enum Sign {
		PLUS, MINUS;
	}
}
