package com.kyzh.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kyzh.user.service.HelloService;

/**
 * @author
 * @Company
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
