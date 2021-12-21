/**
 * Author: 老洋
 * Date:  2021/6/4 20:24
 */
package com.yyy.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论类
 */

@Data                   //get、set、toString
@NoArgsConstructor      //无参构造
@AllArgsConstructor     //有参构造
public class Comment {
    private Long id;
    @ApiModelProperty(value = "主键",hidden = true)
    private String nickname;        //昵称
    @ApiModelProperty(value = "主键",hidden = true)
    private String email;           //邮箱
    @ApiModelProperty(value = "主键",hidden = true)
    private String avatar;          //头像
    @ApiModelProperty(value = "主键",hidden = true)
    private String content;         //评论内容
    @ApiModelProperty(value = "主键",hidden = true)
    private Date createTime;        //创建时间
    @ApiModelProperty(value = "主键",hidden = true)
    private List<Comment> replyComments = new ArrayList<>();   //一个评论父类对象 ==>> 对应多条评论子类对象
    @ApiModelProperty(value = "主键",hidden = true)
    private Comment parentComment;  //多个子类 ==>>> 对应一个父类
    @ApiModelProperty(value = "主键",hidden = true)
    private Blog blog;              //多条评论  ==>>> 1篇博客
    @ApiModelProperty(value = "主键",hidden = true)
    private boolean adminComment;   //true为博主评论， false则不是
}