package com.kh.portfolio.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gugudan")
public class GuguController {
	private static Logger logger = 
			LoggerFactory.getLogger(GuguController.class);
	//화면
	@GetMapping("/gugudanForm")
	public String gugu() {
		logger.info("gugu()메소드 호출");
		return "gugu";
	}
	//처리
	@PostMapping("")
	public String gugudan( //요청 파라미터 이름과 지역변수명이 같으면 매개값 생략가능
		@RequestParam String dansu, Model model) {
		logger.info("gugudan()메소드 호출");
		model.addAttribute("dansu", dansu);
		
		return "gugu";
	}
}

	