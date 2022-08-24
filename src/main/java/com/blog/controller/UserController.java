package com.blog.controller;

import com.blog.result.Result;
import com.blog.result.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Author jackchen
 * @Date 2022/8/23 15:33
 * @Description 用户登录模块
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @param req 请求
     * @return 返回
     */
    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest req) {
        try {
            req.login(username, password);
            log.info("用户{}登录",username);
            return ResultResponse.getSuccessResult("登录成功");
        } catch (ServletException e) {
            e.printStackTrace();
            return ResultResponse.getFailResult(e.getMessage());
        }
    }

    /**
     * 用户登出
     * @param req 请求体
     * @return 返回结果
     */
    @PostMapping("/loginout")
    public Result loginout(HttpServletRequest req) {
        try {
            log.info("用户登出");
            req.logout();
            return ResultResponse.getSuccessResult("退出成功");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return ResultResponse.getSuccessResult("退出失败");
    }

}
