package com.bookstore;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* BookRegistrationServlet.doPost(..))")
    public void logMethodExecution() {
        System.out.println("Method doPost() is about to be executed");
    }
}
