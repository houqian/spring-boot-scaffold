package org.houqian.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2019/12/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RenbaoRiskControlDTO {

  private String merchNo;

  private String instNo;

  private String renbaoApplyStatus;

  private String failReason;

  private String code;
}
