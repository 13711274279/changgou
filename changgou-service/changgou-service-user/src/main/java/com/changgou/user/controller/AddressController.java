package com.changgou.user.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.user.pojo.Address;
import com.changgou.user.service.AddressService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController extends AbstractCoreController<Address>{

    private AddressService  addressService;

    @Autowired
    public AddressController(AddressService  addressService) {
        super(addressService, Address.class);
        this.addressService = addressService;
    }

    //获取登录用怒明所对应的地址列表
    @GetMapping("/user/list")
    public Result<List<Address>> list(){
        //获取当前用户名
        String username = "zhangsan";
        List<Address> addressList = addressService.list(username);
        return new Result(true, StatusCode.OK,"查询成功",addressList);
    }
}
