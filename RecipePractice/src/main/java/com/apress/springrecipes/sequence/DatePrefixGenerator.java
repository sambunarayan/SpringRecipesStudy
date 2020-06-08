package com.apress.springrecipes.sequence;

public class DatePrefixGenerator implements PrefixGenerator {
	
	private String pattern;

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public String toString() {
		return pattern;
	}
}
