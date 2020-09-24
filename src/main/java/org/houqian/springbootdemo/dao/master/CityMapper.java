package org.houqian.springbootdemo.dao.master;

import org.apache.ibatis.annotations.Insert;
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

  /**
   * 查询一个
   * @return
   */
  @Select("select * from city limit 1")
  City findOne();

  /**
   * 新增一个
   * @param city
   */
  @Insert("insert into city("
            + "name, "
            + "state,"
            + "country"
            + ") values ("
            + "#{name}, "
            + "#{state}, "
            + "#{country}"
          + ");")
  void insertOne(City city);
}
