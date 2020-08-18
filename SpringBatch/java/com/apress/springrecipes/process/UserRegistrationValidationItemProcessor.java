package com.apress.springrecipes.process;

import org.springframework.batch.item.ItemProcessor;

import com.apress.springrecipes.beans.UserRegistration;

public class UserRegistrationValidationItemProcessor implements ItemProcessor<UserRegistration, UserRegistration> {

	private String stripNonNumbers(String input) {
		return input.trim();
	}
	
	private boolean isTelephoneValid(String telephone) {
		if (telephone == null || telephone.isEmpty()) {
			return false;
		} else if (telephone.length() < 11) {
			return false;
		}
		return true;
	}
	
	private boolean isZipCodeValid(String zip) {
		if (zip == null || zip.isEmpty()) {
			return false;
		} else if (zip.length() < 7) {
			return false;
		}
		return true;
	}
	
	private boolean isValidState(String state) {
		if (state == null || state.isEmpty()) {
			return false;
		} else if (state.length() < 3) {
			return false;
		}
		return true;
	}
	
	@Override
	public UserRegistration process(UserRegistration item) throws Exception {
		String zipCode = stripNonNumbers(item.getZip());
		String telephone = stripNonNumbers(item.getPhoneNumber());
		String state = item.getState();
		if (isTelephoneValid(telephone) && isZipCodeValid(zipCode) && isValidState(state)) {
			item.setZip(zipCode);
			item.setPhoneNumber(telephone);
			return item;
		}
		return null;
	}

}
