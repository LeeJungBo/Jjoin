package com.jung.Jjoin.user.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jung.Jjoin.common.HashingEncoder;
import com.jung.Jjoin.user.domain.User;
import com.jung.Jjoin.user.repository.UserRepository;

@Service
public class UserService {
	
	
	// spring bean에 등록되어있음(Component)를 통해서
	// DI(Dependency injection) : 의존성 주입 (원래는 객체는 new ~해서 우리가 만들어줘야하는데 스프링에서는 그것을 스프링 내부에 의존하게해서 주입하게한다는 뜻) 
	// Ioc : 제어의 역전(제어를 스프링 프레임에 맡긴다는거)
	private HashingEncoder encoder; // MD5객체든 SHA256객체든 어떤거든지 쓸수 있다. // MD5HashingEncode encoder;
	private UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository, @Qualifier("sha256Hashing") HashingEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	
	public int addUser(
			 String email
			, String phoneNumber
			, String nickName
			, String name
			, String password) {
		
		
		if(nickName == "") {
			nickName += name;
		}
		
		
		String encryptPassword = encoder.encode(password);
		
		
		// String entryptPassword = MD5HashingEncode.encode(password);
		
		return userRepository.insertUser(email, phoneNumber, nickName, name, encryptPassword);
		
		
	}
	
	public Boolean isDuplicateEmail(String email) {
		
		int count = userRepository.selectCountByEmail(email);
		
		if(count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public User getUser(String email, String password) {
		
		String encryptPassword = encoder.encode(password);
		
		return userRepository.selectUser(email, encryptPassword);
	}
	
}
