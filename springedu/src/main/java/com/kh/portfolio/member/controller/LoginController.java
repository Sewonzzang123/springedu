package com.kh.portfolio.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.portfolio.member.svc.MemberSVC;
import com.kh.portfolio.member.vo.MemberVO;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Inject
	MemberSVC memberSVC;

	@GetMapping("/loginForm")
	public String loginForm(
	@RequestParam(value="reqURI",required=false) String reqURI, Model model) {
		model.addAttribute("reqURI", reqURI);
		return "/member/loginForm";
	}

	@PostMapping("/login")
	public String login(@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("reqURI") String reqURI,
			HttpSession session, Model model) {

//		logger.info("String login() 호출");
//		logger.info("id:" + id);
//		logger.info("pw:" + pw);

		// 1)회원 id존재 유무
		MemberVO memberVO = memberSVC.listOneMember(id);
		// 2-1)id가 존재하지 않으면
		if (memberVO == null) {
			model.addAttribute("svr_msg", "회원정보가 없습니다.");
			return"/member/loginForm";
		} else {
			// 2-2)id가 존재하면
			// 3-1)비밀번호가 일치하는 경우
			if (memberVO.getPw().equals(pw)) {
				session.setAttribute("member", memberVO);
				logger.info("로그인성공");
			} else {
				// 3-2)비밀번호가 일치하지 않는 경우
				model.addAttribute("svr_msg", "비밀번호가 일치하지 않습니다.");
				return"/member/loginForm";
			}
		}
		return "redirect:/"+reqURI;
	}
	
	@GetMapping("/logout")
	public String logoutForm(
			HttpSession session) {
		//세션에 저장된 정보 제거
		session.invalidate();
		
		return "redirect:/";
	}

}
