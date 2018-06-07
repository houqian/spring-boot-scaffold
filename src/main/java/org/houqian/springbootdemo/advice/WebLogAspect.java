package org.houqian.springbootdemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

  ThreadLocal<Long> watch = new ThreadLocal<>();

  @Pointcut("execution(public * org.houqian.springbootdemo.controller.*.*(..))")
  public void webLog() {}

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    watch.set(System.currentTimeMillis());
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    // 记录下请求内容
    log.info("IP : " + request.getRemoteAddr());
    log.info("{} | {} | {}} ", request.getMethod(), request.getRequestURL().toString(), Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(returning = "ret", pointcut = "webLog()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    log.info("RESPONSE :{} ", ret);
    log.info("COST :{}ms", (System.currentTimeMillis() - watch.get()));
  }

}