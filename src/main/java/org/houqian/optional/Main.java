package org.houqian.optional;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019-06-17
 */
public class Main {

  public static void main(String[] args) {

    ArrayList<String> list = new ArrayList<>(2);
    list.add("1");
    list.add("2");
    list.add("3");
    list.add("4");
    System.out.println("4的位置：" + list.indexOf("4"));
    list.remove("3");
    System.out.println("4的位置：" + list.indexOf("4"));


  }

  static class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
      return car;
    }
  }

  static class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
      return insurance;
    }
  }

  static class Insurance {
    private String name;

    public String getName() {
      return name;
    }
  }


}
