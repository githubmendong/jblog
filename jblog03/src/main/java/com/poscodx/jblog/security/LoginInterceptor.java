package com.poscodx.jblog.security;

import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// UserService();
		UserVo authUser = userService.getUser(id, password);

		if (authUser == null) {
			request.setAttribute("id", id);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);

			return false;
		}

		
		System.out.println("auth success: " + authUser);
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}

}
