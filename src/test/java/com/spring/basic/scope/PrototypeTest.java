package com.spring.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    /*
    prototype은 조회시 빈이 생성, 초기화 메서드 실행.
    생성 된 bean 관리 x. 따라서 생성한 bean return 하면 빈 삭제.
    bean을 조회할 때마다 새로운 bean 객체 생성.
    컨테이너에서 bean을 관리하지 않으므로 destroy 메서드 실행 안됨.
    원한다면 클라이언트가 직접 해줘야 함.
     */
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototype1 = " + bean1);
        System.out.println("prototype2 = " + bean2);

        Assertions.assertThat(bean1).isNotSameAs(bean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("ProtoBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("ProtoBean.destroy");
        }
    }
}
