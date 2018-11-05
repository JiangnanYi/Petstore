package com.lij.dao;

import com.lij.entity.Tag;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagMapper {

    List<Tag> selectAll();

    int deleteByPrimaryKey(Integer tId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer tId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}