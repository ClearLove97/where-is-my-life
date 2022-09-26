package membertest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import membertest.dao.MemberDao;
import membertest.dto.MemberDto;

@Service
public class MemberService {
	
	private final MemberDao memberDao;
	
	@Autowired
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public String Insert(MemberDto memberDto) {
		memberDao.findById(memberDto.getId())
		.ifPresent(m ->{ throw new IllegalStateException("이미존재하는 아이디입니다.");});
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@123");
		memberDao.insert(memberDto);
		return memberDto.getId();
	}
	
	public List<MemberDto> ShowMember(){
		return memberDao.findByAll();
	}
}
