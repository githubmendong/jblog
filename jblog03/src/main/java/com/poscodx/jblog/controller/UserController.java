package com.poscodx.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.CategoryService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.UserVo;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BlogService blogService;
    private final CategoryService categoryService;

    @Autowired
    public UserController(UserService userService, BlogService blogService, CategoryService categoryService) {
        this.userService = userService;
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping("/join")
    public String getJoinPage(@ModelAttribute UserVo userVo) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error);
            }
            model.addAllAttributes(result.getModel()); // Output as a Map
            return "user/join";
        }
        // 1. Add user
        userService.addUser(userVo);

        // 2. Set up the default blog
        BlogVo blogVo = new BlogVo();
        blogVo.setTitle(userVo.getName() + "'s Blog");
        blogVo.setBlogId(userVo.getId());
        blogVo.setImage("/assets/images/default.jpg");
        System.out.println(blogVo);
        blogService.addBlog(blogVo);

        // 3. Create an uncategorized category
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setBlogId(userVo.getId());
        categoryVo.setName("Uncategorized");
        categoryVo.setDescription("Category for uncategorized posts.");
        categoryService.addCategory(categoryVo);

        return "redirect:/user/joinsuccess";
    }

    @GetMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "user/login";
    }

    @PostMapping("/auth")
    public String authenticate(HttpSession session,
                               @RequestParam(value = "accountName", required = true, defaultValue = "") String id,
                               @RequestParam(value = "password", required = true, defaultValue = "") String password, Model model) {
        UserVo authUser = userService.getUser(id, password);
        if (authUser == null) {
            model.addAttribute("id", id);
            return "user/login";
        }
        /* Authentication process */
        session.setAttribute("authUser", authUser);
        System.out.println(authUser + " logged in successfully!");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return "redirect:/";
    }
}
