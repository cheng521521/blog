package com.blog.result;

import lombok.Data;

/**
 * @ClassName Result
 * @Author jackchen
 * @Date 2022/8/23 20:19
 * @Description 统一API响应结果封装
 **/
@Data
public class Result {
    private int code;

    private String message = "success";

    private Object data;

    // 后面result生成器需要以下方法
    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code;
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }
}
