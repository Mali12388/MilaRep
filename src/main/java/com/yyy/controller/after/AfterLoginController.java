/**
 * Author: 老洋
 * Date:  2021/7/3 14:27
 */
package com.yyy.controller.after;


import com.yyy.pojo.User;
import com.yyy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * 后台登录：
 */
@Api(tags = {"用户登录Controller"})
@Controller
public class AfterLoginController {
    @Autowired
    private UserService userService;        //用户表：业务层对象

    /**
     *
     * @param username  登录：账号
     * @param password  登录：密码
     * @param session   用来存放：错误信息 Or  用户名
     * @return
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("/after/login")
    public String index(@RequestParam String username,
                        @RequestParam String password,
                        @ApiIgnore Model model,  @ApiIgnore HttpSession session){

        User user = userService.queryUserByCode(username, password);

        //判断：user是否存在
        if(user == null){
            
            model.addAttribute("message", "用户名或密码错误!!!");
            return "front/login";     //跳转到：登录页面
        }

        session.setAttribute("user", user);   //将用户信息：存入user中
        return "redirect:/after/index";
    }

}