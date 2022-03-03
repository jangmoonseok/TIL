package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	MemberService memberService; 
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterforEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");
		//when
		Long saveId = memberService.join(member);
		//then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () ->  memberService.join(member2));
		
		//then
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
	
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}

}
