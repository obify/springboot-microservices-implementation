package com.mycompany.bookingservice;

//import feign.RequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

	@Bean
	public RequestInterceptor requestInterceptor(){
		return requestTemplate -> {
			JwtAuthenticationToken token = (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
			requestTemplate.header("Authorization", "Bearer "+token.getToken().getTokenValue());
		};
	}
}
