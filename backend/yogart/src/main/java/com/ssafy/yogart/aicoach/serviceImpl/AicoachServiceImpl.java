package com.ssafy.yogart.aicoach.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yogart.aicoach.model.Aicoach;
import com.ssafy.yogart.aicoach.model.AicoachCourse;
import com.ssafy.yogart.aicoach.repository.AicoachCourseRepository;
import com.ssafy.yogart.aicoach.repository.AicoachRepository;
import com.ssafy.yogart.aicoach.service.AicoachService;

@Service
public class AicoachServiceImpl implements AicoachService {
	
	@Autowired
	private AicoachCourseRepository aicoachcourseRepository;

	@Autowired
	private AicoachRepository aicoachRepository;

	@Override
	public List<AicoachCourse> allCourse() {
		List<AicoachCourse> list = aicoachcourseRepository.findAll();
		return list;
	}
	

	@Override
	public AicoachCourse detailCourse(int aiCourseDetailNo) {
		AicoachCourse aicoach = aicoachcourseRepository.findByAiCourseDetailNo(aiCourseDetailNo);
		return aicoach;
	}

	@Override
	public AicoachCourse createCourse(AicoachCourse aicoachCourse) {
		return aicoachcourseRepository.save(aicoachCourse);
	}



	@Override
	public List<Aicoach> getNo(String nickname) {
		return aicoachRepository.findByAiCourseUserNickname(nickname);
	}

}
