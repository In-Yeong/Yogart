package com.ssafy.yogart.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.mypage.model.GraphBodyPart;
import com.ssafy.yogart.user.model.User;

public interface GraphBodyPartRepository extends JpaRepository<GraphBodyPart, Integer> {
	GraphBodyPart findByGraphBodyPartUserNickname(User user);
//	GraphBodyPart findByUserUserNickname(String nickname);
}
