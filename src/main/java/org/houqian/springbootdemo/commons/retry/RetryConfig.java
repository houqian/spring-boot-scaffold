package org.houqian.springbootdemo.commons.retry;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 基于注解的spring-retry提供了重试机制，非常实用
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/25
 */
@Configuration
@EnableRetry
public class RetryConfig {
}
