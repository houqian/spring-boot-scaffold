package org.houqian.springbootdemo.service;

import org.houqian.springbootdemo.dto.City;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/7
 */
public interface CityService {

  City getOne(String cNo);
}
