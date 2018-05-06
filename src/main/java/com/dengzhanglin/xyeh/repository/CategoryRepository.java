package com.dengzhanglin.xyeh.repository;

import com.dengzhanglin.xyeh.entity.CategoryEntity;
import com.dengzhanglin.xyeh.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryRepository {
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryRepository(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public Integer delete(Integer id) {
        return  this.categoryMapper.delete(id);
    }

    public Integer update(CategoryEntity categoryEntity) {
        return this.categoryMapper.update(categoryEntity);
    }

    public CategoryEntity findById(Integer id) {
        return this.categoryMapper.findById(id);
    }

    public List<CategoryEntity> subCategories(Integer pid) {
        return this.categoryMapper.subCategories(pid);
    }

    public List<CategoryEntity> all() {
        return this.categoryMapper.getAll();
    }

    public List<CategoryEntity> getTopCategories() {
        return this.categoryMapper.getTopCategories();
    }

    public CategoryEntity add(String name, Integer pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(name);
        categoryEntity.setPid(pid);
        if (this.categoryMapper.add(categoryEntity) > 0) {
            return categoryEntity;
        }

        return null;
    }
}
