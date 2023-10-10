package com.poscodx.jblog.service;

import com.poscodx.jblog.repository.BlogRepository;
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

//    @Transactional
//    public void join(@Valid UserVo userVo, CategoryVo categoryVo) {
//        userRepository.insert(userVo);
//        BlogRepository.insert(userVo);
//        BlogRepository.insertCategory(categoryVo);
//    }

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
