package com.blog.controller;

import com.blog.dto.ArticleDto;
import com.blog.entity.Article;
import com.blog.result.Result;
import com.blog.result.ResultResponse;
import com.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/arricle/push")
    public Result pushArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        Article article1 = articleService.save(article);
        return ResultResponse.getSuccessResult(article1);
    }
    @PostMapping("/arricle/edit")
    public Result editArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        articleService.edit(article);
        return ResultResponse.getSuccessResult("修改成功");
    }
}
