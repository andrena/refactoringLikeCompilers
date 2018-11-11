package de.andrena.refactoringlikecompilers.ssa;

import java.util.Arrays;

public class UglyClass {
	private final String firstName;
	private final String lastName;
	private final String gender;

	public UglyClass(String gender, String firstName, String lastName) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean isTheSameAs(String name) {
		String[] nameParts1 = name.split(" ");
		boolean salutationMatched = f1(nameParts1);
		boolean abbreviationMatched = f2(nameParts1);
		String[] nameParts = f3(nameParts1, salutationMatched, abbreviationMatched);
		if (abbreviationMatched || nameParts.length == 1) {
			return matchLastName(nameParts);
		} else {
			return matchFullName(nameParts);
		}
	}

	private String[] f3(String[] nameParts, boolean salutationMatched, boolean abbreviationMatched) {
		if (nameParts[0].indexOf('.') > 0 && salutationMatched || abbreviationMatched) {
			return Arrays.copyOfRange(nameParts, 1, nameParts.length);
		}
		return nameParts;
	}

	private boolean f2(String[] nameParts) {
		if ((nameParts[0].indexOf('.') > 0 && nameParts[0].equals("Hr.") && gender.equals("m"))
			|| (nameParts[0].indexOf('.') > 0 && nameParts[0].equals("Fr.") && gender.equals("f"))) {
		} else if (nameParts[0].indexOf('.') > 0 && firstName.startsWith(nameParts[0].substring(0, nameParts[0].length() - 1))) {
			return true;
		}
		return false;
	}

	private boolean f1(String[] nameParts) {
		if ((nameParts[0].indexOf('.') > 0 && nameParts[0].equals("Hr.") && gender.equals("m"))
			|| (nameParts[0].indexOf('.') > 0 && nameParts[0].equals("Fr.") && gender.equals("f"))) {
			return true;
		}
		return false;
	}

	private boolean matchFullName(String[] nameParts) {
		return firstName.equals(nameParts[0]) && lastName.equals(nameParts[1]);
	}

	private boolean matchLastName(String[] nameParts) {
		return lastName.equals(nameParts[0]);
	}

}