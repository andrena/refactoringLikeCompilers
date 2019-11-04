package de.andrena.optimizeformaintainability.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class HTMLCleanerTest {

	private HTMLCleaner cleaner;

	@BeforeEach
	void before() throws Exception {
		cleaner = new HTMLCleaner();
	}

	@Nested
	class testTokenize {
		@Test
		void illegalUnicode() throws Exception {
			assertThrows(TokenizeException.class, () -> cleaner.tokenize("" + (char) 888));
		}
		
		@Test
		void emptyString() throws Exception {
			assertThat(cleaner.tokenize("")).isEmpty();
		}

		@Test
		void plainStrings() throws Exception {
			assertThat(cleaner.tokenize("plain")).contains("plain");
		}

		@Test
		void tags() throws Exception {
			assertThat(cleaner.tokenize("<tag>")).contains("<tag>");
		}

		@Test
		void entities() throws Exception {
			assertThat(cleaner.tokenize("&amp;")).contains("&amp;");
		}

		@Test
		void entitiesInText() throws Exception {
			assertThat(cleaner.tokenize("Bed &amp; Breakfast")).contains("Bed ", "&amp;", " Breakfast");
		}

		@Test
		void tagsWithAttribute() throws Exception {
			assertThat(cleaner.tokenize("<tag attr=\"value\">")).contains("<tag attr=\"value\">");
		}

		@Test
		void tagsWithMultipleAttributes() throws Exception {
			assertThat(cleaner.tokenize("<tag attr1=\"value1\" attr2=\"value2\">")).contains("<tag attr1=\"value1\" attr2=\"value2\">");
		}

		@Test
		void tagsInText() throws Exception {
			assertThat(cleaner.tokenize("text before<tag> text after")).contains("text before", "<tag>", " text after");
		}

		@Test
		void tagsEnclosingText() throws Exception {
			assertThat(cleaner.tokenize("text before<tag> text between </tag> text after")).contains("text before", "<tag>", " text between ", "</tag>", " text after");
		}
	}

}
