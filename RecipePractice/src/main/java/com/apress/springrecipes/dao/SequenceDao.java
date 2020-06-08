package com.apress.springrecipes.dao;

import com.apress.springrecipes.domain.Sequence;

public interface SequenceDao {
	public Sequence getSequence(String sequenceId);
	public int getNextValue(String sequenceId);
}
