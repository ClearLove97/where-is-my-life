package jpabook.service;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.domain.Member;
import jpabook.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception{
		//given < 이런게 주어졌을떄 >
		Member member = new Member();
		member.setName("kim");
		
		//when < 이런걸 통해서 >
		Long saveId = memberService.join(member);
		
		// then < 이렇게 결과가 나와야됨 >
		// em.flush(); 쿼리를 보고싶을떄;
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception{
		//given
		Member member1 = new Member();
		member1.setName("kim1");
		
		Member member2 = new Member();
		member2.setName("kim1");
		
		//when
		memberService.join(member1);
		memberService.join(member2);
		
		//then
		
		fail("예외가 발생해야 한다.");
	}
}
