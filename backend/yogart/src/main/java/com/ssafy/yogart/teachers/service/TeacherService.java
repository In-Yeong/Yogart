package com.ssafy.yogart.teachers.service;

import java.util.List;

import com.ssafy.yogart.teachers.model.PtClicked;
import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.user.model.User;

public interface TeacherService {
	
	List<PtInfo> showAllTeacherPTlist(User user);
	
	PtInfo showPTInfo(int ptId);
	
	List<PtClicked> showClassTime(PtInfo ptinfo);
	
	PtClicked updatePtClickedInfo(PtClicked ptClicked);
	
	PtClicked updatePtClickedInfo(int ptDay, int ptTime, PtInfo ptinfo);
}
