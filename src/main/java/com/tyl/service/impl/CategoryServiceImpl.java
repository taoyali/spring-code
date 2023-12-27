package com.tyl.service.impl;

import com.tyl.mapper.CategoryMapper;
import com.tyl.pojo.Category;
import com.tyl.service.CategoryService;
import com.tyl.utils.ThreadLocalUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("userId");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("userId");
        List<Category> list = categoryMapper.list(userId);
        return list;
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

}