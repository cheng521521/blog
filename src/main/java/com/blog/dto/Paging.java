package com.blog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Paging
 * @Author jackchen
 * @Date 2022/8/24 11:05
 * @Description 分页返回对象
 **/
@Data
@NoArgsConstructor
public class Paging<T> implements Serializable {
    /**
     * 总数量
     */
    private Long total;

    /**
     * 当前请求数量
     */
    private Integer count;

    /**
     * 当前页码
     */
    private Integer page;

    /**
     * 总页码
     */
    private Integer totalPage;

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 构造函数
     */
    public Paging(Page<T> pageData) {
        this.initPageParameters(pageData);
    }

    /**
     * 构造分页参数
     * @param pageData
     */
    void initPageParameters(Page<T> pageData) {
        this.total = pageData.getTotalElements();
        this.count = pageData.getSize();
        this.page = pageData.getNumber();
        this.totalPage = pageData.getTotalPages();
        this.data = pageData.getContent();
    }
}
