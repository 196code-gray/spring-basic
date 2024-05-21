package com.spring.basic.aop;

public class Program {

    public static void main(String[] args) {
        Exam exam = new NewExam(1,1,1,1);



        System.out.printf("total is %d\n", exam.total());
    }
}
