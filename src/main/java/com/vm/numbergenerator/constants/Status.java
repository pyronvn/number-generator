package com.vm.numbergenerator.constants;

public enum Status {

	SUCCESS("Success"), ERROR("Error"), IN_PROGRESS("In-Progress"), CREATED("CREATED");

	private final String text;

	Status(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

}
