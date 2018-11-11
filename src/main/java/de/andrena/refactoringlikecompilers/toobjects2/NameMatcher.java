package de.andrena.refactoringlikecompilers.toobjects2;

import java.util.Arrays;

public class NameMatcher {
	private FullName fullName;
	private Gender gender;

	public NameMatcher(String gender, String firstName, String lastName) {
		this.gender = Gender.valueOf(gender);
		this.fullName = new FullName(firstName, lastName);
	}

	public boolean isTheSameAs(String name) {
		String[] nameParts = name.split(" ");
		
		boolean salutationMatched = gender.isSalutationMatched(nameParts);
		
		boolean abbreviationMatched = !gender.isSalutationMatched(nameParts) && fullName.isAbbreviationMatched(nameParts);
		
		String[] strippedNameParts = stripNameParts(nameParts, salutationMatched, abbreviationMatched);
		
		if (abbreviationMatched || strippedNameParts.length == 1) {
			return fullName.matchLastName(strippedNameParts);
		} else {
			return fullName.matchFullName(strippedNameParts);
		}
	}

	private String[] stripNameParts(String[] nameParts, boolean salutationMatched, boolean abbreviationMatched) {
		if (nameParts[0].indexOf('.') > 0 && salutationMatched || abbreviationMatched) {
			return Arrays.copyOfRange(nameParts, 1, nameParts.length);
		}
		return nameParts;
	}

}