package de.andrena.refactoringlikecompilers.toobjects1;

import java.util.Arrays;

public class UglyClass {
	private final String firstName;
	private final String lastName;
	private Gender gender;

	public UglyClass(String gender, String firstName, String lastName) {
		this.gender = Gender.valueOf(gender);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isTheSameAs(String name) {
		String[] nameParts = name.split(" ");
		boolean salutationMatched = gender.isSalutationMatched(nameParts);
		boolean abbreviationMatched = !gender.isSalutationMatched(nameParts) && isAbbreviationMatched(nameParts);
		String[] strippedNameParts = stripNameParts(nameParts, salutationMatched, abbreviationMatched);
		if (abbreviationMatched || strippedNameParts.length == 1) {
			return matchLastName(strippedNameParts);
		} else {
			return matchFullName(strippedNameParts);
		}
	}

	private String[] stripNameParts(String[] nameParts, boolean salutationMatched, boolean abbreviationMatched) {
		if (nameParts[0].indexOf('.') > 0 && salutationMatched || abbreviationMatched) {
			return Arrays.copyOfRange(nameParts, 1, nameParts.length);
		}
		return nameParts;
	}

	private boolean isAbbreviationMatched(String[] nameParts) {
		return nameParts[0].indexOf('.') > 0 && firstName.startsWith(nameParts[0].substring(0, nameParts[0].length() - 1));
	}

	private boolean matchFullName(String[] nameParts) {
		return firstName.equals(nameParts[0]) && lastName.equals(nameParts[1]);
	}

	private boolean matchLastName(String[] nameParts) {
		return lastName.equals(nameParts[0]);
	}

}