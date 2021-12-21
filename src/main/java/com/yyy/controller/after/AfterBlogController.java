/**
 * Author: 老洋
 * Date:  2021/7/3 21:05
 */
package com.yyy.controller.after;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyy.pojo.Type;
import com.yyy.service.BlogService;
import com.yyy.service.CommentService;
import com.yyy.service.TagService;
import com.yyy.service.TypeService;
import com.yyy.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台博客：controller
 */
@Api(tags = {"后台博客列表Controller"})
@Controller
public class AfterBlogController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;        //blog业务层对象

    @Autowired
    private TypeService typeService;        //type业务层对象

    @Autowired
    private TagService tagService;          //tag业务层对象

    @Autowired
    private CommentService commentService;

    //1、跳转：博客列表
    @GetMapping("/after/blog-list")
    @ApiOperation(value = "后台跳转博客列表")
    public  String blogList(@ApiIgnore Model model){
        List<Type> types = typeService.queryAllTypes();
        model.addAttribute("types", types);

        return "after/blog-list";
    }

    //2、显示：表格
    @ApiOperation(value = "后台显示表格")
    @GetMapping("/after/showBlogTable")
    @ResponseBody
    public DataVO<BlogVO> list(Integer page, Integer limit, SearchBlogInfo searchBlogInfo){
        DataVO<BlogVO> data = new DataVO<>();
        data.setCode(0);
        data.setMsg("");

        Page pageObj = PageHelper.startPage(page, limit);
        List<BlogVO> blogVOS = blogService.queryAllBlogVO(searchBlogInfo);
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOS);

        data.setData(pageInfo.getList());
        data.setCount(pageObj.getTotal());
        return data;
    }

    //3、修改：(推荐、评论、赞赏、版权)按钮状态
    @ApiOperation(value = "后台修改(推荐、评论、赞赏、版权)按钮状态")
    @PostMapping("/after/updSwitch")
    @ResponseBody
    public String t1(SwitchVO switchVO){
        int i = blogService.updBlogSwitch(switchVO);
        if(i > 0){
            return "true";          //修改成功
        }

        return "false";             //修改失败
    }

    //2、跳转：博客添加页面
    @ApiOperation(value = "后台跳转博客添加页面")
    @GetMapping("/after/toAddBlogPage")
    public String toAddBlogPage(@ApiIgnore Model model){
        List<Type> types = typeService.queryAllTypesNotSort();
        model.addAttribute("types", types);

        logger.info(types +"");
        return "after/blog-add";
    }

    //3、添加博客
    @ApiOperation(value = "后台博客添加页面")
    @PostMapping("/after/addBlog")
    public String addBlog(BlogInfo blogInfo){
        Long id = blogService.addBlog(blogInfo);

        tagService.addBlogAndTag(id, blogInfo.getTags());
        return "redirect:/after/index";
    }

    //4、跳转：修改博客页面
    @ApiOperation(value = "后台跳转博客修改页面")
    @GetMapping("/after/toUpdBlogPage/{id}")
    public String toUpdBlogPage(@PathVariable Long id, Model model){
        model.addAttribute("tags", tagService.queryTagIdByBlogId(id));
        model.addAttribute("types", typeService.queryAllTypesNotSort());
        model.addAttribute("blog", blogService.queryBlogInfoById(id));

        return "after/blog-upd";
    }

    //5、正式修改：博客
    @ApiOperation(value = "后台博客修改页面")
    @PostMapping("/after/updBlog")
    public String updBlog(BlogInfo blogInfo){
        blogService.updBlog(blogInfo);                  //更新blog
        tagService.delTagByBlogId(blogInfo.getBid());   //删除：blog_tag表中：旧blogId的所有标签
        tagService.addBlogAndTag(blogInfo.getBid(), blogInfo.getTags());    //添加新的：blogId的标签
        return "redirect:/after/index";
    }

    //6、批量：选中博客 （单篇、多篇皆可）
    @ApiOperation(value = "后台批量删除选中博客")
    @PostMapping("/after/delAllBlog")
    @ResponseBody
    public String delAllBlog(Long[] ids){
        for(Long id:ids){
            tagService.delTagByBlogId(id);          //删除：blog的标签
            commentService.delCommentByBlogId(id);  //删除：评论
            blogService.delBlogById(id);            //删除：blog
        }
        return "true";
    }
}