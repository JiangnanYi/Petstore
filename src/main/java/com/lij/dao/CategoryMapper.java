package com.lij.dao;

import com.lij.entity.Category;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {

    List<Category> selectAll();

    int deleteByPrimaryKey(Integer cId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}