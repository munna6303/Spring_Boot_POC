package com.auth.server.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class AuthController 
{
	
	@RequestMapping("/secure")
	private Principal secure(Principal principal) {
		return principal;
	}
	
}
