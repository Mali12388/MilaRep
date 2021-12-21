/**
 * Author: 老洋
 * Date:  2021/7/3 16:05
 */
package com.yyy.controller.after;


import com.yyy.service.BlogService;
import com.yyy.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页：controller
 */
@Api(tags = {"后台首页controller"})
@Controller
public class AfterIndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    //跳转：后台首页
    @ApiOperation(value = "跳转后台首页")
    @GetMapping("/after/index")
    public String afterIndex(){
        return "after/index";
    }

    //welcome
    @ApiOperation(value = "welcome页面")
    @GetMapping("/after/welcome")
    public String welcome(Model model){
        Long blogsNum = blogService.queryBlogsNum();            //博客总数目
        Long commentsNum = commentService.queryCommentsNum();   //评论总数目

        model.addAttribute("blogsNum", blogsNum);
        model.addAttribute("commentsNum", commentsNum);
        return "after/welcome";
    }
}