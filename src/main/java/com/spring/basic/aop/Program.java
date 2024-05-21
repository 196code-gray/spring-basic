package com.spring.basic.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Program {

    public static void main(String[] args) {
        Exam exam = new NewExam(1,1,1,1);

        /*
        자바에서 지원해주는 Proxy 라이브러리를 사용해서 Proxy 객체 생성가능.
        Proxy.newProxyInstance에는 매개변수로 아래 주석과 같은 정보가 들어가야 한다.

        현재 exam 클래스에서 메서드를 실행할 때 실행시 시간, 끝나는 시간을 측정하고 싶음.
        시간 측정은 주요 기능이 아닌 부가 기능으로 관심외의 기능.
        그래서 이 부분을 분리.
        Proxy를 이용해서 Exam의 구현 클래스의 가짜 객체를 만들어주고,
        그 객체는 기존 구현 클래스와 똑같이 생겼기 때문에 기존 구현 클래스의 기능을 똑같이 사용하는 것처럼 느낄 수 있음.
         */
//        Exam proxy = Proxy.newProxyInstance(loader, interfaces, h);
        Exam proxy = (Exam) Proxy.newProxyInstance(NewExam.class.getClassLoader(),
                new Class[]{Exam.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        long start = System.currentTimeMillis();

                        /*
                        invoke 메서드를 통해서 매개변수로 넘어온 method, args를 통해 해당 메서드를 호출하면서 핵심 기능 실행.
                        핵심 기능 실행 후 다시 돌아와 부가 기능 실행.
                        method에 넘어오는 method는 proxy 인스턴스에서 호출된 인터페이스 메소드에 해당하는 메서드.
                         */
                        Object result = method.invoke(exam, args);

                        long end = System.currentTimeMillis();
                        String massage = (end - start) + "ms 시간";

                        System.out.println(massage);

                        return result;
                    }
                });

        System.out.printf("total is %d\n", proxy.total());
        System.out.printf("total is %f\n", proxy.avg());
    }
}
