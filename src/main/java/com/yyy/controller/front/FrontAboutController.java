/**
 * Author: 老洋
 * Date:  2021/7/2 16:02
 */
package com.yyy.controller.front;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Api(tags = {"前端关于我Controller"})
@Controller
public class FrontAboutController {
    //关于我页面
    @ApiOperation(value = "关于我页面")
    @GetMapping("/front/about")
    public String about(){
        return "front/about";
    }
}