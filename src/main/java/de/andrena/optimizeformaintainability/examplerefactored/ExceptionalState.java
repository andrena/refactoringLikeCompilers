package de.andrena.optimizeformaintainability.examplerefactored;

import java.util.List;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public class ExceptionalState implements State {

	private TokenizeException e;

	public ExceptionalState(TokenizeException e) {
		this.e = e;
	}

	@Override
	public List<String> tokens() throws TokenizeException {
		throw e;
	}

	@Override
	public State next(char c) {
		return this;
	}

}
