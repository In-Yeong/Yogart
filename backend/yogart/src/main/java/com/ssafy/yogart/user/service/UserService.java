package com.ssafy.yogart.user.service;

import com.ssafy.yogart.user.model.User;

public interface UserService {
	// email 중복 체크
	User emailChk(String email);
	// nickname 중복 체크
	User nicknameChk(String nickname);
	
	// 로그인
	User login(String username, String password);
	User login(String email, String loginMethod, String trash);
	// 가입
    User join(String email, String name, String nickname, String password);
    User join(String email, String nickname, String password);
    // 인증 & 개인정보 조회
    User authentication(String token);
    // 비밀번호 변경
    User updateInfo(String token, User user);
    // 탈퇴
    void withdraw(String token);
}
