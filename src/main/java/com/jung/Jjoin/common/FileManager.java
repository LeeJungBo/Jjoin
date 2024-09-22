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
	
	
	
	
	

}
