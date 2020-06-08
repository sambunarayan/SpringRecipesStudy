package com.apress.springrecipes.sequence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.springrecipes.dao.SequenceDao;
import com.apress.springrecipes.domain.Sequence;

@Component
public class SequenceService {
	
	private SequenceDao sequenceDao;
	
	@Autowired
	public void setSequenceDao(SequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}
	
	public String generate(String sequenceId) {
		Sequence sequence = sequenceDao.getSequence(sequenceId);
		int value = sequenceDao.getNextValue(sequenceId);
		return sequence.getPrefix() + value + sequence.getSuffix();
	}
}
