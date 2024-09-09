package com.jung.Jjoin.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component("sha256Hashing")
public class SHA256HashingEncoder implements HashingEncoder {
// MD5보다 더 보안성이 뛰어남
	
	public String encode(String message) {
		
		String result = "";
		
		try {														// sha256dl 최신방식
			MessageDigest messageDigest = MessageDigest.getInstance("sha256");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			for(int i = 0; i < digest.length; i++) {
				
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return null;
			// null이 발생할수 있는 타이밍은 결국 MessageDigest messageDigest = MessageDigest.getInstance("md5"); 얘가 밑에 밑줄이 쳐쪗을때 예외상황을 가질때 이것을 넣는다는거
			// 결국 MessageDigest messageDigest = MessageDigest.getInstance("md5"); 결국 얘만 쳐다보면 된다는거
		}
		
		return result;
		
		
		
	}
	
	
	
}
