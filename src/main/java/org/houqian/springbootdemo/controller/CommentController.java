package org.houqian.springbootdemo.controller;

import org.houqian.springbootdemo.dto.CommentInfo;
import org.houqian.springbootdemo.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2020/1/5
 */
@RestController("/comment")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping()
  public CommentInfo save(@NotEmpty @RequestBody CommentInfo commentInfo) {
    return commentService.save(commentInfo);
  }

  @GetMapping()
  public CommentInfo getById(@RequestParam("id") Long id) {
    return commentService.getById(id);
  }
}
