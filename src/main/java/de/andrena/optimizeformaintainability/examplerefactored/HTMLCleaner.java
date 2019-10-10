package de.andrena.optimizeformaintainability.examplerefactored;

import java.util.List;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public class HTMLCleaner {
	
	public List<String> tokenize(String html) throws TokenizeException {
		return html.chars()
			.mapToObj(i -> Character.valueOf((char) i))
			.reduce(new InitialState(), State::next, (s1, s2) -> s1)
			.tokens();
	}

}
