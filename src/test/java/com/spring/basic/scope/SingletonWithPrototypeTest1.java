package com.spring.basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


public class SingletonWithPrototypeTest1 {
/*
ObjectProvider vs Provider

둘 다 기능은 비슷하지만 spring에서 제공해주는 인터페이스가 기능은 더 많음.
다만, 자바 표준 Provider의 경우 라이브러리를 추가해줘야하는 불편함 있음.

spring 외의 다른 곳에서도 동작해야 한다면 자바 표준.
그게 아니라면 기능을 보고 선택하자.
 */
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        Assertions.assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean1.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    public static class ClientBean {
        private final Provider<PrototypeBean> prototypeBeansProvider;

        public ClientBean (Provider<PrototypeBean> prototypeBeanProvider){
            this.prototypeBeansProvider = prototypeBeanProvider;
        }

        public int logic(){
            PrototypeBean object = prototypeBeansProvider.get();
             object.addCount();
            return object.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PRototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Prototype Bean " + this);
        }

    }
}
