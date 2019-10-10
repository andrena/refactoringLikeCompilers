package de.andrena.optimizeformaintainability.patterns;

/**
 * A code snippet containing a sequential instruction flow (no branches).
 */
public class Sequences {

	public String wrap(String prefix, Object object, String suffix) {
		String wrapped = "";
		wrapped += prefix;
		wrapped += object.toString();
		wrapped += suffix;
		return wrapped;
	}

}
