package com.jung.Jjoin.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jung.Jjoin.user.Service.UserService;
import com.jung.Jjoin.user.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;



	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("email") String email,
			@RequestParam("phoneNumber") String phoneNumber
			,@RequestParam("nickName") String nickName
			,@RequestParam("name") String name
			,@RequestParam("password") String password){

	
		
		int count = userService.addUser(email, phoneNumber, nickName, name, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(count == 1) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@GetMapping("/duplicate-email")
	public Map<String, Boolean> isDuplicateEmail(@RequestParam("email") String email) {
		
		boolean isDuplicate= userService.isDuplicateEmail(email);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isDuplicate", isDuplicate);
		
		return resultMap;
		
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("email") String email
			,@RequestParam("password") String password
			, HttpServletRequest request){
		
		User user = userService.getUser(email, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			resultMap.put("result", "success");
			// 이때 로그인된 정보는 다른 화면으로 이동될때에도 계속해서 유지되어야 한다.
			// 유지할수 있게 정보를 계속해서 잡고있다.
			HttpSession session= request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("profileImage", user.getProfileImage());
			session.setAttribute("nickName", user.getNickName());
		
		}else {
		
			resultMap.put("result", "fail");
		
		}
		
		return resultMap;
	}
	
	
}
