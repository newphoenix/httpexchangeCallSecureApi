package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class CallSecureMethodHttpExchangeApplication {

	public static String JWT_TOKEN = null;

	public static void main(String[] args) {
		SpringApplication.run(CallSecureMethodHttpExchangeApplication.class, args);
	}

	@Bean
	UserClient userClient() {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081")
				.filter((request, next) -> next.exchange(withBearerAuth(request))).build();

		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
				.createClient(UserClient.class);
	}	
	
	private static ClientRequest withBearerAuth(ClientRequest request) {		
		return ClientRequest.from(request).header(HttpHeaders.AUTHORIZATION, "Bearer " + JWT_TOKEN).build();
	}

	private void getToken(JWTClient jwtClient) {	

		if (JWT_TOKEN == null) {
			CredentialDto credentials = CredentialDto.builder().username("john").password("justdoit").build();
			JWT_TOKEN = jwtClient.authenticate(credentials).getToken();
		}	
	}
	
	
	@Bean
	JWTClient jwtClient() {
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081")
				.build();

		return HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
				.createClient(JWTClient.class);
	}	
	
    @Bean
    public CommandLineRunner run(JWTClient jwtClient) throws Exception {
        return args -> {
        	getToken(jwtClient);
            System.out.println(JWT_TOKEN);
        };
    }

}
