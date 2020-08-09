package com.ssafy.yogart.aicoach.service;

import java.util.List;

import com.ssafy.yogart.aicoach.model.Aicoach;
import com.ssafy.yogart.aicoach.model.AicoachCourse;

public interface AicoachService {
	// 전체목록 조회
	List<AicoachCourse> allCourse();
	// 상세 조회
	AicoachCourse detailCourse(int aiCourseDetailNo);
	// 코스 등록
	AicoachCourse createCourse(AicoachCourse aicoachCourse);
	
	List<Aicoach> getNo(String nickname);
}
