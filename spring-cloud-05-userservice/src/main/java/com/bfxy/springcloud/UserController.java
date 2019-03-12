package com.bfxy.springcloud;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

	
	@RequestMapping(value="/user")
	public String user() throws Exception {
		return "---------user---------";
	}
	
	@RequestMapping(value="/coupon")
	public String coupon(@RequestHeader("token") String token, @RequestHeader("level") String level) throws Exception {
		System.err.println("token: " + token  + ", user level : " + level);
		return "-------get coupon!---------";
	}
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file){
		try {
			System.err.println("文件名称: " + file.getOriginalFilename() + ", 文件大小:" + file.getSize());
			FileUtils.writeByteArrayToFile(new File("d:/" + file.getOriginalFilename()), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	
}
