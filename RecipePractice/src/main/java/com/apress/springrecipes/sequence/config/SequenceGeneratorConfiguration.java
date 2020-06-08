package com.apress.springrecipes.sequence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.apress.springrecipes.sequence.DatePrefixGenerator;
import com.apress.springrecipes.sequence.NumberPrefixGenerator;
import com.apress.springrecipes.sequence.SequenceGenerator;

@Configuration
public class SequenceGeneratorConfiguration {

	@Bean
	@DependsOn({"numberPrefixGenerator","datePrefixGenerator",})
	public SequenceGenerator sequenceGenerator() {
		System.out.println(">>>>>>>>>>> initialize sequenceGenerator <<<<<<<<<<<");
		SequenceGenerator seqgen = new SequenceGenerator();
		seqgen.setPrefix("30");
		seqgen.setSuffix("A");
		seqgen.setDatePrefixGenerator(datePrefixGenerator());
		seqgen.setInitial(100000);
		return seqgen;
	}
	
	@Bean
	public DatePrefixGenerator datePrefixGenerator() {
		System.out.println(">>>>>>>>>>> initialize datePrefixGenerator <<<<<<<<<<<");
		DatePrefixGenerator dpg = new DatePrefixGenerator();
		dpg.setPattern("yyyyMMdd");
		return dpg;
	}
	
	@Bean
	@DependsOn("datePrefixGenerator"
			+ "")
	public NumberPrefixGenerator numberPrefixGenerator() {
		System.out.println(">>>>>>>>>>> initialize numberPrefixGenerator <<<<<<<<<<<");
		NumberPrefixGenerator npg = new NumberPrefixGenerator();
		return npg;
	}
}
