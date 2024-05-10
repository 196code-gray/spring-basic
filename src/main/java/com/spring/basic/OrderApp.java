package com.spring.basic;

import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemberService;
import com.spring.basic.member.MemberServiceImpl;
import com.spring.basic.order.Order;
import com.spring.basic.order.OrderService;
import com.spring.basic.order.OrderServiceImpl;

// 주문을 실행
public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 2000);

        System.out.println(order);
    }
}
