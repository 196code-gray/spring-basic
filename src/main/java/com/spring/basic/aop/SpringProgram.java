package com.spring.basic.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringProgram {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(NewExam.class);

        Exam e = ac.getBean(NewExam.class);

//        Exam e = new NewExam(1,1,1,1);

        System.out.println(e.getClass());
        System.out.printf("total is %d\n", e.total());
        System.out.printf("total is %f\n", e.avg());
    }
}
