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

    User queryByUsername(String username);

    User queryByEmail(String email);

    /**
     * 修改密码
     *
     * @param userId
     * @param newPassword
     * @return
     */
    int updatePassword(Long userId, String oldPassword, String newPassword);
}
