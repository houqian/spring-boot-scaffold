package org.houqian.springbootdemo.ext;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-04-11
 */
public class Main {
  public static void main(String[] args) {
    Human human = new Boy();
    human.say();

    Human human2 = new Human();
    human2.say();
  }
}
