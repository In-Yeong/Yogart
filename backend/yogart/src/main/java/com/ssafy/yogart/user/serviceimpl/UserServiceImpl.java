package com.ssafy.yogart.user.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.yogart.user.error.AlreadyExistsException;
import com.ssafy.yogart.user.model.User;
import com.ssafy.yogart.user.repository.UserRepository;
import com.ssafy.yogart.user.service.JwtService;
import com.ssafy.yogart.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
	public User login(String email, String password) {
    	User user = userRepository.findByUserEmailAndUserPassword(email, password);
//    	if(user == null)
//    			throw new AlreadyExistsException("Wrong information!");
		return user;
	}
    
    // 가입
    @Override
    public User join(String email, String name, String nickname, String password) {
    	User user = userRepository.findByUserEmail(email);
    	if (user != null)
    		throw new AlreadyExistsException("Duplicate useremail");
        return userRepository.save(new User(email, name, nickname, password));
    }
    
    public User join(String email, String nickname, String loginMethod) {
    	User user = userRepository.findByUserEmailAndLoginMethod(email, loginMethod);
    	if (user != null)
    		throw new AlreadyExistsException("Duplicate useremail");
    	return userRepository.save(new User(email, nickname, loginMethod));
    }

    // 인증 & 개인정보 조회
    @Override
    public User authentication(String token) {
    	if(jwtService.isUsable(token)) {
    		System.out.println("123");
//    		Map<String, Object> result = jwtService.get(token);
    		User userTemp = jwtService.get(token);
//    		User user = userRepository.findByUserEmailAndUserPassword((String)result.get("UserEmail")
//    																,(String)result.get("UserPassword"));
    		User user = userRepository.findByUserEmailAndUserPassword(userTemp.getUserEmail(),userTemp.getUserPassword());
    		return user;
    	}
    	return null;
    }

    // 비밀번호 업데이트
    @Override
    public User updatePassword(String token, String password) {
        User user = this.authentication(token);
        user.setUserPassword(password);
        return userRepository.save(user);
    }

    // 탈퇴
    @Override
    public void withdraw(String token) {
        User user = this.authentication(token);
        userRepository.delete(user);
    }

	@Override
	public User login(String email, String loginMethod, String trash) {
		User user = userRepository.findByUserEmailAndLoginMethod(email, loginMethod);
		return user;
	}
	
	@Override
    public User updateInfo(User user) {
        return userRepository.save(user);
    }

}
