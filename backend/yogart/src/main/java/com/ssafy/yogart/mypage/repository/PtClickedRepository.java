package com.ssafy.yogart.mypage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.mypage.model.PtClicked;
import com.ssafy.yogart.user.model.User;

public interface PtClickedRepository extends JpaRepository<PtClicked, Integer> {
	List<PtClicked> findByPtStudentId(User user);
}
