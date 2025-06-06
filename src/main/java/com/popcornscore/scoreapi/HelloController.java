package com.popcornscore.scoreapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {
	
	@GetMapping("api/test")
	public String hello() {
		return "안녕하세요 백엔드입니다.";
	}
}
