package com.ssafy.yogart.teachers.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.controller.AicoachController;
import com.ssafy.yogart.teachers.model.PtClicked;
import com.ssafy.yogart.teachers.model.PtInfo;
import com.ssafy.yogart.teachers.model.TeacherDetailResult;
import com.ssafy.yogart.teachers.model.TeacherPTInfoResult;
import com.ssafy.yogart.teachers.model.Time;
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
	public ResponseEntity<TeacherDetailResult> teacherPTList(@PathVariable int id) throws Exception {
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
	
	@ApiOperation(value = "강사의 수업 정보와 이미 예약된 PT리스트를 가져온다.", response = TeacherPTInfoResult.class)
	@GetMapping(value="/pt/{ptId}")
	public ResponseEntity<TeacherPTInfoResult> teacherReservedPTList(@PathVariable int ptId) throws Exception {
		System.out.println("ptId:" +  ptId);
		PtInfo ptInfo = teacherService.showPTInfo(ptId);
		List<PtClicked> clickedList = teacherService.showClassTime(ptInfo);
		List<Time> timeList = new ArrayList<>();
		List<LocalDate> soldOut = new ArrayList<>();
		for(int i = 0; i < clickedList.size(); i++) {
			PtClicked temp = clickedList.get(i);
			Time time = new Time(temp.getPtDay(), temp.getPtTime());
			timeList.add(time);
			if(temp.getDateTime() == null) continue;
			soldOut.add(LocalDate.from(temp.getDateTime()));
		}
		TeacherPTInfoResult result = new TeacherPTInfoResult();
		result.setClicked(timeList);
		result.setPtInfo(ptInfo);
		result.setSoldOut(soldOut);
		return new ResponseEntity<TeacherPTInfoResult>(result , HttpStatus.OK);
	}
	
	// 서비스에 맞게 데이터 처리하는지 확인하고 만들 것
	@ApiOperation(value = "신청완료시 수업에 대한 정보와 유저정보 가져온다.", response = String.class)
	@PostMapping(value="/pt-regist")
	public ResponseEntity<String> teacherReserved(@RequestHeader Map<String,String> header, @RequestBody Map<String, String> data) throws Exception {
		String token = header.get("authorization");
		User user = userService.authentication(token);
		/**
		// 일단 제대로 받아온다고 치고
		// 스푼 차감
		user.setUserSpoon(user.getUserSpoon() - data.get("ptInfo").get("ptPrice") );
		String ptName = data.get("ptInfo").get("ptName");
		// 
		PtInfo ptinfo = new PtInfo("name",300,"intro", user);
		// time을 받아서 pt_clicked에  [user.getId() -> student_id, time은 새로 할필요가 없음..!]
		**/			
		return new ResponseEntity<String>(SUCCESS , HttpStatus.OK);
	}

}
