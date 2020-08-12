package com.ssafy.yogart.mypage.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestHeader;
=======
>>>>>>> 82e170df7d6284abdd894ac1819a59e753b665dd
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.controller.AicoachController;
import com.ssafy.yogart.mypage.model.GraphBodyPart;
import com.ssafy.yogart.mypage.model.GraphResult;
import com.ssafy.yogart.mypage.model.GraphTime;
import com.ssafy.yogart.mypage.model.MyPagePtResult;
import com.ssafy.yogart.mypage.model.PtClicked;
import com.ssafy.yogart.mypage.service.MyPageService;
import com.ssafy.yogart.user.model.User;
import com.ssafy.yogart.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/mypage")
public class MyPageController {
	private static final Logger logger = LoggerFactory.getLogger(AicoachController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyPageService myPageService;

	@ApiOperation(value = "그래프 기록 데이터를 보낸다", response = GraphResult.class)
	@GetMapping(value="/graph")
	public ResponseEntity<GraphResult> showResult(HttpServletRequest request) throws Exception {
<<<<<<< HEAD
//		Map<String, String> headers = (Map<String, String>)courseData.get("headers");
//		String token = headers.get("auth-token");
=======
>>>>>>> 82e170df7d6284abdd894ac1819a59e753b665dd
		String token = request.getHeader("auth-token");
		System.out.println(token);
		User user = userService.authentication(token);
		GraphBodyPart tags = myPageService.showTagGraph(user);
		System.out.println(1);
		List<GraphTime> attendance = myPageService.showattendance(user);
		System.out.println(2);
		GraphResult result = new GraphResult();
		Map<Integer, Integer> timeCount = result.getTimeCount();
		for(int i = 0; i < attendance.size(); i++) {
			int hour = attendance.get(i).getGraphDateTime().getHour();
			System.out.println(hour);
			int value = timeCount.get(hour) + 1;
			timeCount.put(hour, value);
		}
		Map<LocalDate, Integer> attendances = new HashMap<>();
		for(int i = 0; i < attendance.size(); i++) {
			attendances.put(LocalDate.from(attendance.get(i).getGraphDateTime()), attendance.get(i).getGraphRunningTime());
		}
		result.setTags(tags);
		result.setAttendance(attendances);
		result.setTimeCount(timeCount);
		return new ResponseEntity<GraphResult>(result, HttpStatus.OK);
	}

	
	@ApiOperation(value = "수업 내역을 보낸다", response = MyPagePtResult.class)
	@GetMapping(value="/ptlist")
	public ResponseEntity<MyPagePtResult> showPtList(HttpServletRequest request) throws Exception {
		String token = request.getHeader("auth-token");
		System.out.println("token:::" + token);
		User user = userService.authentication(token);
		List<PtClicked> Courses = myPageService.showPTList(user);
		List<PtClicked> pastCourses = new ArrayList<>();
		List<PtClicked> todayCourses = new ArrayList<>();
		List<PtClicked> futureCourses = new ArrayList<>();	
		LocalDate local = LocalDate.now();
		PtClicked pt = null;
		for(int i = 0; i < Courses.size(); i++) {
			pt = Courses.get(i);
			if(local.isBefore(pt.getDateTime())) {
				pastCourses.add(pt);
			} else if (local.isAfter(Courses.get(i).getDateTime())) {
				futureCourses.add(pt);
			} else {
				todayCourses.add(pt);
			}
		}
		MyPagePtResult result = new MyPagePtResult();
		result.setPastCourses(pastCourses);
		result.setTodayCourses(todayCourses);
		result.setFutureCourses(futureCourses);
		return new ResponseEntity<MyPagePtResult>(result, HttpStatus.OK);
	}
	
}
