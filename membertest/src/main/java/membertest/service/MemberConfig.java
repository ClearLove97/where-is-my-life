package membertest.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import membertest.dao.MemberDao;

@Configuration
public class MemberConfig {
	
	private final DataSource dataSource;
	
	public MemberConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService MemberService() {
		return new MemberService(MemberDao());
	}
	
	@Bean
	public MemberDao MemberDao() {
		return new MemberDao(dataSource);
	}
}
