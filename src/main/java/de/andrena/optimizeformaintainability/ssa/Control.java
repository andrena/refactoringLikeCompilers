package de.andrena.optimizeformaintainability.ssa;

/**
 * Helper class to transform exceptional control flow to ssa
 */
public enum Control {
	BREAK, CONTINUE, RETURN, THROW;
}