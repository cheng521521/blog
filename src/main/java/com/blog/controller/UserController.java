package com.blog.controller;

import com.blog.result.Result;
import com.blog.result.ResultResponse;
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
public class UserController {

    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest req) {
        try {
            req.login(username, password);
            return ResultResponse.getSuccessResult("登录成功");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return ResultResponse.getSuccessResult("登录失败");
    }

    @PostMapping("/loginout")
    public Result loginout(HttpServletRequest req) {
        try {
            req.logout();
            return ResultResponse.getSuccessResult("退出成功");
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return ResultResponse.getSuccessResult("退出失败");
    }

}
