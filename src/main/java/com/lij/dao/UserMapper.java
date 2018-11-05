package com.lij.dao;

import com.lij.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    List<User> selectAll();

    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPrimaryName(String name);

   // int insertAll2(ArrayList<User> list);

   // int insertAll3(List<User> list);

    int udateLogout(int status);
}