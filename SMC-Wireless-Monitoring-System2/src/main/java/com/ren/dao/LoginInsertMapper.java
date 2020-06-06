package com.ren.dao;

import org.apache.ibatis.annotations.Param;

public interface LoginInsertMapper {
    void insertLoginInfo(@Param("userName") String userName, @Param("loginTime") String loginTime);
}
