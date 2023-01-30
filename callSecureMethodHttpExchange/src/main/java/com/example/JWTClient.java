package com.example;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/")
public interface JWTClient {
	
	@PostExchange("authenticate")
	JwtResponse authenticate(@RequestBody CredentialDto cridentialDto);


}
