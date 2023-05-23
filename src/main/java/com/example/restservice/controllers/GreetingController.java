package com.example.restservice.controllers;

import com.example.restservice.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s %s! You are a %s";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam Map<String,String> requestParams) {
		String firstName = requestParams.get("firstName");
		String lastName = requestParams.get("lastName");
		String role = requestParams.get("role");
		return new Greeting(counter.incrementAndGet(), String.format(template, firstName, lastName, role));
	}
}
