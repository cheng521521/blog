package com.blog.service;

import com.blog.entity.Article;
import org.springframework.data.domain.Page;

/**
 * @ClassName ArticleService
 * @Author jackchen
 * @Date 2022/8/23 18:14
 * @Description TODO
 **/
public interface ArticleService {

    Article save(Article article);

    void edit(Article article);

    Page<Article> serach(String keyword, int pageNum);

}
