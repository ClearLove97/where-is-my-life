package jpabook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.domain.Member;
import jpabook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	//회원 가입
	@Transactional
	public Long join(Member member) {
		
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) { // 중복된 이름이면 오류메세지 를 보낸다.
		//EXCPTION
		List<Member> findMembers = memberRepository.findByName(member.getName()); /* 멀티쓰레드를 대비하기위해 findByName 안에 member.getName() 을 유니크 제약조건 으로 잡아주는게 좋다*/
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원 입니다.");
		}
	}
	
	//회원 전체 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
