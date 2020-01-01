package org.houqian.springbootdemo.ext;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-04-11
 */
public class Human {

  void say() {
    System.out.println(this.getClass().getCanonicalName());
  }
}
