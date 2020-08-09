package com.ssafy.yogart.aicoach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.yogart.aicoach.model.AicoachCourse;

public interface AicoachCourseRepository extends JpaRepository<AicoachCourse, Integer> {
	@Query(value="SELECT * FROM ai_course_detail WHERE ai_course_detail_no = :ai_course_detail_no", nativeQuery=true)
	AicoachCourse findByAiCourseDetailNo(@Param("ai_course_detail_no") int ai_course_detail_no);
	
	AicoachCourse findTopByOrderByAiCourseDetailIdDesc();
}
