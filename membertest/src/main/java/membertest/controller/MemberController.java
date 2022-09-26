package membertest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import membertest.dto.InsertForm;
import membertest.dto.MemberDto;
import membertest.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@GetMapping("/")
	public String home() {
		return "home";
	}
	@GetMapping("/members/new")
	public String createMember() {
		return "/insertform";
	}
	@PostMapping("/members/new")
	public String create(InsertForm insertform) {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setId(insertform.getId());
		memberDto.setName(insertform.getName());
		memberDto.setPassword(insertform.getPassword());
		memberDto.setNumber1(insertform.getNumber1());
		memberDto.setNumber2(insertform.getNumber2());
		memberDto.setNumber3(insertform.getNumber3());
		memberDto.setAddress(insertform.getAddress());
		memberDto.setResident(insertform.getResident());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		memberService.Insert(memberDto);
		return "redirect:/";
	}
	@GetMapping("/members")
	public String showMember(Model model) {
		List<MemberDto> members = memberService.ShowMember();
		model.addAttribute("members",members);
		return "/memberList";
	}
}
