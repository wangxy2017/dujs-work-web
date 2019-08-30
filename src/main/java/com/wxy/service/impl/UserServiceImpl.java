package com.wxy.service.impl;

import com.wxy.entity.User;
import com.wxy.mapper.UserMapper;
import com.wxy.service.UserService;
import com.wxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author wxy
 * @Date 19-7-24 下午5:28
 * @Description TODO
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int saveUser(String username, String password, String email) {
        Assert.hasText(username, "The parameter username is required");
        Assert.hasText(password, "The parameter password is required");
        Assert.hasText(email, "The   parameter email is required");
        User user = new User();
        user.setUsername(username);
        User user1 = userMapper.queryByProperties(user);
        if (user1 == null) {
            user.setSalt(MD5Utils.getSalt(8));
            user.setPassword(MD5Utils.MD5Encode(password, user.getSalt()));
            user.setEmail(email);
            return userMapper.save(user);
        }
        throw new RuntimeException("用户已存在");
    }

    @Override
    public User queryById(Long id) {
        Assert.notNull(id, "The parameter id is required");
        return userMapper.queryById(id);
    }

    @Override
    public User queryByUsername(String username) {
        Assert.notNull(username, "The parameter username is required");
        User user = new User();
        user.setUsername(username);
        return userMapper.queryByProperties(user);
    }

    @Override
    public User queryByEmail(String email) {
        Assert.notNull(email, "The parameter email is required");
        User user = new User();
        user.setEmail(email);
        return userMapper.queryByProperties(user);
    }

    @Override
    public int updatePassword(Long userId, String oldPassword, String newPassword) {
        Assert.notNull(userId, "The parameter userId is required");
        Assert.notNull(oldPassword, "The parameter oldPassword is required");
        Assert.notNull(newPassword, "The parameter newPassword is required");
        User user = userMapper.queryById(userId);
        if (user != null && user.getPassword().equals(MD5Utils.MD5Encode(oldPassword, user.getSalt()))) {
            user.setSalt(MD5Utils.getSalt(8));
            user.setPassword(MD5Utils.MD5Encode(newPassword, user.getSalt()));
            return userMapper.update(user);
        }
        throw new RuntimeException("密码错误");
    }

    @Override
    public int updateUser(Long userId, String email, String nickName) {
        Assert.notNull(userId, "The parameter userId is required");
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        user.setNickName(nickName);
        return userMapper.update(user);
    }
}
