package com.ren.dao;


import com.ren.bean.LoginInformation;

import java.util.List;


public interface LoginMapper {
    List<LoginInformation> selectLoginInfo();
}
