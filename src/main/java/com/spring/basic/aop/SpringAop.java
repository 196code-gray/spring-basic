package com.spring.basic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAop {

//    @Around("execution(* com.spring..*(..))")S
    @Around("execution(* com.spring.basic.aop..*(..))")
    public void exam(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START = " + joinPoint.toString());

        long end = System.currentTimeMillis();
        long result = end - start;

        System.out.println("END : " + joinPoint.toString() + " " + result + "ms");
    }
}
