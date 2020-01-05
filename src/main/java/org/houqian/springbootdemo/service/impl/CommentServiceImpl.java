package org.houqian.springbootdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.houqian.springbootdemo.dao.CommentInfoMapper;
import org.houqian.springbootdemo.dto.CommentInfo;
import org.houqian.springbootdemo.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2020/1/5
 */
@Service
public class CommentServiceImpl implements CommentService {

  private CommentInfoMapper commentInfoMapper;

  public CommentServiceImpl(CommentInfoMapper commentInfoMapper) {
    this.commentInfoMapper = commentInfoMapper;
  }

  @Override
  public CommentInfo save(CommentInfo commentInfo) {
    this.commentInfoMapper.insert(commentInfo);
    return commentInfo;
  }

  @Override
  public CommentInfo getById(Long id) {
    return commentInfoMapper.selectOne(new QueryWrapper<CommentInfo>().lambda().eq(CommentInfo::getGoodsId, 0));
  }
}
