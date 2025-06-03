package CanditateonboardingSystem.CanditateonboardingSystem.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class loginAspect {

    @Around("execution(* CanditateonboardingSystem.CanditateonboardingSystem.service.*.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("Entering {}.{}() with arguments: {}", className, methodName, joinPoint.getArgs());

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - startTime;

        log.info("Exiting {}.{}() with result: {}. Execution time: {} ms",
                className, methodName, result, elapsedTime);

        return result;
    }
}
