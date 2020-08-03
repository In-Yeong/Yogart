package com.ssafy.yogart.user.service;

import java.util.HashMap;

public interface KakaoService {

	String getAccessToken(String authorizeCode);
	HashMap<String, Object> getUserProfile (String access_Token);
}
