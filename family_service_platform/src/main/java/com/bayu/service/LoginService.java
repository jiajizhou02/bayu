package com.bayu.service;

import com.bayu.bean.TblUserRecord;
import com.bayu.mapper.TblUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginService {

    @Autowired
    private TblUserRecordMapper tblUserRecordMapper;

    public TblUserRecord login(String username, String password){
        return tblUserRecordMapper.login(username,password);
    }

    //public void login(@RequestParam String username,@RequestParam String password){
        //System.out.println("login");
    //}
}
