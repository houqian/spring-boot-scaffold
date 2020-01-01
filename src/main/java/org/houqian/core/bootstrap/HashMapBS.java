package org.houqian.core.bootstrap;

import java.util.HashMap;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-06-18
 */
public class HashMapBS {

  public static void main(String[] args) {
    HashMap<String, String> map = new HashMap<>();

    map.put("1", "y");
    map.put("2", "y");
    map.put("3", "z");

    map.replace("1", "222222");
    System.out.println(map);

    // 如果 key 不存在，则执行后面的函数，非常适合缓存的场景
    map.computeIfAbsent("4", (k) -> {
      // getFromDB
      return "1";

    });

    System.out.println(map);
    // 如果 key 存在，则执行后面的函数
    map.computeIfPresent("1", (k, v) -> {
      return k + "-" + v;
    });
    System.out.println(map);


    char[] chars = "12345".toCharArray();
    System.out.println(chars);
    reverseString(chars);
    System.out.println(chars);
  }

  public static void reverseString(char[] s) {
    int  length = s.length;
    char temp;
    for (int i = 0; i < length / 2; i++) {
      temp = s[i];
      s[i] = s[length - 1 - i];
      s[length - 1 - i] = temp;
    }
  }
}
