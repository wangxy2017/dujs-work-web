package com.wxy.service.impl;

import com.wxy.constanst.NoteCategories;
import com.wxy.entity.User;
import com.wxy.mapper.UserMapper;
import com.wxy.service.CategoryService;
import com.wxy.service.UserService;
import com.wxy.util.CodeUtils;
import com.wxy.util.EmailUtils;
import com.wxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.util.List;

/**
 * @Author wxy
 * @Date 19-7-24 下午5:28
 * @Description TODO
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EmailUtils emailUtils;

    @Value("${mail.adminEmail}")
    private String adminEmail;

    @Override
    public int saveUser(String username, String password, String email) {
        Assert.hasText(username, "The parameter username is required");
        Assert.hasText(password, "The parameter password is required");
        Assert.hasText(email, "The   parameter email is required");
        User user = new User();
        user.setUsername(username);
        List<User> list = userMapper.queryList(user);
        if (list.size() == 0) {
            user.setSalt(CodeUtils.randomStr(8));
            user.setPassword(MD5Utils.encrypt(password, user.getSalt()));
            user.setEmail(email);
            int save = userMapper.save(user);
            if (save > 0) {
                // 创建默认分类
                categoryService.saveCategory(NoteCategories.DEFAULT, user.getId());
                categoryService.saveCategory(NoteCategories.RECYCLE, user.getId());
                return 1;
            }
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
        Assert.hasText(username, "The parameter username is required");
        User user = new User();
        user.setUsername(username);
        List<User> list = userMapper.queryList(user);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User queryByEmail(String email) {
        Assert.hasText(email, "The parameter email is required");
        User user = new User();
        user.setEmail(email);
        List<User> list = userMapper.queryList(user);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updatePassword(Long userId, String oldPassword, String newPassword) {
        Assert.notNull(userId, "The parameter userId is required");
        Assert.hasText(oldPassword, "The parameter oldPassword is required");
        Assert.hasText(newPassword, "The parameter newPassword is required");
        User user = userMapper.queryById(userId);
        if (user != null && user.getPassword().equals(MD5Utils.encrypt(oldPassword, user.getSalt()))) {
            user.setSalt(CodeUtils.randomStr(8));
            user.setPassword(MD5Utils.encrypt(newPassword, user.getSalt()));
            return userMapper.update(user);
        }
        throw new RuntimeException("密码错误");
    }

    @Override
    public int updateUser(Long userId, String email, String nickName, String photo) {
        Assert.notNull(userId, "1The parameter userId is required");
        if (StringUtils.hasText(email)) {
            User user1 = queryByEmail(email);
            if (user1 != null && !user1.getId().equals(userId)) {
                throw new RuntimeException("邮箱已存在");
            }
        }
        User user = new User();
        user.setId(userId);
        user.setEmail(email);
        user.setNickName(nickName);
        user.setPhoto(photo);
        return userMapper.update(user);
    }

    @Override
    public boolean forgotPassword(String username, String email) {
        Assert.hasText(username, "The parameter username is required");
        Assert.hasText(email, "The parameter email is required");
        User user = queryByUsername(username);
        if (user != null && user.getEmail().equals(email)) {
            String newPwd = CodeUtils.randomStr(10);
            user.setSalt(CodeUtils.randomStr(8));
            user.setPassword(MD5Utils.encrypt(newPwd, user.getSalt()));
            userMapper.update(user);
            // 发送邮件
            try {
                emailUtils.sendEmail(email, "重置密码", "新密码：" + newPwd);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException("发送失败");
            }
        }
        return false;
    }

    @Override
    public boolean giveIdea(String email, String content) {
        Assert.hasText(email, "The parameter email is required");
        Assert.hasText(content, "The parameter content is required");
        // 发送邮件
        try {
            emailUtils.sendEmail(adminEmail, "用户意见反馈", content);
            emailUtils.sendEmail(email, "dujs系统管理员回复", "感谢您的宝贵意见，我们会努力改进的，谢谢支持！");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("发送失败");
        }
    }

    @Override
    public boolean sendCode(String email, String code) {
        Assert.hasText(email, "The parameter email is required");
        Assert.hasText(code, "The parameter code is required");
        try {
            emailUtils.sendEmail(email, "注册验证码", code);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("发送失败");
        }
    }
}
