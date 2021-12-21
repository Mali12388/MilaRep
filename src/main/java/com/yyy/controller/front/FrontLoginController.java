/**
 * Author: 老洋
 * Date:  2021/7/2 16:18
 */
package com.yyy.controller.front;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
@Api(tags = {"前端登录controller"})
@Controller
public class FrontLoginController {
    @ApiOperation(value = "登录")
    @GetMapping("/front/login")
    public String login(HttpSession session){
        //session.invalidate();               //让user失效

        if(session.getAttribute("user") != null){
            return "after/index";
        }

        return "front/login";
    }
}