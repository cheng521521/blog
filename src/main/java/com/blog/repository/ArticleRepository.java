package com.blog.repository;

import com.blog.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ArticleRepository
 * @Author jackchen
 * @Date 2022/8/23 16:59
 * @Description TODO
 **/
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT * FROM t_article WHERE MATCH(content) "
            + "AGAINST (?1)", nativeQuery = true)
    public Page<Article> search(String keyword, Pageable pageable);
}
