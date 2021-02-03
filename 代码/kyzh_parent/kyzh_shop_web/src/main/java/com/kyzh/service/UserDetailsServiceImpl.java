package com.kyzh.service;

import com.kyzh.pojo.TbSeller;
import com.kyzh.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {


    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    //认证方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=登录后调用=");
        //根据sellerId获取Seller对象
        TbSeller seller = sellerService.findOne(username);

        if(seller!=null && "1".equals(seller.getStatus())){
            //在角色列表中添加角色
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            //1.登录用户名  2.数据库中的密码  3.当期用户角色列表
            return new User(username, seller.getPassword(), authorities);
        }
        return null;
    }
}
