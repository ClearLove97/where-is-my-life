package jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	
	@RequestMapping("/")
	public String home() { 	//홈으로 이동
		log.info("home controller");
		return "home";
	}
	
}
