package com.jung.Jjoin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Jjoin.user.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;



	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	@SuppressWarnings("null")
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("email") String email,
			@RequestParam("phoneNumber") String phoneNumber
			,@RequestParam("nickName") String nickName
			,@RequestParam("name") String name
			,@RequestParam("password") String password){

		if(nickName == null) {
			nickName.equals(name);
		}
		
		int count = userService.addUser(email, phoneNumber, nickName, name, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
}
