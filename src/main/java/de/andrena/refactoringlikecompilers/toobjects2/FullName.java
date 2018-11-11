package de.andrena.refactoringlikecompilers.toobjects2;

public class FullName {
	private String firstName;
	private String lastName;

	public FullName(String firstName,String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isAbbreviationMatched(String[] nameParts) {
		return nameParts[0].indexOf('.') > 0 
			&& firstName.startsWith(nameParts[0].substring(0, nameParts[0].length() - 1));
	}

	public boolean matchFullName(String[] nameParts) {
		return firstName.equals(nameParts[0]) 
			&& lastName.equals(nameParts[1]);
	}

	public boolean matchLastName(String[] nameParts) {
		return lastName.equals(nameParts[0]);
	}

}