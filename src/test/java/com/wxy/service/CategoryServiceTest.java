package com.wxy.service;

import com.wxy.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author wxy
 * @Date 19-8-30 下午5:04
 * @Description TODO
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @Test
    public void TestSaveCategory() {
        Long userId = 4L;
        String name = "默认分类";
        int save = categoryService.saveCategory(name, userId);
        log.info("新增分类：save = {}", save > 0);
    }

    /**
     * 查询所有分类
     */
    @Test
    public void TestFindAll() {
        Long userId = 4L;
        List<Category> list = categoryService.findAll(userId, null);
        log.info("查询所有分类：list = {}", list);
    }

    /**
     * 删除分类
     */
    @Test
    public void TestDeleteCategory() {
        Long id = 1L;
        Long userId = 4L;
        boolean bool = categoryService.deleteCategory(id, userId);
        log.info("删除分类；bool = {}", bool);
    }

    @Test
    public void testUpdateCategory() {
        Long id = 0L;
        String name = "123";
        int update = categoryService.updateCategory(id, name);
        log.info("修改分类：update = {}", update > 0);
    }
}
