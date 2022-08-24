package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.repository.CommentRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CommentServiceImpl
 * @Author jackchen
 * @Date 2022/8/23 19:19
 * @Description 评论
 **/
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Page<Comment> listComments(Long articleId, int pageNum) {
        Specification<Comment> specification = new Specification<Comment>() {
            @Override
            public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<>();
                predicates.add(cb.equal(root.get("articleId"), articleId));
                predicates.add(cb.between(root.get("createTime"),  // 发布时间7天前至今
                        Date.from(Instant.now().minus(Duration.ofDays(7))), new Date()));

                return query.where(predicates.toArray(new Predicate[0])).getRestriction();
            }
        };
        Pageable pageable = PageRequest.of(pageNum, 10, Sort.by(Sort.Direction.ASC, "createTime"));
        return commentRepository.findAll(specification, pageable);
    }

    @Override
    public void commentGood(Long commentId) {
        //todo 这里是否要判断评论id不存在的情况
        Optional<Comment> comment1 = commentRepository.findById(commentId);
        if (!comment1.isPresent()) {
            throw new RuntimeException("评论不存在");
        }
        Comment comment = commentRepository.findById(commentId).get();
        comment.setGoods(comment.getGoods() +1);
        commentRepository.save(comment);
    }
}
