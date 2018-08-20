package com.pmo.basicauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class PMOcontroller 
{
	@RequestMapping("/hello")
	private String hello() {
		return "Hello World";
	}
	
	@RequestMapping("/secure")
	private String secure() {
		return "Secure Hello";
	}
}
