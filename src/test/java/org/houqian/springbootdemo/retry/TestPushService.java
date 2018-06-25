package org.houqian.springbootdemo.retry;

import org.houqian.springbootdemo.commons.retry.PushService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPushService {

  @Autowired
  private PushService pushService;

  @Test
  public void testPush() {
    pushService.push();
  }
}
