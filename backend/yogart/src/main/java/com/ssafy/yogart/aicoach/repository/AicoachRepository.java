package com.ssafy.yogart.aicoach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.yogart.aicoach.model.Aicoach;

public interface AicoachRepository extends JpaRepository<Aicoach, Integer> {
	@Query(value="SELECT * FROM ai_course WHERE ai_course_user_nickname = :ai_course_user_nickname", nativeQuery=true)
	List<Aicoach> findByAiCourseUserNickname(@Param("ai_course_user_nickname") String ai_course_user_nickname);
}