package com.spring.basic;

import com.spring.basic.discount.DiscountPolicy;
import com.spring.basic.discount.FixDiscountPolicy;
import com.spring.basic.member.MemberService;
import com.spring.basic.member.MemberServiceImpl;
import com.spring.basic.member.MemoryMemberRepository;
import com.spring.basic.order.OrderService;
import com.spring.basic.order.OrderServiceImpl;

// 애플리케이션 전체를 관리하는 클래스(역할)
public class AppConfig {

    /*
    MemberService에서 직접 구현체를 생성하는 것이 아닌 생성자를 통해서 AppConfig이 주입을 시켜줌.
    OrderService도 마찬가지.

    이렇게 해줌으로서 MeberService도 OrderService도 직접 구현체를 생성하는 것이 아닌 생성자를 통해 구현체를 주입받는 형식이 됨.
    의존 또한 인터페이스에만 의존하고 있음.
     */
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
