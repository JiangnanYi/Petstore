package com.lij.controllers;

import com.lij.dao.OrderMapper;
import com.lij.entity.ApiResponse;
import com.lij.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static java.lang.Double.isNaN;

@Controller
@RequestMapping("/Order")
public class OrderController {
    @Autowired
  private OrderMapper orderMapper;

    @RequestMapping()
    public String index(Model model, HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        List<Order> orders =orderMapper.selectAll();
        model.addAttribute("orders",orders);
        return "index";
    }

    @GetMapping
    public ApiResponse OrderStatus(String status){
       if(orderMapper.selectByPrimarypStatus(status) !=null){
           return new ApiResponse();
       }else {
           return new ApiResponse(200,"err","successful operation");
       }

    }

    @PostMapping("/oradd")
    public ApiResponse OrderInsert(Order order){
       int c =  orderMapper.insert(order);
       if(c > 0){
           return new ApiResponse(200,"err","successful operation");
       }
        return new ApiResponse(400,"err","Invalid Order");
    }

    @GetMapping("/sel/{pid}")
    public ApiResponse OrderselectByPrimarypId(@PathVariable("pid") int pid){
        if(isNaN(pid)){
            if(orderMapper.selectByPrimarypId(pid) != null){
                return new ApiResponse(200,"err","successful operation");
            }else {
                return new ApiResponse(404,"err","Order not found");
            }
        }else {
            return new ApiResponse(400,"err","Invalid Order");
        }

    }

    @DeleteMapping("/del/{oid}")
    public ApiResponse deleteByPrimaryKey(@PathVariable("oid") int oid){
       int c =  orderMapper.deleteByPrimaryKey(oid);
       if(isNaN(oid)){
           if(c > 0){
               return new ApiResponse();
           }else {
               return new ApiResponse(404,"err","Order not found");
           }
       }else {
           return new ApiResponse(400,"err","Invalid Order");
       }
    }
}
