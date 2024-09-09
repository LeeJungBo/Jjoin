package com.jung.Jjoin.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Spring bean에 등록하기 위한 annotation
@Component("md5Hashing") // primary 대신 이름을 부여해서 Service에 써줄수 있음 @Qualifier("md5Hashing") 이런식으로 Service에 써줄수 있음(Service클래스에서 봐줄것)
 // @Primary //(의존성 우선순위 부여) 이 어노테이션을 쓰면 이걸 쓸수 있게 된다(명시).(SHA256인터페이스에 넣어도 됨) (Service에서) 그러면 부모인터페이스를 서비스에 써주고 이것을 선택 가능하게 해준다
public class MD5HashingEncode implements HashingEncoder {

	public String encode(String message) {
		
		
		String result = "";
						
		try {														// sha256dl 최신방식
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			
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
