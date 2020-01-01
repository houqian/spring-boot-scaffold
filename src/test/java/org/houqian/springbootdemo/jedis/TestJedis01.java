package org.houqian.springbootdemo.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.sortedset.ZAddParams;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-07-22
 */
public class TestJedis01 {

  public static void main(String[] args) {
    Jedis jedis = new Jedis();

    jedis.zadd("user", 99, "jack", ZAddParams.zAddParams().nx());
  }
}
