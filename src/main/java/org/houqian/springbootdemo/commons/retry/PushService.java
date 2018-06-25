package org.houqian.springbootdemo.commons.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/25
 */
@Slf4j
@Service
public class PushService {

  @Retryable(
          // 捕获异常
          value = Exception.class,
          // 最大重试次数(即一共执行多少次)
          maxAttempts = 3,
          // 重试策略 delay：间隔时间
          backoff = @Backoff(delay = 2000L)
  )
  public boolean push() {
    log.info("start push...");
    throw new RuntimeException("push faild");
  }

  @Recover
  public boolean recoryPush() {
    log.error("push finally faild..");
    return false;
  }


}
