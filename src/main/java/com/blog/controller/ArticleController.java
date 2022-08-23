package com.blog.controller;

import com.blog.dto.ArticleDto;
import com.blog.dto.CommentDto;
import com.blog.entity.Article;
import com.blog.entity.Comment;
import com.blog.result.Result;
import com.blog.result.ResultResponse;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import com.blog.util.IpUtil;
import com.blog.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ArticleController
 * @Author jackchen
 * @Date 2022/8/23 15:43
 * @Description 发布文章/修改文章
 **/
@RestController
@RequestMapping("/arricle")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/push")
    public Result pushArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        Article article1 = articleService.save(article);
        return ResultResponse.getSuccessResult(article1);
    }
    @PostMapping("/edit")
    public Result editArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleService.edit(article);
        return ResultResponse.getSuccessResult("修改成功");
    }

    @PostMapping("/comment")
    public Result commentArticle(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        commentService.save(comment);
        return ResultResponse.getSuccessResult("评论成功");
    }

    @PostMapping("/comment/good")
    public Result commentGood(@RequestParam Long commentId, HttpServletRequest request) {
        String realIp = IpUtil.getRealIp(request);
        String key = commentId +"/"+ realIp;
        if (redisUtils.exists(key)) {
            return ResultResponse.getFailResult("不允许重复点赞");
        }
        redisUtils.set(key, commentId.toString(), 600L);
        commentService.commentGood(commentId);
        return ResultResponse.getSuccessResult("点赞成功");
    }

    @GetMapping("/search")
    public Result searchArticles(String keyword, int pageNum) {
        Page<Article> articles = articleService.serach(keyword, pageNum);
        return ResultResponse.getSuccessResult(articles);
    }

    @GetMapping("/get/comments")
    public Result getComments(@RequestParam Long articleId) {
        List<Comment> comments = commentService.listComments(articleId);
        return ResultResponse.getSuccessResult(comments);
    }

}