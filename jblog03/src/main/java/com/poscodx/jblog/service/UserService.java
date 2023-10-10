package com.poscodx.jblog.service;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public void join(@Valid UserVo userVo) {

        // 1. Add user
        userRepository.insert(userVo);

        // 2. Set up the default blog
        BlogVo blogVo = new BlogVo();
        blogVo.setTitle(userVo.getName() + "'s Blog");
        blogVo.setBlogId(userVo.getId());
        blogVo.setImage("/assets/images/default.jpg");
        System.out.println(blogVo);
        blogRepository.insert(blogVo);

        // 3. Create an uncategorized category
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setBlogId(userVo.getId());
        categoryVo.setName("Uncategorized");
        categoryVo.setDescription("Category for uncategorized posts.");
        categoryRepository.insert(categoryVo);

    }

    public boolean addUser(UserVo vo) {
        return userRepository.insert(vo);
    }

    public UserVo getUser(String id, String password) {
        return userRepository.findByIdAndPassword(id, password);
    }

    public UserVo getUser(String id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(UserVo userVo) {
        return userRepository.update(userVo);
    }



}
