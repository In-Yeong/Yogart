package com.ssafy.yogart.aicoach.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.yogart.aicoach.model.AicoachCourse;
import com.ssafy.yogart.aicoach.model.CourseDetailResult;
import com.ssafy.yogart.aicoach.repository.AicoachCourseRepository;
import com.ssafy.yogart.aicoach.service.AicoachService;
import com.ssafy.yogart.mypage.model.GraphBodyPart;
import com.ssafy.yogart.mypage.service.MyPageService;
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
	
	@Autowired
	private MyPageService myPageService;
	
	@ApiOperation(value = "유저의 코스결과를 보낸다.", response = String.class)
	@PostMapping(value="/result")
	public ResponseEntity<String> showResult(@RequestBody Map<String, Object> courseData) throws Exception {
		Map<String, String> headers = (Map<String, String>)courseData.get("headers");
    	String token = headers.get("auth-token");
		System.out.println(token);
		User user = userService.authentication(token);
		
		GraphBodyPart bodypart = new GraphBodyPart();	
		
		String nickname = user.getUserNickname();
		String totalTime = (String)courseData.get("totalTime");
		String startDateTime = (String)courseData.get("totalTime");
		String tagCounting = (String)courseData.get("tagCounting");
		String result = "nickname:" + nickname + "," +
						"totalTime:" + totalTime + "," +
						"startDateTime:" + startDateTime + "," +
						"tagCounting:" + tagCounting;
		
		
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 코스결과를 보낸다.", response = String.class)
	@GetMapping(value="/list/{courseId}")
	public ResponseEntity<CourseDetailResult> getDetail(@PathVariable int courseId) throws Exception {
		System.out.println(courseId);
		AicoachCourse aicoachCourse = aicoachService.detailCourse(courseId);
		String courseName = aicoachCourse.getAiCourseName();
		String course = aicoachCourse.getAiCourseOrder();
		CourseDetailResult result = new CourseDetailResult();
		result.setCourse(course);
		result.setCourseName(courseName);
		return new ResponseEntity<CourseDetailResult>(result, HttpStatus.OK);
	}

    @ApiOperation(value = "유저가 만든 모든 코스를 보낸다.", response = List.class)
	@GetMapping(value="/list")
	public ResponseEntity<List<AicoachCourse>> allMyCourse(HttpServletRequest request) throws Exception {
		logger.debug("allMyCourse - 호출");
		String token = request.getHeader("auth-token");
		User user = userService.authentication(token);
		List<AicoachCourse> mylist = aicoachService.userCourse(user.getUserNickname());	
		return new ResponseEntity<List<AicoachCourse>>(mylist, HttpStatus.OK);
	}
    
    @ApiOperation(value="코스를 만들어서 DB에 저장", response = String.class)
    @PostMapping(value = "/list/create")
    public ResponseEntity<String> create(@RequestBody Map<String, Object> courseData) {
    	Map<String, String> headers = (Map<String, String>)courseData.get("headers");
    	String token = headers.get("auth-token");
    	String coursename = (String)courseData.get("courseName");
    	String posecourse = (String)courseData.get("poseCourse");
    	System.out.println(coursename);
    	User user = userService.authentication(token);
		AicoachCourse aicoachCourse = new AicoachCourse();
    	aicoachCourse.setAiCourseOrder(posecourse);
    	aicoachCourse.setAiCourseName(coursename);
    	aicoachCourse.setAiCourseUserNickname(user);
    	aicoachService.createCourse(aicoachCourse);
    	Integer idx = aicoachcourseRepository.findTopByOrderByAiCourseIdDesc().getAiCourseId();
    	System.out.println(idx);
    	String ID = idx.toString();
        return new ResponseEntity<String>(ID, HttpStatus.OK);
    }
}
