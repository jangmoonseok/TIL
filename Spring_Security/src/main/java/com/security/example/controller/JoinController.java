package com.security.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.example.dto.JoinDTO;
import com.security.example.service.JoinService;

@Controller
public class JoinController {

	@Autowired
	private JoinService joinService;
	
	@GetMapping("/join")
	public String joinPage() {
		return "join";
	}
	
	@PostMapping("joinProc")
	public String joinProcess(JoinDTO joinDTO) {
		joinService.joinProcess(joinDTO);
		
		return "redirect:/login";
	}
}
