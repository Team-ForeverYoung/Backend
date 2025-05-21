package com.java.backend.domain.promotion.repository;

import org.springframework.stereotype.Service;

import com.java.backend.domain.promotion.entity.UserEvent;

@Service
public class UserEventRepoService {
	private final UserEventRepository userEventRepository;

	public UserEventRepoService(UserEventRepository userEventRepository) {
		this.userEventRepository = userEventRepository;
	}
	public boolean isExistUserEvent(Long userId, Long eventId) {
		return userEventRepository.findByUserAndEvent(userId, eventId).isPresent();
	}

	public UserEvent saveUserEvent(UserEvent userEvent){
		return userEventRepository.save(userEvent);
	}

}
