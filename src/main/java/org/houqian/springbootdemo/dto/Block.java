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
public class Block {

  /**
   * 索引
   */
  private long index;

  /**
   * Unix时间戳
   */
  private long timestamp;

  /**
   * 工作量证明
   */
  private long proof;

  /**
   * 前一个区块的hash值
   */
  private String previousHash;

  private Transaction[] transactions;

}
