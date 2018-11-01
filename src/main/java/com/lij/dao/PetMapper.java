package com.lij.dao;

import com.lij.entity.Pet;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PetMapper {

    List<Pet> selectAll();

    int deleteByPrimaryKey(Integer pId);

    int insert(Pet record);

    int insertSelective(Pet record);

    Pet selectByPrimaryKey(Integer pId);

    Pet selectByPrimaryName(String name);

    Pet selectByPrimarycId(Integer cId);

    int updateByPrimaryKeySelective(Pet record);

    int updateByPrimaryKey(Pet record);

    int UpdatePhoto(String photo);
}