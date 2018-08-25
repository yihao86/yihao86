package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AController {

	@RequestMapping("/login")
	public String login() {
		System.out.println("aaa");
		return "aaa";
	}
}
