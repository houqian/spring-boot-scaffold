package org.houqian.springbootdemo.service.impl;

import lombok.AllArgsConstructor;
import org.houqian.springbootdemo.dao.CityMapper;
import org.houqian.springbootdemo.dto.City;
import org.houqian.springbootdemo.service.CityService;
import org.springframework.stereotype.Service;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/7
 */
@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

  private CityMapper cityMapper;

  @Override
  public City getOne(String cNo) {
    return cityMapper.findOne();
  }
}
