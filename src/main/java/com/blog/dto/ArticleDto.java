package com.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ArticleDto
 * @Author jackchen
 * @Date 2022/8/23 18:09
 * @Description 博客文章dto
 **/
@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private List<CommentDto> comments;
}
