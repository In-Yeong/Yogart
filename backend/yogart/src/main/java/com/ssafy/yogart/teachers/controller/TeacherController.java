package com.ssafy.yogart.teachers.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.controller.AicoachController;
import com.ssafy.yogart.user.model.User;
import com.ssafy.yogart.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	private static final Logger logger = LoggerFactory.getLogger(AicoachController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "강사리스트를 출력", response = List.class)
	@GetMapping(value="/list")
	public ResponseEntity<List<User>> teacherList(@RequestHeader Map<String, String> header) throws Exception {
		String token = header.get("authorization");
		System.out.println("token / " + token);
		User user = userService.authentication(token);
		List<User> teachers = userService.showAllTeacherlist();
		return new ResponseEntity<List<User>>(teachers, HttpStatus.OK);
	}

}
