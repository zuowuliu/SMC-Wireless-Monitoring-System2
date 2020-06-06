package com.ren.TcpGateWayNetty;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ren
 * **/

public class TcpGateWayServerHandler extends SimpleChannelInboundHandler<String> {

    //存当前连接的客户端的数量
    public static List<Channel> channels = new ArrayList<>();


    @Override
    public  void channelActive(ChannelHandlerContext ctx) throws Exception {
        //设置通道就绪事件
        /**
         * 显示上线了的网关
         * */
        Channel channel = ctx.channel();
        System.out.println("客户端：" + channel.remoteAddress().toString().substring(1) + "上线...");
        channels.add(channel);
        System.out.println("目前在线的网关个数：" + channels.size() + "个");
    }

    @Override
    public  void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //设置通道关闭事件
        /**
         * 显示离线了的网关
         * */
        Channel channel = ctx.channel();
        System.out.println("客户端：" + channel.remoteAddress().toString().substring(1) + "离线...");
        channels.remove(channel);
        System.out.println("目前在线的网关个数：" + channels.size() + "个");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //设置异常处理事件
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String)msg;
        if (s != null) {
            ctx.write(s);
            ctx.flush();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }
}