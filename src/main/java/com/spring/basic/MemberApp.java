package com.spring.basic;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberService;
import com.spring.basic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // 직접 테스트를 위해 두 멤버가 같은지 출력으로 확인(눈으로 직접 확인)
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
