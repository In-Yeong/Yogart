package com.ssafy.yogart.mypage.service;

import java.util.List;

import com.ssafy.yogart.mypage.model.GraphBodyPart;
import com.ssafy.yogart.mypage.model.GraphTime;
import com.ssafy.yogart.mypage.model.PtClicked;
import com.ssafy.yogart.user.model.User;

public interface MyPageService {
	GraphBodyPart showTagGraph(User user);
	List<GraphTime> showattendance(User user);
	List<PtClicked> showPTList(User user);
	GraphBodyPart saveTag(GraphBodyPart bodypart);
}
