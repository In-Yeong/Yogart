package com.ssafy.yogart.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.user.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserEmailAndLoginMethod(String email, String loginmethod);
	
	User findByUserEmailAndUserPassword(String username, String password);

	User findByUserEmail(String username);
	
	User findByUserNickname(String usernickname);
}
