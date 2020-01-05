package org.houqian.springbootdemo.service;

import org.houqian.springbootdemo.dto.CommentInfo;

/**
 * 备注服务
 *
 * @author : houqian
 * @version : 1.0
 * @since : 2020/1/5
 */
public interface CommentService {
  /**
   * 保存备注
   *
   * @param commentInfo
   * @return
   */
  CommentInfo save(CommentInfo commentInfo);

  CommentInfo getById(Long id);
}
