/**
 * Author: 老洋
 * Date:  2021/7/1 20:59
 */
package com.yyy.controller.front;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyy.pojo.Blog;
import com.yyy.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Api(tags = {"前端搜索controller"})
@Controller
public class FrontSearchController {
    @Autowired
    private BlogService blogService;

    @Value("${searchsize}")
    private Integer pageSizeConfig;         //搜索页面：按多少篇文章分页
    private Logger logger = LoggerFactory.getLogger(getClass());
    @ApiOperation(value = "搜索页面")
    @PostMapping("/front/search")
    public String search(
            @RequestParam(required = false,defaultValue = "") String query,
            @RequestParam(required = false,defaultValue = "1")int pagenum,
            @RequestParam(required = false,defaultValue = "2")int pagesize,
            Model model){

        if(pagesize == 2){
            pagesize = pageSizeConfig;
        }

        PageHelper.startPage(pagenum, pagesize);
        List<Blog> blogs =  blogService.queryBlogsByQueryStr(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("currentQuery", query);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query",query);

        return "front/search";
    }
}