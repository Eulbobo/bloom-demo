package fr.eulbobo.bloomdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //AOP expression for which methods shall be intercepted
    @Around("execution(* fr.eulbobo.bloomdemo..*(..)))")
    public Object profileAllMethods(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        final String className = methodSignature.getDeclaringType().getSimpleName();
        final String methodName = methodSignature.getName();

        //Measure method execution time
        final long nanoStart = System.nanoTime();
        final Object result = proceedingJoinPoint.proceed();
        final long nanoStop = System.nanoTime();

        final long elapsed = nanoStop - nanoStart;
        //Log method execution time
        LOGGER.info("Execution time of " + className + "." + methodName + " :: " + elapsed + " nanos");

        return result;
    }

}
