package com.blog.service.impl;

import com.blog.entity.Article;
import com.blog.repository.ArticleRepository;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleServiceImpl
 * @Author jackchen
 * @Date 2022/8/23 18:15
 * @Description 文章ServiceImpl
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article save(Article article) {
        Article article1 = articleRepository.save(article);
        return article1;
    }

    @Override
    public void edit(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void good(Long articleId) {
        Article article = articleRepository.findById(articleId).get();
        article.setGoods(article.getGoods() + 1);
        articleRepository.save(article);
    }

    @Override
    public Page<Article> serach(String keyword, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 10);
        return articleRepository.search(keyword, pageable);
    }

    @Override
    public Page<Article> serach() {
        Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable = PageRequest.of(0, 10, sort);
        return articleRepository.findAll(pageable);
    }
}
