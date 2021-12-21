/**
 * Author: 老洋
 * Date:  2021/7/3 16:34
 */
package com.yyy.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器作用：
 *      日志记录，权限检查，性能监控，通用行为
 */
//判断是否登录 登录就可以访问后台 没有登录只能访问前台
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user") == null){
            request.setAttribute("message", "没有权限, 请登录。");
            request.getRequestDispatcher("/front/login").forward(request, response);
            return false;
        }

        return true;        //放行
    }
}