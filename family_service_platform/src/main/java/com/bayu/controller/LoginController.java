package com.bayu.controller;


import com.alibaba.fastjson.JSONObject;
import com.bayu.bean.TblUserRecord;
import com.bayu.returnJson.Permission;
import com.bayu.returnJson.Permissions;
import com.bayu.returnJson.ReturnObject;
import com.bayu.returnJson.UserInfo;
import com.bayu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin与CorsConfig工具类二选一
//①@CrossOrigin //所有域名均可访问该类下所有接口
//②@CrossOrigin("https://blog.csdn.net") // 只有指定域名可以访问该类下所有接口
//③@CrossOrigin(origins ="*",allowedHeaders= "*",methods = {} ,allowCredentials = "true")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/auth/2step-code")
    public boolean test(){
        System.out.println("ceshi 前端框架自带一个验证规则");
        return  true;
    }

     @ResponseBody
     @RequestMapping("/auth/login")
     //public String login(@RequestBody Map<String, Object> map){
     public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){

            System.out.println("login");
            //System.out.println(map);
            TblUserRecord tblUserRecord = loginService.login(username,password);
            //Object o = JSONObject.toJSON(tblUserRecord);
            tblUserRecord.setToken(tblUserRecord.getUserName());
            session.setAttribute("userRecord",tblUserRecord);
            ReturnObject returnObject = new ReturnObject(tblUserRecord);
            System.out.println("session id 1 :" +session.getId());

            //System.out.println(tblUserRecord);
            return JSONObject.toJSONString(returnObject);
    }


    @ResponseBody
    @RequestMapping("/user/info")
    public  String getInfo(HttpSession  session){
           /* System.out.println("session id 2 :" +session.getId());
            System.out.println("sessionGet: " + session.getAttribute("userRecord"));*/
            TblUserRecord tblUserRecord = (TblUserRecord)session.getAttribute("userRecord");
            //(数组的拼接就是下面写的)
            //获取模块信息
            String[] split ;
            split = tblUserRecord.getTblRole().getRolePrivileges().split("-");// 数组里的属性值
            //先创建一个总体的集合对象
            Permissions permissions =new Permissions();
            //再创建一个具体的集合
            List<Permission> permissionList =new ArrayList<Permission>();

            for (String s :split){
                permissionList.add(new Permission(s));
            }
            permissions.setPermissions(permissionList);
            UserInfo userinfo =new UserInfo(tblUserRecord.getUserName(),permissions);
            return JSONObject.toJSONString(new ReturnObject(userinfo));
    }

    @RequestMapping("/auth/logout1")
    public void logOut(HttpSession session){
            System.out.println("logout1");
            session.invalidate();
    }
}
