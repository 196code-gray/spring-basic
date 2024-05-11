package com.spring.basic.order;

import com.spring.basic.discount.DiscountPolicy;
import com.spring.basic.discount.FixDiscountPolicy;
import com.spring.basic.discount.RateDiscountPolicy;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberRepository;
import com.spring.basic.member.MemoryMemberRepository;

// 주문 서비스 역할 구현체
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 고정 할인에서 정률 할인으로 변경
    /*
    의존 관계이기 때문에 고정 할인을 의존하고 있던 OrderServiceImpl의 로직을 변경해야 함.
    DIP원칙을 지키려면 아래와 같이 구현체를 없애면 됨.
    하지만 말이 안됨.
    그럼 어떻게 지킬 수 있는가?
     */
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
