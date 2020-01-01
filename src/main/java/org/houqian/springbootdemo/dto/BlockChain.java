package org.houqian.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/8/30
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BlockChain {

  private Block[] chain;

  private Transaction[] transactions;

  private Set<String> nodes;
}
