package com.changgou.oauth.config;

import com.changgou.user.feign.UserFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/***
 * 从数据库中获取用户名和密码进行匹配是否用户名和面正确
 * @author ljh
 * @packagename com.itheima.config
 * @version 1.0
 * @date 2020/1/10
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("获取到的用户名是：" + username);
        String permission = "ROLE_ADMIN,ROLE_USER";//设置权限
        //1、从数据库获取该用户名对应的密码，然后交给框架进行匹配
          //1.1 创建一个feign接口，正在changgou-service-user-api中
          //1.2 changgou-service-user中实现业务接口
          //1.3 在changgou-user-oauth中添加依赖
          //1.4 启用feignclients
          //1.5 注入，调用
         //1.6 需要去user微服务放行
        Result<com.changgou.user.pojo.User> result = userFeign.findById(username);
        if (result.getData() == null){
            return null;
        }
        //加密过的
        String password = result.getData().getPassword(); //加密过的
        //2、判断用户是否存在，如果存在，抛出异常
        //3、获取用户的密码，然后交给框架本身。自己进行校验
//        return new User(username, passwordEncoder.encode("123456"),
//                AuthorityUtils.commaSeparatedStringToAuthorityList(permission));
        return new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permission));
    }
}
