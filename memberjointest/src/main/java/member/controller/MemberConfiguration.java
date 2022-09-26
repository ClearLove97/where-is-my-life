package member.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import member.dao.MemberDao;
import member.service.MemberService;

@Configuration
public class MemberConfiguration {
	private final DataSource dataSource;
	
	@Autowired
	public MemberConfiguration(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Bean
	public MemberService memberService() {
		return new MemberService(memberDao());
	}
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource);
	}
}
