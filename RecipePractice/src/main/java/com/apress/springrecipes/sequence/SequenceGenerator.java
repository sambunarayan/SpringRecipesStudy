package com.apress.springrecipes.sequence;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class SequenceGenerator {

	private String prefix;
	private String suffix;
	private DatePrefixGenerator dpg;
	private int initial;
	private final AtomicInteger counter = new AtomicInteger();
	
	@Autowired
	private PrefixGenerator[] prefixGenerators;
	@Autowired
	private List<PrefixGenerator> prefixGenList;
	@Autowired
	private Map<String, PrefixGenerator> prefixGenMap;
	
	public SequenceGenerator() {
		
	}
	
	@Required
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Required
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public void setDatePrefixGenerator(DatePrefixGenerator dpg) {
		this.dpg = dpg;
	}
	
	public void setInitial(int initial) {
		this.initial = initial;
	}
	
	public void printPrefix() {
		
		System.out.print("PrefixGen Array -> ");
		for (PrefixGenerator pfg : prefixGenerators) {
			System.out.print(pfg + " ");
		}
		System.out.println();
		System.out.print("PrefixGen List -> ");
		prefixGenList.forEach(pfg->System.out.print(pfg + " "));
		System.out.println();
		System.out.println("PrefixGen Map -> ");
		for (String key : prefixGenMap.keySet()) {
			System.out.println(key + "," + prefixGenMap.get(key));
		}
		System.out.println();
	}
	
	public String getSequence() {
		StringBuilder builder = new StringBuilder();
		builder.append(prefix);
		builder.append(initial);
		builder.append(counter.getAndIncrement());
		builder.append(suffix);
		builder.append(dpg);
		return builder.toString();
	}
	
}
