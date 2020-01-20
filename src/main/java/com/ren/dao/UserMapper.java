package com.ren.dao;

import com.ren.bean.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    User selectByUserName(@Param("userName") String userName, @Param("passWord") String passWord);
}
