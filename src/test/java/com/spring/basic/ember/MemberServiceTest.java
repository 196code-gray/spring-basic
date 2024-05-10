package com.spring.basic.ember;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberService;
import com.spring.basic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// 방금 만든 기능들을 JUint을 이용하여 테스트.
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("회원가입 성공 테스트")
    void join(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
