package com.ren.service;

import com.ren.bean.IndustrialMessage;
import com.ren.dao.TcpGateWayMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcpGateWayMessageService {


    @Autowired
    TcpGateWayMessageMapper tcpGateWayMessageMapper;
    //将网关传过来的数据存储到数据库中
    public void insertGateWayMessage(IndustrialMessage industrialMessage){
        tcpGateWayMessageMapper.insertGateWayMessage(industrialMessage);
    }
}
