package com.wxy.service;

import com.wxy.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wxy
 * @Date 19-8-30 下午4:35
 * @Description TODO
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     */
    @Test
    public void TestSaveUser() {
        String username = "wxy123";
        String password = "wxy123";
        String email = "wxy123@qq.com";
        int save = userService.saveUser(username, password, email);
        log.info("新增用户：save = {}", save > 0);
    }

    /**
     * 查询用户
     */
    @Test
    public void TestQueryById() {
        Long id = 4L;
        User user = userService.queryById(id);
        log.info("查询用户：user = {}", user);
    }

    /**
     * 查询用户
     */
    @Test
    public void TestQueryByUsername() {
        String username = "wxy123";
        User user = userService.queryByUsername(username);
        log.info("查询用户：user = {}", user);
    }

    /**
     * 查询用户
     */
    @Test
    public void TestQueryByEmail() {
        String email = "wxy@qq.com";
        User user = userService.queryByEmail(email);
        log.info("查询用户：user = {}", user);
    }

    /**
     * 修改密码
     */
    @Test
    public void TestUpdatePassword() {
        Long id = 4L;
        String oldPassword = "wxy123";
        String newPassword = "wxy123456";
        int update = userService.updatePassword(id, oldPassword, newPassword);
        log.info("修改密码：update = {}", update > 0);
    }

    /**
     * 修改用户
     */
    @Test
    public void TestUpdateUser() {
        Long id = 4L;
        String email = "";
        String nickName = "小王子";
        String photo = "";
        int update = userService.updateUser(id, email, nickName,photo);
        log.info("修改用户：update = {}", update > 0);
    }
}
