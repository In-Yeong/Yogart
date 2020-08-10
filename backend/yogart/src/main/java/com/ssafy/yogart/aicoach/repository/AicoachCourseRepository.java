package com.ssafy.yogart.aicoach.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.yogart.aicoach.model.AicoachCourse;
import com.ssafy.yogart.user.model.User;

public interface AicoachCourseRepository extends JpaRepository<AicoachCourse, Integer> {
	@Query(value="SELECT * FROM ai_course WHERE ai_course_id = :ai_course_id", nativeQuery=true)
	AicoachCourse findByAiCourseId(@Param("ai_course_id") int ai_course_id);
	
	List<AicoachCourse> findByAiCourseUserNickname(User user);
	
	AicoachCourse findTopByOrderByAiCourseIdDesc();
}
