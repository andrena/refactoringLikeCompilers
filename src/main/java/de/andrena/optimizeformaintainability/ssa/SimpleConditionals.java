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
		String wrapped2 = wrapped1 + fmt(sign);
		String wrapped3 = wrapped2 + number;
		return wrapped3;
	}


	private String fmt(Sign sign) {
		if (sign == Sign.MINUS) {
			return "-";
		}
		return "";
	}


	public static enum Sign {
		PLUS, MINUS;
	}
}
