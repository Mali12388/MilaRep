/**
 * Author: 老洋
 * Date:  2021/6/17 14:58
 */
package com.yyy.web;


import com.yyy.pojo.Blog;
import com.yyy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    //跳转：归档页面
    @GetMapping("/archives")
    public String archives(Model model){
        //按creatTime降序查询：所有blog
        List<Blog> blogs = blogService.listBlog();
        model.addAttribute("blogs", blogs);

        return "archives";
    }

}