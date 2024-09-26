package com.jung.Jjoin.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	
	
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\이중보\\Desktop\\코딩\\springProject\\upload\\JJoin";
	
	public static String saveFile(int userId, MultipartFile file){
	
		String directoryName = "/" + userId + "_" + System.currentTimeMillis(); // "/" 를 붙여주는 이유는 JJoin폴더와 구분하기 위해
		
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			return null;
		}
		
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		
		try {
			
			byte[] bytes = file.getBytes();
		Path path = Paths.get(filePath);
			Files.write(path, bytes); // 실제 경로에 bytes를 저장한다.
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
		
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	public static boolean removeFile(String filePath) { // filePath는 현재 이경로 '/images/2_8120980/test.png' 이때 image는 임의로 붙여놓은거 즉, 떼야한다.
		
		if(filePath == null) {
			return false;
		}
		
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images","");
		
		Path path = Paths.get(fullFilePath); // 위 문자열을 객체로 파일의 경로를 가져온거
		
		Path directoryPath = path.getParent(); // path의 상위 객체를 가져온거 즉, 파일(path)이 들어 있는 폴더(path의 상위 객체)
		
		if(Files.exists(path) && Files.exists(directoryPath)) {
			try {
				Files.delete(path);
				Files.delete(directoryPath);
			} catch (IOException e) {
			
				return false;
				
			}
			
			return true;
			
		}else {
			
			return false;
		
		}
		
		
		
	}
	
	
	
	
	

}
