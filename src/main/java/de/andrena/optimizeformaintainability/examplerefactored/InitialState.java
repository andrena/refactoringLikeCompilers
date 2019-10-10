package de.andrena.optimizeformaintainability.examplerefactored;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public class InitialState implements State {

	public InitialState() {
	}

	@Override
	public List<String> tokens() throws TokenizeException {
		return Collections.emptyList();
	}

	@Override
	public State next(char c) {
		if (!Character.isDefined(c)) {
			return new ExceptionalState(new TokenizeException());
		}
		if (isOpenTag(c)) {
			return new TagState(asList(""), "" + c);
		} else if (isOpenEntity(c)) {
			return new EntityState(asList(""), "" + c);
		}
		return new PlainState(new ArrayList<>(), "" + c);
	}

}
