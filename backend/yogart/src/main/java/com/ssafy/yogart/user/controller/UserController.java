package com.ssafy.yogart.user.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.yogart.user.model.KakaoLoginRequest;
import com.ssafy.yogart.user.model.KakaoPaymentApproval;
import com.ssafy.yogart.user.model.KakaoPaymentReady;
import com.ssafy.yogart.user.model.Result;
import com.ssafy.yogart.user.model.User;
import com.ssafy.yogart.user.service.JwtService;
import com.ssafy.yogart.user.service.KakaoService;
import com.ssafy.yogart.user.service.NaverService;
import com.ssafy.yogart.user.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private NaverService naverService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    private static int RECENT_TOTAL_AMOUNT;
    private static String RECENT_TID;
    
    //로그인
    @ApiOperation(value = "로그인")
    @PostMapping(value = "/login")
    public ResponseEntity<Result> login(@RequestBody User loginData) {
    	String email = loginData.getUserEmail();
    	String password = loginData.getUserPassword();
    	// jwtServiceImpl -> create  메서드 이용해서 토큰 생성
    	System.out.println(email + "  " + password);
    	ResponseEntity<Result> response = null;
    	Result result = Result.successInstance();
    	User user = userService.login(email, password);
    	if(user == null) {                                   
    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	} else {
    		String token = jwtService.create("user", user, email);
    		System.out.println(token);
    		result.setToken(token);
    		result.setUser(user);
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    	}
    	
        return response;
    }
    
    @ApiOperation(value="카카오로그인")
    @PostMapping(value="/kakaoLogin")
    public ResponseEntity<Result> kakaoLogin(@RequestBody KakaoLoginRequest req) {
//    	System.out.println("KakaoLoginRequest : " + req);
    	String accessToken = req.getAccess_token();
    	HashMap<String, Object> userProfile = kakaoService.getUserProfile(accessToken);
    	String nickname = (String)userProfile.get("nickname");
    	String email = (String)userProfile.get("email");
//    	System.out.println("nickname : " + nickname + " / email : " + email);
    	// jwtServiceImpl -> create  메서드 이용해서 토큰 생성
    	ResponseEntity<Result> response = null;
    	Result result = Result.successInstance();
    	User user = userService.login(email, "kakao", "");
    	if(user == null) {                                   
        	user = new User(email, nickname, "kakao");
        	result.setUser(user);
        	userService.join(email, nickname, "kakao");
    	} 
    		String token = jwtService.create("user", user, email);
    		System.out.println(token);
    		result.setToken(token);
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    	
        return response;
    }
    
    @ApiOperation(value="네이버로그인")
    @GetMapping(value="/naverLogin")
    public ResponseEntity<Result> naverLogin(@RequestParam String code, @RequestParam String state) {	
//    	System.out.println("Code : " + code + " / State : " + state);
    	ResponseEntity<Result> response = null;
    	try {
    		String accessToken = naverService.getTokenInfo(code, state).get("access_token");
//    		System.out.println("accessToken : " + accessToken);
    		HashMap<String, String> userProfile = naverService.getUserProfile(accessToken);
    		String nickname = userProfile.get("nickname");
    		String email = userProfile.get("email");
//    		System.out.println("nickname : " + nickname + " / email : " + email);
        	Result result = Result.successInstance();
        	User user = userService.login(email, "naver", "");
        	if(user == null) {                                   
            	user = new User(email, nickname, "naver");
            	result.setUser(user);
            	userService.join(email, nickname, "naver");
        	} 
        		String token = jwtService.create("user", user, email);
        		System.out.println(token);
        		result.setToken(token);
        		response = new ResponseEntity<>(result, HttpStatus.OK);
        	
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	return response;
    }

 // 사용자 username과 password을 입력받아 새로운 User를 생성하고 그 결과를 반환
    @ApiOperation(value="회원가입", response = User.class)
    @PostMapping(value = "/signup")
    public ResponseEntity<Result> create(@RequestBody User signupData) {
    	String email = signupData.getUserEmail();
    	String username = signupData.getUserName();
    	String nickname = signupData.getUserNickname();
    	String password = signupData.getUserPassword();
    	User user = new User(email, username, nickname, password);
    	Result result = Result.successInstance();
    	result.setUser(user);
    	System.out.println(email + ", " 
    					+ username + ", " 
    					+ nickname + ", " 
    					+ password);
    	userService.join(email, username, nickname, password);
    	String token = jwtService.create("user", user, email);
		System.out.println(token);
		result.setToken(token);
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }

    // 자신의 정보를 반환
    @ApiOperation(value="자신의 정보를 반환")
    @PostMapping(value = "/myinfo")
    public ResponseEntity<Result> getMe(HttpServletRequest request) {
    	ResponseEntity<Result> response = null;
    	Cookie[] myCookies = request.getCookies();
    	System.out.println("token: " + myCookies[0].getValue());
    	Result result = Result.successInstance();
    	User user = userService.authentication(myCookies[0].getValue());
    	if(user == null) {
    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	} else {
    		result.setUser(user);
    		response = new ResponseEntity<>(result, HttpStatus.OK);
    	}
        return response;
    }

    // 자신의 비밀번호를 갱신한 뒤 그 결과를 반환
    @ApiOperation(value="자신의 비밀번호를 갱신한 뒤 그 결과를 반환", response = User.class)
    @PutMapping(value = "/myinfo")
    public User updatePassword(@RequestHeader String authorization, @RequestParam String password) {
        return userService.updatePassword(authorization, password);
    }

    // 탈퇴
    @ApiOperation(value="탈퇴", response = String.class)
    @DeleteMapping
    public void withdraw(@RequestHeader String authorization) {
        userService.withdraw(authorization);
    }
    
    @ApiOperation(value="이미지 업로드")
    @PostMapping(value = "/imageUpload")
    public ResponseEntity<Result> imageUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
;
    	System.out.println(files.length);
    	ResponseEntity<Result> response;
    	for(MultipartFile file : files)
    	{
    		String fileName = file.getOriginalFilename();
    		System.out.println(fileName);
//    		File dest = new File(request.getServletContext().getRealPath("/") + fileName);
    		System.out.println(request.getServletContext().getRealPath("/"));
    		try {
				save(file, request.getServletContext().getRealPath("/"));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
    	}
    	Result result = Result.successInstance();
    	response = new ResponseEntity<>(result, HttpStatus.OK);
    	return response;
    }
    
    @ApiOperation(value="스푼 결제")
    @PostMapping(value = "/pay")
    public ResponseEntity<KakaoPaymentReady> chargeSpoon(@RequestHeader String authorization, 
    		@RequestBody Map<String, Integer> purchaseData) {
    	
    	int quantity = purchaseData.get("quantity");
    	int price = purchaseData.get("price");
    	User currentUser = userService.authentication(authorization);
    	String userNickname = currentUser.getUserNickname();
    	KakaoPaymentReady paymentInfo = null;
    	ResponseEntity<KakaoPaymentReady> response;
    	
    	try {
			paymentInfo = kakaoService.kakaoPayReady(userNickname, quantity, price);
			response = new ResponseEntity<>(paymentInfo, HttpStatus.OK);
			RECENT_TOTAL_AMOUNT = price;
			RECENT_TID = paymentInfo.getTid();
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
    	
    	return response;
    }
    
    @ApiOperation(value="스푼 결제 승인 정보")
    @PostMapping(value = "/paymentSuccess")
    public ResponseEntity<KakaoPaymentApproval> paymentSuccess(@RequestHeader String authorization, 
    		@RequestBody Map<String, String> paymentData) {
    	
    	String pgToken = paymentData.get("pgToken");
    	User currentUser = userService.authentication(authorization);
    	String userNickname = currentUser.getUserNickname();
    	KakaoPaymentApproval approvalInfo = null;
    	ResponseEntity<KakaoPaymentApproval> response;
    	
    	try {
			approvalInfo = kakaoService.kakaoPaySuccess(userNickname, RECENT_TID, pgToken, RECENT_TOTAL_AMOUNT);
			response = new ResponseEntity<>(approvalInfo, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
    	
    	return response;
    }
    
    private String save(MultipartFile file, String contextPath) {
        try {
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
           String newFileName = simpleDateFormat.format(new Date()) + file.getOriginalFilename();
           byte[] bytes = file.getBytes();
           //윈도우에서는 폴더가 없으면 생성이안됨
           Path path = Paths.get(contextPath + newFileName);
           Files.write(path,bytes);
           return newFileName; 
           
        } catch (Exception e) {
        	e.printStackTrace();
           return null;
        }
     }
}
