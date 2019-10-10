package de.andrena.optimizeformaintainability.examplessa;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import de.andrena.optimizeformaintainability.example.TokenizeException;

public class HTMLCleaner {

	private static class State {
		final List<String> tokens;
		final String current;
		final boolean tag;
		final boolean attr;
		final boolean entity;
		final Optional<TokenizeException> e;

		public State() {
			this(new ArrayList<>(), "", false, false, false, Optional.empty());
		}

		public State(List<String> tokens, String current, boolean tag, boolean attr, boolean entity, Optional<TokenizeException> e) {
			this.tokens = unmodifiableList(tokens);
			this.current = current;
			this.tag = tag;
			this.attr = attr;
			this.entity = entity;
			this.e = e;
		}

		public State withCurrent(String current) {
			return new State(tokens, current, tag, attr, entity, e);
		}

		public State addToken(String token) {
			List<String> tokens = new ArrayList<String>(this.tokens);
			tokens.add(token);

			return new State(tokens, current, tag, attr, entity, e);
		}

		public State withTag(boolean tag) {
			return new State(tokens, current, tag, attr, entity, e);
		}

		public State withAttr(boolean attr) {
			return new State(tokens, current, tag, attr, entity, e);
		}

		public State withEntity(boolean entity) {
			return new State(tokens, current, tag, attr, entity, e);
		}

		public State andThrow(TokenizeException e) {
			return new State(tokens, current, tag, attr, entity, Optional.of(e));
		}

	}

	public List<String> tokenize(String html) throws TokenizeException {
		State state = html.chars()
			.mapToObj(i -> Character.valueOf((char) i))
			.reduce(new State(), this::next, (s1, s2) -> s1);
		if (state.e.isPresent()) {
			throw state.e.get();
		}
		State finalstate = state.addToken(state.current).withCurrent("");
		List<String> tokens = new ArrayList<>(finalstate.tokens);
		Iterator<String> tokenIterator = tokens.iterator();
		while (tokenIterator.hasNext()) {
			String next = tokenIterator.next();
			if (next.isEmpty()) {
				tokenIterator.remove();
			}
		}
		return tokens;
	}

	private State next(State s, char c) {
		if (s.e.isPresent()) {
			return s;
		} else if (!Character.isDefined(c)) {
			return s.andThrow(new TokenizeException());
		}
		if (s.tag && s.attr) {
			return s.withAttr(!isCloseAttr(c)).withCurrent(s.current + c).withCurrent(s.current + c);
		} else if (s.tag) {
			boolean tag = !isCloseTag(c);
			if (!tag) {
				return s.withTag(tag).withAttr(isOpenAttr(c)).addToken(s.current + c).withCurrent("");
			}
			return s.withTag(tag).withAttr(isOpenAttr(c)).withCurrent(s.current + c);
		} else if (s.entity) {
			boolean entity = !isCloseEntity(c);
			if (!entity) {
				return s.withEntity(entity).addToken(s.current + c).withCurrent("");
			}
			return s.withEntity(entity).withCurrent(s.current + c);
		} else {
			boolean entity = isOpenEntity(c);
			boolean tag = isOpenTag(c);
			if (tag || entity) {
				return s.withTag(tag).withEntity(entity).addToken(s.current).withCurrent("" + c);
			}
			return s.withTag(tag).withEntity(entity).withCurrent(s.current + c);
		}
	}

	private boolean isOpenTag(char c) {
		return c == '<';
	}

	private boolean isCloseTag(char c) {
		return c == '>';
	}

	private boolean isOpenEntity(char c) {
		return c == '&';
	}

	private boolean isCloseEntity(char c) {
		return c == ';';
	}

	private boolean isOpenAttr(char c) {
		return c == '"';
	}

	private boolean isCloseAttr(char c) {
		return c == '"';
	}

}
