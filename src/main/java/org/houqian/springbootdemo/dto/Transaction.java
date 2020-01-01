package org.houqian.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/8/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Transaction {

  private String sender;

  private String recipient;

  private long amount;

  public static void main(String[] args) {

  }
}
