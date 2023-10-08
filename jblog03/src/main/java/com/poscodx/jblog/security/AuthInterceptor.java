package com.poscodx.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import com.poscodx.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        UserVo authUser = (UserVo) session.getAttribute("authUser");

        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        String blogId = request.getServletPath().split("/")[1];

        if (!authUser.getId().equals(blogId)) {
            response.sendRedirect(request.getContextPath() + "/" + blogId);
            return false;
        }
        return true;
    }
}
