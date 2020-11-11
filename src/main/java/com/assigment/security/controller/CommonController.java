package com.assigment.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class CommonController {
	@RequestMapping({ "/getCommonMessage" })
	public String firstPage() {
		return "Greet Hello World";
	}

}
