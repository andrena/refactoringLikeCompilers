package de.andrena.optimizeformaintainability.ssa;

/**
 * A code snippet containing a sequential instruction flow (no branches):
 * 
 * * replace all compound operators by primitive ones
 * * assign each temporary result to a new variable and this variable in future operations
 */
public class Sequences {

	public String wrap(String prefix, Object object, String suffix) {
		String wrapped1 = "";
		String wrapped2 = wrapped1 + prefix;
		String wrapped3 = wrapped2 + object.toString();
		String wrapped4 = wrapped3 + suffix;
		return wrapped4;
	}

}
