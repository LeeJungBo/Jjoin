package com.jung.Jjoin.user.Service;

import org.springframework.stereotype.Service;

import com.jung.Jjoin.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public int addUser(
			 String email
			, String phoneNumber
			, String nickName
			, String name
			, String password) {
		
		
		
		return userRepository.insertUser(email, phoneNumber, nickName, name, password);
		
		
	}
	
}
