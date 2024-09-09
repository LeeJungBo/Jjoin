package com.jung.Jjoin.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {

	public int insertUser(
			@Param("email") String email
			,@Param("phoneNumber") String phoneNumber
			,@Param("nickName") String nickName
			,@Param("name") String name
			,@Param("password") String  password);
	 
	
}
