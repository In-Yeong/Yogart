package com.ssafy.yogart.teachers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.controller.AicoachController;
import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.teachers.model.TeacherDetailResult;
import com.ssafy.yogart.teachers.service.TeacherService;
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
	private TeacherService teacherService;
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "강사리스트를 출력", response = List.class)
	@GetMapping(value="/list")
	public ResponseEntity<List<User>> teacherList() throws Exception {
		List<User> teachers = userService.showAllTeacherlist();
		return new ResponseEntity<List<User>>(teachers, HttpStatus.OK);
	}
	
	@ApiOperation(value = "강사가 가능한 시간들를 출력", response = List.class)
	@GetMapping(value="/list/detail/{id}")
	public ResponseEntity<TeacherDetailResult> teacherPTList(/**@RequestHeader Map<String,String> header,**/ @PathVariable int id) throws Exception {
//		String token = header.get("authorization");
//		User user = userService.authentication(token);
		System.out.println("id:" +  id);
		User teacher = userService.findUser(id);
		System.out.println(teacher.getUserName());
		List<PtInfo> teachersPT = teacherService.showAllTeacherPTlist(teacher);
		System.out.println("Teacher's PT Class: " + teachersPT);
		TeacherDetailResult result = new TeacherDetailResult();
		result.setPtList(teachersPT);
		result.setTeacherInfo(teacher);
		return new ResponseEntity<TeacherDetailResult>(result , HttpStatus.OK);
	}

}
