package org.houqian.springbootdemo.converter.mapstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.houqian.springbootdemo.dto.RenbaoRiskControlDTO;
import org.houqian.springbootdemo.vo.InsureRiskControlVO;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2020/1/1
 */
public class TestConverter {

  static ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) throws JsonProcessingException {
    RiskControlConverter riskControlConverter = RiskControlConverter.INSTANCE;

    RenbaoRiskControlDTO renbaoRiskControlDTO = RenbaoRiskControlDTO.builder()
            .code("5000")
            .failReason("xxxxx 失败")
            .merchNo("30060")
            .instNo("10210")
            .renbaoApplyStatus("F")
            .build();

    InsureRiskControlVO insureRiskControlVO = riskControlConverter.insureRiskControl(renbaoRiskControlDTO);
    System.out.println(objectMapper.writeValueAsString(insureRiskControlVO));

  }
}
