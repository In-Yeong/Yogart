package com.ssafy.yogart.teachers.service;

import java.util.List;

import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.user.model.User;

public interface TeacherService {
	
	List<PtInfo> showAllTeacherPTlist(User user);
}
