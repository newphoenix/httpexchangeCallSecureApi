package com.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("demo")
@AllArgsConstructor
public class DemoController {
	
	private UserClient userClient;
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody NewUserDto newUserDto){
		return ResponseEntity.ok(userClient.createUser(newUserDto));
	}

}
