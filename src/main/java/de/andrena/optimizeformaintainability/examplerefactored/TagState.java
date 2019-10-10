package de.andrena.optimizeformaintainability.examplerefactored;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public class TagState implements State {

	private List<String> tokens;
	private String current;

	public TagState(List<String> tokens, String current) {
		this.tokens = tokens;
		this.current = current;
	}

	@Override
	public List<String> tokens() throws TokenizeException {
		List<String> tokens = new ArrayList<>(this.tokens);
		tokens.add(current);
		Iterator<String> tokenIterator = tokens.iterator();
		while (tokenIterator.hasNext()) {
			String next = tokenIterator.next();
			if (next.isEmpty()) {
				tokenIterator.remove();
			}
		}
		return tokens;
	}

	@Override
	public State next(char c) {
		if (!Character.isDefined(c)) {
			return new ExceptionalState(new TokenizeException());
		}
		if (isCloseTag(c)) {
			List<String> tokens = new ArrayList<>(this.tokens);
			tokens.add(current + c);
			return new PlainState(tokens, "");
		}
		return new TagState(new ArrayList<>(tokens), current + c);
	}

}
