package member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import member.dto.MemberDto;
import member.dto.MemberForm;
import member.service.MemberService;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/") // 메인화면
	public String home() {
		return "home";
	}
	
	@GetMapping("/members/new") //회원가입 폼으로 이
	public String createMemberForm(MemberDto memberDto) {
		return "/createMemberForm";
	}
	
	@GetMapping("/members")	// 회원리스트표시
	public String memberList(Model model) {
		List<MemberDto> members = memberService.findMember();
		model.addAttribute("members", members);
		return "/memberList";
	}
	
	@PostMapping("/member/new")
	public String create(MemberForm form) {
		MemberDto member = new MemberDto();
		member.setName(form.getName());
		memberService.join(member);
		return "redirect:/";
	}
}
