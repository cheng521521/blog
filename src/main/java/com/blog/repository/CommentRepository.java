package com.blog.repository;

import com.blog.entity.Article;
import com.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName CommentRepository
 * @Author jackchen
 * @Date 2022/8/23 19:20
 * @Description dao
 **/
public interface CommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment> {
}
