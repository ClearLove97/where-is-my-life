package member.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import member.dao.MemberDao;
import member.dto.MemberDto;

@Transactional
public class MemberService {
	
	private MemberDao memberDao;
	
	@Autowired
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public int join(MemberDto memberDto) {
		Optional<MemberDto> result = memberDao.findByName(memberDto.getName());
		result.ifPresent(m ->{
			throw new IllegalStateException("이미 존재하는 아이디 입니다.");
		});
		memberDao.save(memberDto);
		return memberDto.getId();
	}
	public List<MemberDto> findMember(){
		return memberDao.findByAll();
	}
	
	public Optional<MemberDto> findOne(int id){
		return memberDao.findById(id);
	}
	
}
