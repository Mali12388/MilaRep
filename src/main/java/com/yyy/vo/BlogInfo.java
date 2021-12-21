/**
 * Author: 老洋
 * Date:  2021/7/5 17:17
 */
package com.yyy.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于：添加博客、更新博客（存放blog信息）
 */
@ApiModel(value = "博客信息实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {
    @ApiModelProperty(value = "主键")
    private Long bid; //添加博客id
    private String title;      //添加博客标题
    private String content;    //添加博客内容
    @ApiModelProperty(value = "标记类型")
    private String flag;     // //标记：原创、转载、翻译
    private Long typeId;   //添加博客类型
    private Long[] tags;   //添加博客标签
    private String firstPicture; //添加博客首图
    private String description;  //添加博客描述
    private String published;          //发布状态
    private String recommend;          //推荐
    private String commentabled;       //评论
    private String appreciation;       //赞赏
    private String shareStatement;     //版权
    private Long uid;  //添加博客的用户id
}