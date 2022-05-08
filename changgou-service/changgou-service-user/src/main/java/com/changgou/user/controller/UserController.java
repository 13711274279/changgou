package com.changgou.user.controller;

import com.alibaba.fastjson.JSON;
import com.changgou.core.AbstractCoreController;
import com.changgou.user.pojo.User;
import com.changgou.user.service.UserService;
import entity.JwtUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends AbstractCoreController<User>{

    private UserService  userService;

    @Autowired
    public UserController(UserService  userService) {
        super(userService, User.class);
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> Login, HttpServletResponse response){
        if  (Login.isEmpty()){
            return new Result(false, StatusCode.ERROR, "请输入用户或密码");
        }
        User user = userService.selectByPrimaryKey(Login.get("username"));
        if (user == null){
            return new Result(false, StatusCode.ERROR, "未注册用户");
        }

        if(!Login.get("password").equals(user.getPassword())){
            return new Result(false, StatusCode.ERROR, "用户或密码错误");
        }
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("username", Login.get("username"));
        info.put("role","adimin");
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);

        //放在Cookie
        Cookie cookie = new Cookie("Authorization", jwt);
        cookie.setPath("/");

        response.addCookie(cookie);
        return new Result(true, StatusCode.OK, "登录成功", jwt);

    }

    // 区分这个专门用于fegin调用
    @GetMapping("/load/{id}")
    Result<User> findById(@PathVariable(name = "id")String id){
        User user = userService.selectByPrimaryKey(id);
        return new Result(true, StatusCode.OK, "查询成功", user);
    }

    @GetMapping("/points/add")
    public Result addPoints(@RequestParam(name = "username") String username,
                            @RequestParam(name = "points") Integer points){
        Integer count  =- userService.addPoints(username, points);
        if(count>0){
            return new Result(true, StatusCode.OK, "更新成功");
        }else {
            return new Result(false, StatusCode.OK, "更新失败");
        }
    }

}
