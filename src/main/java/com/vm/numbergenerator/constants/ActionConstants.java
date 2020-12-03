package com.vm.numbergenerator.constants;

public enum ActionConstants {
	GET_NUMLIST("get_numl;ist");

	private final String text;

	ActionConstants(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
