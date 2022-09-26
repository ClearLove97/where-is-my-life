package hello.core.order;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OrderApp {
	
	public static void main(String[] args) {
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
//		OrderService orderService = appConfig.orderService();
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService" , OrderService.class);
		
		Long memberId = 1L;
		Member member = new Member(memberId , "memberA",Grade.VIP);
		memberService.joun(member);
		
		Order order = orderService.createOreder(memberId, "itemA", 100000);
		
		System.out.println(order);
	}
}
