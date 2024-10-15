package ru.aston.springtask2.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* ru.aston.springtask2.repository..*(..))")
    public Object logAroundRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object targetMethodResult = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Вызов метода в репозитории: {} выполнен за {} мс", joinPoint.getSignature().toShortString(), timeTaken);

        return targetMethodResult;
    }

    @AfterReturning(pointcut = "execution(* ru.aston.springtask2.service..*(..))", returning = "result")
    public void logAfterReturningService(JoinPoint joinPoint, Object result) {
        log.info("Метод в сервисе: " + joinPoint.getSignature().toShortString() + " вернул: " + result);
    }

    @Before("execution(* ru.aston.springtask2.controller..*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        log.info("Вызов метода в контроллере: " + joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "execution(* ru.aston.springtask2..*(..))", throwing = "ex")
    public void logAfterThrowing(Exception ex) {
        log.error("Произошло исключение: {}", ex.getMessage(), ex);
    }
}
