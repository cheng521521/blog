package com.blog.controller;

import com.blog.dto.CommentDto;
import com.blog.dto.Paging;
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
 * @Description 文章评论/查找/点赞
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

    /**
     * 评论文章
     * @param commentDto commentDto
     * @return 返回结果
     */
    @PostMapping("/comment")
    public Result commentArticle(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        commentService.save(comment);
        log.info("评论文章{}成功", commentDto.getArticleId());
        return ResultResponse.getSuccessResult("评论成功");
    }

    /**
     * 评论点赞
     * @param commentId 评论id
     * @param request 请求
     * @return 返回结果
     */
    @PostMapping("/comment/good")
    public Result commentGood(@RequestParam Long commentId, HttpServletRequest request) {
        String realIp = IpUtil.getRealIp(request);
        String key = commentId +"/"+ realIp;
        if (redisUtils.exists(key)) {
            log.info("ip{}重复点赞评论", realIp);
            return ResultResponse.getFailResult("不允许重复点赞");
        }
        redisUtils.set(key, commentId.toString(), 6L);
        commentService.commentGood(commentId);
        return ResultResponse.getSuccessResult("点赞成功");
    }

    /**
     * 文章点赞
     * @param articleId 文章id
     * @param request 请求体
     * @return 返回结果
     */
    @PostMapping("/good")
    public Result articletGood(@RequestParam Long articleId, HttpServletRequest request) {
        String realIp = IpUtil.getRealIp(request);
        String key = articleId +"/"+ realIp;
        if (redisUtils.exists(key)) {
            log.info("ip{}重复点赞文章", realIp);
            return ResultResponse.getFailResult("不允许重复点赞");
        }
        redisUtils.set(key, articleId.toString(), 6L);
        articleService.good(articleId);
        return ResultResponse.getSuccessResult("点赞成功");
    }

    /**
     * 全文检索
     * @param keyword 关键字
     * @param pageNum 起始页
     * @return 返回结果
     */
    @GetMapping("/search")
    public Result searchArticles(@RequestParam String keyword, int pageNum) {
        if (pageNum < 0) {
            pageNum = 0;
        }
        Page<Article> articles = articleService.serach(keyword, pageNum);
        return ResultResponse.getSuccessResult(new Paging<>(articles));
    }

    /**
     * 获取文章所有评论
     * @param articleId 文章id
     * @return 返回结果
     */
    @GetMapping("/get/comments")
    public Result getComments(@RequestParam Long articleId, int pageNum) {
        Page<Comment> comments = commentService.listComments(articleId, pageNum);
        return ResultResponse.getSuccessResult(new Paging<>(comments));
    }

    /**
     * 查找所有文章
     * @return 返回结果
     */
    @GetMapping("/find")
    public Result findArticles() {
        Object articles =  redisUtils.get("articles");
        return ResultResponse.getSuccessResult(articles);
    }

}
