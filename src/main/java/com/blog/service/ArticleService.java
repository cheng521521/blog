package com.blog.service;

import com.blog.entity.Article;
import org.springframework.data.domain.Page;

/**
 * @ClassName ArticleService
 * @Author jackchen
 * @Date 2022/8/23 18:14
 * @Description 文章Service
 **/
public interface ArticleService {

    Article save(Article article);

    void edit(Article article);

    void good(Long articleId);

    Page<Article> serach(String keyword, int pageNum);

    Page<Article> serach();

}
