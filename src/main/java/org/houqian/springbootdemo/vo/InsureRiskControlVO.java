package org.houqian.springbootdemo.vo;

import io.swagger.annotations.ApiModelProperty;
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
public class InsureRiskControlVO {

  @ApiModelProperty(value = "资产商户号", required = true, notes = "资产商户号")
  private String merchNo;

  @ApiModelProperty(value = "资金机构编号", required = true, notes = "资金机构编号")
  private String instNo;

  @ApiModelProperty(value = "风控结果状态", required = true, notes = "风控结果状态")
  private String applyStatus;

  @ApiModelProperty(value = "审核失败原因", required = false, notes = "审核失败原因")
  private String failReason;

  @ApiModelProperty(value = "审核失败编码", required = false, notes = "审核失败编码")
  private String failCode;

  public enum ApplyStatusEnum {

    /**
     * 成功
     */
    SUCCESS("0201", "S"),
    /**
     * 失败
     */
    FAILD("0202", "F"),
    /**
     * 处理中
     */
    PROCESSING("0203", "W"),
    ;

    public static String getInsureStatusByRenbaoStatus(String renbaoStatus) {
      for (ApplyStatusEnum value : ApplyStatusEnum.values()) {
        if (value.renbaoStatus.equals(renbaoStatus)) {
          return value.insureStatus;
        }
      }
      return null;
    }

    ApplyStatusEnum(String insureStatus, String renbaoStatus) {
      this.insureStatus = insureStatus;
      this.renbaoStatus = renbaoStatus;
    }

    private String insureStatus;
    private String renbaoStatus;

    public String getRenbaoStatus() {
      return renbaoStatus;
    }

    public void setRenbaoStatus(String renbaoStatus) {
      this.renbaoStatus = renbaoStatus;
    }

    public String getInsureStatus() {
      return insureStatus;
    }

    public void setInsureStatus(String insureStatus) {
      this.insureStatus = insureStatus;
    }
  }
}
