package com.spring.basic.order;

import com.spring.basic.discount.FixDiscountPolicy;
import com.spring.basic.member.Grade;
import com.spring.basic.member.Member;
import com.spring.basic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        /*
        생성자 주입이 아닌 수정자(setter)주입을 사용할 경우
        아래 주석처럼 어떤 의존성 주입을 받고 있는지 일일이 확인이 필요함.
        NEP를 발생시킬 수 있음.

        생성자 주입을 사용하게 될 경우
        final 키워드를 사용해서 무조건 의존성을 주입하게 할 수 있음.
        final 키워드를 사용하게 되면 컴파일 시점에 이미 주입 o
        테스트 하기도 용이.
        의존성 주입시 한번 호출이 보장되며 불변.
         */
//        OrderServiceImpl service = new OrderServiceImpl();
//        service.createOrder(1L, "name", 1000);

        MemoryMemberRepository member = new MemoryMemberRepository();
        member.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl service = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        Order order = service.createOrder(1L, "name", 1000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}