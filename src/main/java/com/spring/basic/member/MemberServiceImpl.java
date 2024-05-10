package com.spring.basic.member;

public class MemberServiceImpl implements MemberService{
    /*
     fixme : 문제점 발생
     현재 MemberServiceImpl은 MemberRepository 역할도 알고있고, 구현체인 MemoryMemberRepository 도 알고 있다.
      객체지향 원칙 중 DIP 원칙 위반.
      한마디로 역할만 알면 되는데 구현체까지 현재 의존중.
     */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
