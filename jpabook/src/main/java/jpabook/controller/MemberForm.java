package jpabook.controller;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	
	@NotEmpty( message = "회원이름은 필수가 아닙니다.")
	//스프링 부트 2.3 부터는 build.gradle에 다른 코드를 추가해야 한다.
	private String name;
	
	private String city;
	private String street;
	private String zipcode;
}
