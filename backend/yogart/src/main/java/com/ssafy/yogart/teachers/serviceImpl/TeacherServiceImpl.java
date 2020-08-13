package com.ssafy.yogart.teachers.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yogart.teachers.model.PtClicked;
import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.teachers.repository.PtClickedRepository;
import com.ssafy.yogart.teachers.repository.PtInfoRepository;
import com.ssafy.yogart.teachers.service.TeacherService;
import com.ssafy.yogart.user.model.User;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	PtInfoRepository ptInfoRepository;
	
	@Autowired
	PtClickedRepository ptClickedRepository;
	
	@Override
	public List<PtInfo> showAllTeacherPTlist(User user) {
		return ptInfoRepository.findByPtTeacherId(user);
	}

	@Override
	public PtInfo showPTInfo(int ptId) {
		return ptInfoRepository.findByPtId(ptId);
	}

	@Override
	public List<PtClicked> showClassTime(PtInfo ptinfo) {
		return ptClickedRepository.findByPtClickedName(ptinfo);
	}
}
