package com.kyzh.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kyzh.user.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消费者的前端控制器，它的作用是暴露给使用的用户提供访问URL的
 * @author
 * @Company
 */
@Controller//当前类是控制器
public class HelloController {

    @Reference//要选择com.alibaba提供的
    private HelloService helloService;

    /**
     * 此方法应该调用服务的提供者
     * @param name
     * @return
     */
    @RequestMapping("/hello")//暴露出来的访问映射  http://xxx:端口/hello
    public @ResponseBody String hello(String name){
        return helloService.sayHello(name);
    }
}
