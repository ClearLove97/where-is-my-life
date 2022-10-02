package jpashop;

import jpashop.domain.Member;
import jpashop.repository.MemberRepository;
import jpashop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception{
		//given
		Member member = new Member();
		member.setName("kim");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		assertEquals(member,memberRepository.findMember(saveId));
	}
	
	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		//given
		
		Member member1 = new Member();
		Member member2 = new Member();
		
		member1.setName("kim");
		member2.setName("kim");
		
		//when
		
		memberService.join(member1);
		memberService.join(member1); // 예외가 발생해야 한다.!!
		
		//then
		
		fail("예외가 발생해야 한다.");
		
	}
}
