package com.auth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVCController 
{
	@RequestMapping("/login")
	public String loginPage()
	{
		return "login";
	}

}
