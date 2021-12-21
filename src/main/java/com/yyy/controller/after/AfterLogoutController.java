/**
 * Author: 老洋
 * Date:  2021/7/3 19:17
 */
package com.yyy.controller.after;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * 处理后台退出：controller
 */
@Api(tags = {"用户退出Controller"})
@Controller
public class AfterLogoutController {
    @GetMapping("/after/logout")
    @ApiOperation(value = "用户退出")
    public String layout( @ApiIgnore HttpSession session){
        session.invalidate();             //将session设置为失效

        return "redirect:/front/login";
    }
}