package org.houqian.springbootdemo.exception;

import lombok.Data;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/21
 */

@Data
public class BizException extends RuntimeException {

  private String message;
  private Integer code;

  public BizException() {}


}
