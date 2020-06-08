package com.apress.springrecipes.sequence;

public class NumberPrefixGenerator implements PrefixGenerator {
	
	private String name = "NumberPrefix";
	
	@Override
	public String toString() {
		return name;
	}
}
