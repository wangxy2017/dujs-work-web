package com.wxy.service;

import com.wxy.entity.User;

/**
 * @Author wxy
 * @Date 19-7-24 下午5:27
 * @Description TODO
 **/
public interface UserService {

    int saveUser(String username, String password, String email);

    User queryById(Long id);
}
