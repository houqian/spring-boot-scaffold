package org.houqian.springbootdemo.advice;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    try {
      //Referenced from logging configuration.
      String requestId = String.format("[%s]", requestId());
      MDC.put("mdcData", requestId);
      chain.doFilter(request, response);
    } finally {
      MDC.clear();
    }
  }

  private String requestId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void destroy() {
  }
}