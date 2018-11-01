package com.lij.controllers;

import com.lij.dao.UserMapper;
import com.lij.entity.ApiResponse;
import com.lij.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.isNaN;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @PostMapping("/insertuser")
    public ApiResponse insertuser(User user){
        userMapper.insert(user);
        return new ApiResponse("default","successful operation");
    }

    @PostMapping
    public ApiResponse UserArray(ArrayList<User> list){
        userMapper.insertAll2(list);
        return new ApiResponse("default","successful operation");
    }
    @PostMapping
    public ApiResponse UserList(List<User> list){
        userMapper.insertAll3(list);
        return new ApiResponse("default","successful operation");
    }

    @GetMapping("/login")
    public ApiResponse login(Model model, HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<User> entities =userMapper.selectAll();
        if(entities !=null){
            return new ApiResponse(200,"err","successful operation");
        }else {
            return new ApiResponse(400,"err","Invalid username supplied");
        }
    }


    //暂时没有写
    @GetMapping("/logout")
    public ApiResponse logout(int ostatus){
        userMapper.udateLogout(ostatus);
        return new ApiResponse("default","successful operation");
    }




    @GetMapping("/username")
    public ApiResponse username(String name,Model model){
        User user =  userMapper.selectByPrimaryName(name);
        if(name !=null || name !=""){
            if(user !=null){
                return new ApiResponse(200,"err","successful operation");
            }else {
                return new ApiResponse(404,"err","User not found");
            }
        }else {
            return new ApiResponse(400,"err","Invalid username supplied");
        }
    }

    @PutMapping("/userupdate")
    public ApiResponse userUpdate(User user){
        if(user != null){
            if(userMapper.updateByPrimaryKey(user) > 0){
                return new ApiResponse();
            }else {
                return new ApiResponse(404,"err","User not found");
            }
        }else {
            return new ApiResponse(400,"err","Invalid username supplied");
        }
    }

    @DeleteMapping("/userdel/{uid}")
    public ApiResponse userdel(@PathVariable("uid") int uid){
        if(isNaN(uid)){
            if(userMapper.deleteByPrimaryKey(uid) > 0){
                return new ApiResponse();
            }else {
                return new ApiResponse(404,"err","User not found");
            }
        }else {
            return new ApiResponse(400,"err","Invalid username supplied");
        }

    }
}
