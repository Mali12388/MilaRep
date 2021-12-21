/**
 * Author: 老洋
 * Date:  2021/6/4 20:39
 */
package com.yyy.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@ApiModel(value = "用户实体")
@Data                   //get、set、toString
@NoArgsConstructor      //无参构造
@AllArgsConstructor     //有参构造
public class User {
    @ApiModelProperty(value = "主键",hidden = true)
    private Long id;    //用户ID
    @ApiModelProperty(value = "昵称")
    private String nickname;    //昵称
    @ApiModelProperty(value = "用户名")
    private String username;    //用户名
    @ApiModelProperty(value = "用户密码")
    private String password;    //密码
    @ApiModelProperty(value = "邮箱")
    private String email;       //邮箱
    @ApiModelProperty(value = "主键",hidden = true)
    private String type;        //类型
    @ApiModelProperty(value = "头像")
    private String avatar;      //头像
    @ApiModelProperty(value = "主键",hidden = true)
    private Date createTime;    //创建时间
    @ApiModelProperty(value = "主键",hidden = true)
    private Date updateTime;    //更新时间
    @ApiModelProperty(value = "主键",hidden = true)
    private List<Blog> blogs = new ArrayList<>();


    public User(Long id, String nickname, String password, String email, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
    }
}