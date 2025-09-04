package com.example.demo.member;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest2 {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	void test() {
		// role 테이블에 insert
		RoleVO roleVO = new RoleVO();
//		roleVO.
		
//		MemberVO memberVO = new MemberVO();
//		memberVO.setUsername("user");
//		memberVO.setPassword("pw");
//		memberVO.setName("name");
//		memberVO.setEmail("user@asdf.com");
//		
//		List<MemberRoleVO> list = new ArrayList<>();
//		
//		MemberRoleVO memberRoleVO = new MemberRoleVO();
//		memberRoleVO.setRoleNum(2L);
//		
//		
//		
	}

}
