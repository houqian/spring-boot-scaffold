package org.houqian.springbootdemo.converter.mapstruct;

import org.houqian.springbootdemo.dto.RenbaoRiskControlDTO;
import org.houqian.springbootdemo.vo.InsureRiskControlVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2020/1/1
 */
@Mapper
public interface RiskControlConverter {

  RiskControlConverter INSTANCE = Mappers.getMapper(RiskControlConverter.class);


  /**
   * <pre>
   * 1、如果转换的字段相同，则可以自动转换，不需要配置
   * 2、如果不同：
   *  2.1 简单转换，直接指定即可
   *
   * @param rrc
   * @return
   */
  @Mappings({
          @Mapping(target = "applyStatus", expression = "java(InsureRiskControlVO.ApplyStatusEnum.getInsureStatusByRenbaoStatus(rrc.getRenbaoApplyStatus()))"),
          @Mapping(target = "failCode", expression = "java(rrc.getCode().equals(\"200\") ? null : rrc.getCode())")
  })
  InsureRiskControlVO insureRiskControl(RenbaoRiskControlDTO rrc);

}
