package com.spring.basic.order;

import com.spring.basic.discount.DiscountPolicy;
import com.spring.basic.discount.FixDiscountPolicy;
import com.spring.basic.discount.RateDiscountPolicy;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberRepository;
import com.spring.basic.member.MemoryMemberRepository;

// 주문 서비스 역할 구현체
public class OrderServiceImpl implements OrderService{

    // 구현체 의존 x
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
