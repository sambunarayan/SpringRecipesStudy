package com.apress.springrecipes.process;

import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import com.apress.springrecipes.beans.UserRegistration;

public class UserDuplicateCheckProcess implements ItemProcessor<UserRegistration, UserRegistration> {
	
	private StepExecution stepExecution;
	
	@BeforeStep
	public void saveStepExecution(StepExecution stepExcution) {
		System.out.print("saveStepExecution");
		this.stepExecution = stepExcution;
	}
	
	@Override
	public UserRegistration process(UserRegistration item) throws Exception {
		System.out.println("UserDuplicateCheckProcess start");
		Map<String, JobParameter> params = stepExecution.getJobParameters().getParameters();
		for (String jobParameterKey : params.keySet()) {
			System.out.println(String.format("%s=%s", jobParameterKey, params.get(jobParameterKey).getValue().toString()));
		}
		
		return item;
	}

}
