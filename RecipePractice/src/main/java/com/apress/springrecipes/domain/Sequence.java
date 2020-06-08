package com.apress.springrecipes.domain;

public class Sequence {
	
	private final String id;
	private final String prefix;
	private final String suffix;
	
	public Sequence(String id, String prefix, String suffix) {
		this.id = id;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	@Override
	public String toString() {
		return "["+this.id+","+this.prefix+","+this.suffix+"]";
	}
}
