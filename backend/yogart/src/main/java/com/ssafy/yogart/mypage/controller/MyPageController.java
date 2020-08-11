package com.ssafy.yogart.mypage.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.controller.AicoachController;
import com.ssafy.yogart.mypage.model.GraphBodyPart;
import com.ssafy.yogart.mypage.model.GraphResult;
import com.ssafy.yogart.mypage.model.GraphTime;
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

	@ApiOperation(value = "그래프 기록 데이터를 보낸다", response = String.class)
	@GetMapping(value="/graph")
	public ResponseEntity<GraphResult> showResult(@RequestBody Map<String, Object> courseData) throws Exception {
		Map<String, String> headers = (Map<String, String>)courseData.get("headers");
		String token = headers.get("auth-token");
		System.out.println(token);
		User user = userService.authentication(token);
		GraphBodyPart tags = myPageService.showTagGraph(user);
		List<GraphTime> attendance = myPageService.showattendance(user);
		GraphResult result = new GraphResult();
		Map<Integer, Integer> timeCount = result.getTimeCount();
		for(int i = 0; i < attendance.size(); i++) {
			int hour = attendance.get(i).getGraphDateTime().getHour();
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
}
