package com.spring.basic.order;

import com.spring.basic.annotaion.MainDiscountPolicy;
import com.spring.basic.discount.DiscountPolicy;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 주문 서비스 역할 구현체
//@RequiredArgsConstructor // Lombok을 이용해서 생성자 자동 생성(ctrl + F12로 확인가능)
public class OrderServiceImpl implements OrderService{
    // 구현체 의존 x
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
