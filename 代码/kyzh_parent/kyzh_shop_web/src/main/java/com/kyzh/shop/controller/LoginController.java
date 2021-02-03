package com.kyzh.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/showName")
    public Map showName(){
        //springSecurity中获取用户信息
        Map map=new HashMap();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("name",name);
        return map;
    }
}
