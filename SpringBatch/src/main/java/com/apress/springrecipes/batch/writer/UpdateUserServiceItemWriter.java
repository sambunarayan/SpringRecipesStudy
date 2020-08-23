package com.apress.springrecipes.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.apress.springrecipes.beans.UserRegistration;
import com.apress.springrecipes.service.UserUpdateService;

public class UpdateUserServiceItemWriter implements ItemWriter<UserRegistration> {
	
	private UserUpdateService userUpdateService;
	
	public UpdateUserServiceItemWriter(UserUpdateService userUpdateService) {
		this.userUpdateService = userUpdateService;
	}

	@Override
	public void write(List<? extends UserRegistration> items) throws Exception {
		items.forEach(item -> userUpdateService.updateUser(item));
	}
}
