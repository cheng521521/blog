package com.blog.dto;

import lombok.Data;

/**
 * @ClassName CommentDto
 * @Author jackchen
 * @Date 2022/8/23 18:11
 * @Description 评论
 **/
@Data
public class CommentDto {
    private Long articleId;
    private String content;
}
