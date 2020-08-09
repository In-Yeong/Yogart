package com.ssafy.yogart.aicoach.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.model.Aicoach;
import com.ssafy.yogart.aicoach.model.AicoachCourse;
import com.ssafy.yogart.aicoach.model.CourseDetailResult;
import com.ssafy.yogart.aicoach.repository.AicoachCourseRepository;
import com.ssafy.yogart.aicoach.service.AicoachService;
import com.ssafy.yogart.user.model.User;
import com.ssafy.yogart.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/aicoach")
public class AicoachController {
	
	private static final Logger logger = LoggerFactory.getLogger(AicoachController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private AicoachService aicoachService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AicoachCourseRepository aicoachcourseRepository;
	
	// test용
	@ApiOperation(value = "유저의 코스결과를 보낸다.", response = String.class)
	@GetMapping(value="/result")
	public ResponseEntity<String> showResult(@RequestBody Map<String, Object> courseData) throws Exception {
		Map<String, String> headers = (Map<String, String>)courseData.get("headers");
    	String token = headers.get("auth-token");
		System.out.println(token);
		User user = userService.authentication(token);
		String nickname = user.getUserNickname();
		String totalTime = (String)courseData.get("totalTime");
		String startDateTime = (String)courseData.get("totalTime");
		String tagCounting = (String)courseData.get("tagCounting");
		String result = "nickname: " + nickname + ", " +
						"totalTime: " + totalTime + ", " +
						"startDateTime: " + startDateTime + ", " +
						"tagCounting" + tagCounting;
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 코스결과를 보낸다.", response = String.class)
	@GetMapping(value="/list/{courseId}")
	public ResponseEntity<CourseDetailResult> getDetail(@PathVariable int courseId) throws Exception {
		System.out.println(courseId);
		AicoachCourse aicoachCourse = aicoachService.detailCourse(courseId);
		String courseName = aicoachCourse.getAiCourseDetailName();
		String course = String.format("%d,%d,%d,%d,%d,%d,%d"
				, aicoachCourse.getC1(),aicoachCourse.getC2(),aicoachCourse.getC3()
				, aicoachCourse.getC4(),aicoachCourse.getC5(),aicoachCourse.getC6()
				, aicoachCourse.getC7());
		CourseDetailResult result = new CourseDetailResult();
		result.setCourse(course);
		result.setCourseName(courseName);
		return new ResponseEntity<CourseDetailResult>(result, HttpStatus.OK);
	}

    @ApiOperation(value = "유저가 만든 모든 코스를 보낸다.", response = List.class)
	@GetMapping(value="/list")
	public ResponseEntity<List<AicoachCourse>> allMyCourse(@RequestParam String token) throws Exception {
		logger.debug("allMyCourse - 호출");
		System.out.println(token);
		User user = userService.authentication(token);
		String nickname = user.getUserNickname();
		List<Aicoach> nums = aicoachService.getNo(nickname);
		List<AicoachCourse> mylist = new ArrayList<>();
		for(int i = 0; i < nums.size(); i++) {
			mylist.add(aicoachService.detailCourse(nums.get(i).getAiCourseId()));			
		}
		return new ResponseEntity<List<AicoachCourse>>(mylist, HttpStatus.OK);
	}
    
    @ApiOperation(value="코스를 만들어서 DB에 저장", response = String.class)
    @PostMapping(value = "/list/create")
    public ResponseEntity<String> create(@RequestBody Map<String, Object> courseData) {
    	Map<String, String> headers = (Map<String, String>)courseData.get("headers");
    	String token = headers.get("auth-token");
    	String coursename = (String)courseData.get("courseName");
    	String posecourse = (String)courseData.get("poseCourse");
    	System.out.println(token);
    	System.out.println(coursename);
    	User user = userService.authentication(token);
		String nickname = user.getUserNickname();
		System.out.println(nickname);
		AicoachCourse aicoachCourse = new AicoachCourse();
		Aicoach aicoach = new Aicoach();
		String[] list = posecourse.split(",");
		String[] list2 = new String[7];
		System.out.println(list.length);
    	for(int i = 0; i < list.length; i++) {
    		list2[i] = list[i];
    	}
    	for(int j = list.length; j < 7; j++) {
    		list2[j] = "1000";
    	}
    	aicoach.setAiCourseUserNickname(nickname);
    	aicoachCourse.setC1(Integer.parseInt(list2[0]));
    	aicoachCourse.setC2(Integer.parseInt(list2[1]));
    	aicoachCourse.setC3(Integer.parseInt(list2[2]));
    	aicoachCourse.setC4(Integer.parseInt(list2[3]));
    	aicoachCourse.setC5(Integer.parseInt(list2[4]));
    	aicoachCourse.setC6(Integer.parseInt(list2[5]));
    	aicoachCourse.setC7(Integer.parseInt(list2[6]));
    	aicoachCourse.setAiCourseDetailName(coursename);
    	aicoachCourse.setAiCourseDetailNo(aicoach);
    	aicoachService.createCourse(aicoachCourse);
    	System.out.println("fail?");
    	Integer idx = aicoachcourseRepository.findTopByOrderByAiCourseDetailIdDesc().getAiCourseDetailId();
    	System.out.println(idx);
    	String detailIdx = idx.toString();
        return new ResponseEntity<String>(detailIdx, HttpStatus.OK);
    }
}
