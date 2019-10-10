package de.andrena.optimizeformaintainability.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HTMLCleaner {

	public List<String> tokenize(String html) throws TokenizeException {
		List<String> tokens = new ArrayList<>();
		StringBuilder current = new StringBuilder();
		boolean tag = false;
		boolean attr = false;
		boolean entity = false;
		for (char c : html.toCharArray()) {
			if (!Character.isDefined(c)) {
				throw new TokenizeException();
			}
			if (tag && attr) {
				attr = !isCloseAttr(c);
				current.append(c);
			} else if (tag) {
				tag = !isCloseTag(c);
				attr = isOpenAttr(c);
				current.append(c);
				if (!tag) {
					tokens.add(current.toString());
					current.setLength(0);
				}
			} else if (entity){
				entity = !isCloseEntity(c);
				current.append(c);
				if (!entity) {
					tokens.add(current.toString());
					current.setLength(0);
				}
			} else {
				tag = isOpenTag(c);
				entity = isOpenEntity(c);
				if (tag || entity) {
					tokens.add(current.toString());
					current.setLength(0);
				}
				current.append(c);
			}
		}
		tokens.add(current.toString());
		current.setLength(0);
		Iterator<String> tokenIterator = tokens.iterator();
		while (tokenIterator.hasNext()) {
			String next = tokenIterator.next();
			if (next.isEmpty()) {
				tokenIterator.remove();
			}
		}
		return tokens;
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
