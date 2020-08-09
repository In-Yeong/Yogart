package com.ssafy.yogart.user.service;

import java.util.HashMap;

import com.ssafy.yogart.user.model.KakaoPaymentReady;

public interface KakaoService {

	String getAccessToken(String authorizeCode);
	HashMap<String, Object> getUserProfile (String access_Token);
	KakaoPaymentReady kakaoPayReady(String userNickname, String quantity, String price) throws Exception;
}
