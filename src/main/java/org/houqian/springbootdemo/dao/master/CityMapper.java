package org.houqian.springbootdemo.dao.master;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.houqian.springbootdemo.dto.City;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/7
 */
@Mapper
public interface CityMapper {

  @Select("select * from city limit 1")
  City findOne();
}
