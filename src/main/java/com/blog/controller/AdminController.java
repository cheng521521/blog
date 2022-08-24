package com.blog.controller;

import com.blog.dto.ArticleDto;
import com.blog.dto.Paging;
import com.blog.entity.Article;
import com.blog.result.Result;
import com.blog.result.ResultResponse;
import com.blog.service.ArticleService;
import com.blog.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminController
 * @Author jackchen
 * @Date 2022/8/23 15:43
 * @Description 发布文章/修改文章
 **/
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 发布文章
     * @param articleDto  articleDto
     * @return 返回结果
     */
    @PostMapping("/arricle/push")
    public Result pushArticle(@RequestBody @Validated ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        Article article1 = articleService.save(article);
        Page<Article> articles = articleService.serach();
        redisUtils.set("articles", new Paging<>(articles));
        log.info("发布文章{}成功,更新缓存", article1.getId());
        return ResultResponse.getSuccessResult(article1);
    }

    /**
     * 修改文章
     * @param articleDto articleDto
     * @return 返回结果
     */
    @PostMapping("/arricle/edit")
    public Result editArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleService.edit(article);
        return ResultResponse.getSuccessResult("修改成功");
    }
}
