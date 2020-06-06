package com.ren.service;


import com.ren.bean.LoginInformation;
import com.ren.bean.User;
import com.ren.dao.LoginInsertMapper;
import com.ren.dao.LoginMapper;
import com.ren.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    LoginInsertMapper loginInsertMapper;

    public User loginServiceVerify(String userName,String passWord){
        User user = userMapper.selectByUserName(userName,passWord);

        if(user.getPassWord().equals(passWord)){
            return user;
        }else{
            return null;
        }

    }

    public List loginInfoService(){
        List<LoginInformation> list = loginMapper.selectLoginInfo();
        return list;
    }

    public void insertLoginInfo(String userName, String loginTime){
        loginInsertMapper.insertLoginInfo(userName,loginTime);
    }
}
