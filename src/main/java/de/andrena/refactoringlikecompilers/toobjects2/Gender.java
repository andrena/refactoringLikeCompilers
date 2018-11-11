package de.andrena.refactoringlikecompilers.toobjects2;

public enum Gender {
	f("Fr."),m("Hr.");
	
	private String salutation;

	private Gender(String salutation) {
		this.salutation = salutation;
	}

	public boolean isSalutationMatched(String[] nameParts) {
		return nameParts[0].indexOf('.') > 0 
			&& nameParts[0].equals(salutation);
	}


}