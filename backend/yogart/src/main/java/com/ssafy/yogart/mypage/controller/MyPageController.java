package com.ssafy.yogart.mypage.controller;

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

	@ApiOperation(value = "그래프 기록 데이터를 보낸다", response = String.class)
	@GetMapping(value="/graph")
	public ResponseEntity<String> showResult(@RequestBody Map<String, Object> courseData) throws Exception {
		Map<String, String> headers = (Map<String, String>)courseData.get("headers");
		String token = headers.get("auth-token");
		System.out.println(token);
		User user = userService.authentication(token);
		//
		String nickname = user.getUserNickname();
		
		return new ResponseEntity<String>("응답 어떻게 줄껀지 작성", HttpStatus.OK);
	}
}
