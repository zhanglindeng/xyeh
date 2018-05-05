package com.dengzhanglin.xyeh.mapper;

import com.dengzhanglin.xyeh.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryMapper {

    // 返回添加成功的行数
    Long add(CategoryEntity categoryEntity);

    // 返回被删除的行数
    Integer delete(Integer id);

    List<CategoryEntity> getAll();

    /**
     * 获取顶级分类，pid = 0
     *
     * @return List<CategoryEntity>
     */
    List<CategoryEntity> getTopCategories();
}
