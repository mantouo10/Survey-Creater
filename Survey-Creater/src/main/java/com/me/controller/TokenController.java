package com.me.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.me.beans.User;
import com.me.security.TokenCreater;
import com.me.service.UserService;
import com.me.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @ClassName TokenController
 * @Description TODO
 * @Author xufeng
 * @Data 2019/3/1 14:20
 * @Version 1.0
 **/
@RestController
public class TokenController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @GetMapping("/tokenCheck")
    @ResponseBody
    public HashMap tokenCheck(@RequestHeader("Authorization")String token, HttpSession session){
        HashMap hashMap = TokenCreater.parseJWT(token);
        if (redisUtil.get((String) hashMap.get("id")).equals(token)){
            User user = userService.selectOne(new EntityWrapper<User>().eq("email",hashMap.get("id")));
            session.setAttribute("userid",user.getId());
            hashMap.put("success",true);
            hashMap.put("manager",user.getIdentify()==0);
            return hashMap;
        }else {
            System.out.println("======error======");
            hashMap.put("success",true);
            hashMap.put("res_code",401);
            return hashMap;
        }
    }
}
