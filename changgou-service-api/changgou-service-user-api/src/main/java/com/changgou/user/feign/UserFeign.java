package com.changgou.user.feign;

import com.changgou.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user", path = "/user")
public interface UserFeign {
    //根据用户名获取与欧诺个户的信息
    @GetMapping("/load/{id}")
    Result<User> findById(@PathVariable(name = "id")String id);
}
