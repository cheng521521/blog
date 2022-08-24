package com.blog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Article
 * @Author jackchen
 * @Date 2022/8/23 16:34
 * @Description 文章实体
 **/
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //文章标题
    private String title;
    //文章内容
    @Lob
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;
    private Integer goods = 0;
    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;


}
