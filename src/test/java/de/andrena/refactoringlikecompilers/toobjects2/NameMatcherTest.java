package de.andrena.refactoringlikecompilers.toobjects2;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.jupiter.api.Test;

import de.andrena.refactoringlikecompilers.toobjects2.NameMatcher;


class NameMatcherTest {
	private NameMatcher frankMustermann = new NameMatcher("m", "Frank", "Mustermann");
	private NameMatcher erikaMusterfrau = new NameMatcher("f", "Erika", "Musterfrau");

	@Test
	void testGivenCorrectLastNameItMatches() {
		assertThat(frankMustermann, matchesName("Mustermann"));
	}

	@Test
	void testGivenEmptyStringDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName(""));
	}

	@Test
	void testGivenOnlyWrongLastNameItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Anders"));
	}

	@Test
	void testGivenCorrectFirstAndLastNameItMatches() {
		assertThat(frankMustermann, matchesName("Frank Mustermann"));
	}

	@Test
	void testGivenIncorrectFirstAndCorrectLastNameItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Bernd Mustermann"));
	}

	@Test
	void testGivenCorrectFirstAndIncorrectLastNameItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Frank Anders"));
	}

	@Test
	void testGivenCorrectFirstAndLastNameButWrongGenderItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Fr. Frank Mustermann"));
	}

	@Test
	void testGivenOnlyCorrectLastNameButWrongGenderItMatches() {
		assertThat(frankMustermann, matchesName("Fr. Mustermann"));
	}

	@Test
	void testGivenCorrectFirstAndLastNameAndCorrectGenderItMatches() {
		assertThat(frankMustermann, matchesName("Hr. Frank Mustermann"));
	}

	@Test
	void testGivenIncorrectFirstNameButCorrectLastNameAndCorrectGenderItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Hr. Bernd Mustermann"));
	}

	@Test
	void testGivenIncorrectLastNameButCorrectFirstNameAndCorrectGenderItDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Hr. Frank Anders"));
	}

	@Test
	void testGivenCorrectLastNameButUnknownGenderDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Mr. Mustermann"));
	}

	@Test
	void testGivenCorrectLastNameAndGenderItMatches() {
		assertThat(frankMustermann, matchesName("Hr. Mustermann"));
	}

	@Test
	void testGivenIncorrectLastNameButCorrectGenderDoesNotMatch() {
		assertThat(frankMustermann, doesNotMatchName("Hr. Anders"));
	}

	@Test
	void testFemaleGivenCorrectLastNameAndGenderItMatches() {
		assertThat(erikaMusterfrau, matchesName("Fr. Musterfrau"));
	}

	@Test
	void testFemaleGivenIncorrectLastNameButCorrectGenderItDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("Fr. Anders"));
	}

	@Test
	void testFemaleGivenCorrectFirstAndLastNameAndGenderItMatches() {
		assertThat(erikaMusterfrau, matchesName("Fr. Erika Musterfrau"));
	}

	@Test
	void testFemaleGivenCorrectLastNameAndWrongGenderItDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("Hr. Musterfrau"));
	}

	@Test
	void testFemaleGivenCorrectFirstAndLastNameItMatches() {
		assertThat(erikaMusterfrau, matchesName("Erika Musterfrau"));
	}

	@Test
	void testFemaleGivenCorrectLastNameButUnknownGenderDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("Ms. Musterfrau"));
	}

	@Test
	void testFemaleGivenIncorrectFirstNameButCorrectLastNameAndCorrectGenderItDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("Fr. Beate Musterfrau"));
	}

	@Test
	void testFemaleGivenIncorrectLastNameButCorrectFirstNameAndCorrectGenderItDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("Hr. Erika Anders"));
	}

	@Test
	void testFemaleGivenCorrectShortenedFirstNameAndCorrectLastNameItMatches() {
		assertThat(erikaMusterfrau, matchesName("E. Musterfrau"));
	}

	@Test
	void testFemaleGivenCorrectShortenedFirstNameButIncorrectLastNameItDoesNotMatch() {
		assertThat(erikaMusterfrau, doesNotMatchName("E. Anders"));
	}

	private TypeSafeDiagnosingMatcher<NameMatcher> matchesName(String name) {
		return new TypeSafeDiagnosingMatcher<NameMatcher>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("to match name '" + name + "'");
			}

			@Override
			protected boolean matchesSafely(NameMatcher item, Description mismatchDescription) {
				return item.isTheSameAs(name);
			}
		};
	}

	private TypeSafeDiagnosingMatcher<NameMatcher> doesNotMatchName(String name) {
		return new TypeSafeDiagnosingMatcher<NameMatcher>() {

			@Override
			public void describeTo(Description description) {
				description.appendText("to NOT match name '" + name + "'");
			}

			@Override
			protected boolean matchesSafely(NameMatcher item, Description mismatchDescription) {
				return !item.isTheSameAs(name);
			}
		};
	}
}
