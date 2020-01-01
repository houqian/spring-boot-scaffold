package org.houqian.springbootdemo.controller;

import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.houqian.springbootdemo.dto.City;
import org.houqian.springbootdemo.service.CityService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/7
 */
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/test")
public class CityController {

  private CityService cityService;

  @GetMapping("/getCity")
  public City getCity(@NotEmpty @RequestParam String cNo) {
    return cityService.getOne(cNo);
  }
}
