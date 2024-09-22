package com.jung.Jjoin.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jung.Jjoin.user.domain.User;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("email") String email
			,@Param("phoneNumber") String phoneNumber
			,@Param("nickName") String nickName
			,@Param("name") String name
			,@Param("password") String  password);
	
	public int selectCountByEmail(@Param("email") String email);
	
	public User selectUser(
			@Param("email") String email
			, @Param("password") String password);
	
	public User selectUserById(@Param("id") int id);
	 
	
}
