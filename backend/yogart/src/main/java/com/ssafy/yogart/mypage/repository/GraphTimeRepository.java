package com.ssafy.yogart.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.yogart.mypage.model.GraphTime;

public interface GraphTimeRepository extends JpaRepository<GraphTime, Integer> {
	
}
