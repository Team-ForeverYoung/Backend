package com.java.backend.domain.promotion.repository;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.entity.UserEvent;

@Service
public class UserEventRepoService {
	private final UserEventRepository userEventRepository;

	public UserEventRepoService(UserEventRepository userEventRepository) {
		this.userEventRepository = userEventRepository;
	}

	public void saveUserEvent(UserEvent userEvent){
		userEventRepository.save(userEvent);
	}
}
