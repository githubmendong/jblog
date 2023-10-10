package com.poscodx.jblog.controller;

import com.poscodx.jblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
    private BlogService blogService;
    private CategoryService categoryService;
    private PostService postService;
    private FileUploadService fileUploadService;



    @Autowired
    public BlogController(BlogService blogService,
                          CategoryService categoryService,
                          PostService postService,
                          FileUploadService fileUploadService) {

        this.blogService = blogService;
        this.categoryService = categoryService;
        this.postService = postService;
        this.fileUploadService = fileUploadService;

    }
    @RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
    public String index(
            @PathVariable("id") String blogId,
            @PathVariable("categoryNo") Optional<Long> categoryNo,
            @PathVariable("postNo") Optional<Long> postNo,
            Model model) {
        BlogVo blogVo = blogService.getBlog(blogId);
        if (blogVo == null) {
            return "redirect:/";
        }
        model.addAttribute("blogVo", blogVo);
        List<CategoryVo> categoryList = categoryService.getCategorys(blogId);
        model.addAttribute("categoryList", categoryList);

        if (!(categoryNo.isPresent() || postNo.isPresent())) {
            List<PostVo> postList = postService.getPosts(blogId);
            model.addAttribute("postList", postList);
            return "blog/main";
        } else if (!postNo.isPresent()) {
            List<PostVo> categoryPostList = postService.getCategoryPosts(blogId, categoryNo.get());
            model.addAttribute("categoryPostList", categoryPostList);
            return "blog/category";
        } else {
            List<PostVo> categoryPostList = postService.getCategoryPosts(blogId, categoryNo.get());
            model.addAttribute("categoryPostList", categoryPostList);

            PostVo post = postService.getPost(blogId, postNo.get());
            model.addAttribute("post", post);

            return "blog/post";
        }
    }

    @RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
    public String adminBasic(@PathVariable("id") String blogId, Model model) {
        BlogVo blogVo = blogService.getBlog(blogId);
        model.addAttribute("blogVo", blogVo);
        return "blog/admin-basic";
    }

    @RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
    public String adminBasic(
            @PathVariable("id") String blogId,
            BlogVo vo,
            @RequestParam("f") MultipartFile file,
            Model model) {
        vo.setImage(blogService.getBlog(blogId).getImage());
        vo.setBlogId(blogId);
        if (!file.isEmpty()) {
            String url = fileUploadService.restore(file);
            vo.setImage(url);
        }
        blogService.updateBlog(vo);
        return "redirect:/" + blogId + "/admin/basic";
    }

    @RequestMapping(value = {"/admin/category", "/admin/category/{categoryNo}"}, method = RequestMethod.GET)
    public String adminCategory(
            @PathVariable("id") String blogId,
            @PathVariable("categoryNo") Optional<Long> categoryNo,
            Model model) {
        BlogVo blogVo = blogService.getBlog(blogId);
        model.addAttribute("blogVo", blogVo);

        if (categoryNo.isPresent()) {
            categoryService.deleteCategory(categoryNo.get());
            return "redirect:/" + blogId + "/admin/category";
        }
        List<CategoryVo> categoryList = categoryService.getCategoryWithPost(blogId);
        model.addAttribute("categoryList", categoryList);

        return "blog/admin-category";
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST)
    public String adminCategory(@PathVariable("id") String blogId, CategoryVo vo) {
        vo.setBlogId(blogId);
        categoryService.addCategory(vo);
        return "redirect:category";
    }

    @RequestMapping(value = "/admin/post", method = RequestMethod.GET)
    public String adminPost(@PathVariable("id") String blogId, Model model) {
        BlogVo blogVo = blogService.getBlog(blogId);
        model.addAttribute("blogVo", blogVo);
        List<CategoryVo> categoryList = categoryService.getCategorys(blogId);
        model.addAttribute("categoryList", categoryList);

        return "blog/admin-write";
    }

    @RequestMapping(value = "/admin/post", method = RequestMethod.POST)
    public String adminPost(@PathVariable("id") String blogId, PostVo vo, Model model) {
        postService.addPost(vo);
        BlogVo blogVo = blogService.getBlog(blogId);
        model.addAttribute("blogVo", blogVo);
        List<CategoryVo> categoryList = categoryService.getCategorys(blogId);
        model.addAttribute("categoryList", categoryList);

        return "redirect:/" + blogId + "/admin/post";
    }
}
