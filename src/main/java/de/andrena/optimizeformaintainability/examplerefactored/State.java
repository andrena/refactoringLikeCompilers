package de.andrena.optimizeformaintainability.examplerefactored;

import java.util.List;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public interface State {

	List<String> tokens() throws TokenizeException;

	State next(char c);

	default boolean isOpenTag(char c) {
		return c == '<';
	}

	default boolean isCloseTag(char c) {
		return c == '>';
	}

	default boolean isOpenEntity(char c) {
		return c == '&';
	}

	default boolean isCloseEntity(char c) {
		return c == ';';
	}

	default boolean isOpenAttr(char c) {
		return c == '"';
	}

	default boolean isCloseAttr(char c) {
		return c == '"';
	}

}