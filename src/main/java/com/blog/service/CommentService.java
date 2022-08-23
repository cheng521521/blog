package com.blog.service;

import com.blog.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CommentService
 * @Author jackchen
 * @Date 2022/8/23 19:18
 * @Description 评论服务
 **/
public interface CommentService {
    void save(Comment comment);
    List<Comment> listComments(Long articleId);
    void commentGood(Long commentId);
}
