package com.example;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/users")
public interface UserClient {
	
	@PostExchange
	UserDto createUser(@RequestBody NewUserDto user);

}
