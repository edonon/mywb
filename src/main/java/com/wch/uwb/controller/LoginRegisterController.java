package com.wch.uwb.controller;

import com.wch.uwb.entity.UserEntity;
import com.wch.uwb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class LoginRegisterController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping(value="/", method=RequestMethod.GET)
    private String index(Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "index";
    }
    @RequestMapping(value="/register", method=RequestMethod.GET)
    private String register(Model model){
        model.addAttribute("userEntity", new UserEntity());
        return "register";
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private String register(UserEntity userEntity, Model model, HttpServletRequest request){
        int flag = 0;
        if(userEntity.getLogin().isEmpty()){
            flag = 1;
            model.addAttribute("ERRLogin", "账号错误");
        }
        if(userEntity.getPasswd().isEmpty()){
            flag = 1;
            model.addAttribute("ERRPasswd", "密码错误");
        }
        if(userEntity.getUserName().isEmpty()){
            flag = 1;
            model.addAttribute("ERRUserName", "用户名错误");
        }
        if(userEntity.getSex()==null){
            flag = 1;
            //model.addAttribute("ERRSex", userEntity.toString());
            model.addAttribute("ERRSex", "性别错误");
        }
        if(userEntity.getResume().isEmpty()){
            flag = 1;
            model.addAttribute("ERRResume", "简介错误");
        }

        if(flag == 1){
            flag = 0;
            return "register";
        }
        System.out.println(userEntity.toString());
        Date now=new java.sql.Date(System.currentTimeMillis());
        userEntity.setRegisterTime(now);
        userEntity.setAuthor(6);
        userEntity.setPhoto("E:\\WBresources\\none.jpg");
        userMapper.insert(userEntity);
        userEntity = userMapper.getOneByLogin(userEntity.getLogin());
        request.getSession().setAttribute("id",userEntity.getId());
        return "index";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    private Map login(@RequestBody UserEntity userEntity, Model model, HttpServletRequest request) {
        UserEntity uE = userMapper.getOneByLogin(userEntity.getLogin());
        System.out.println(userEntity.getLogin()+","+userEntity.getPasswd());

        Map<String, Object> result = new LinkedHashMap<>();
       if(uE != null && uE.getPasswd().equals(userEntity.getPasswd()) ){
           request.getSession().setAttribute("id",uE.getId());
           result.put("message", "loginSuccess");
       }else{
           result.put("message", "密码错误");
       }
       return result;
    }

}
