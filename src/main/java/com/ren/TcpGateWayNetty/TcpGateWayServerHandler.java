package com.ren.TcpGateWayNetty;

import com.ren.bean.IndustrialMessage;
import com.ren.bean.SpringBeanFactoryUtils;
import com.ren.service.TcpGateWayMessageService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

public class TcpGateWayServerHandler extends SimpleChannelInboundHandler<String> {

    //存当前连接的客户端的数量
    public static List<Channel> channels = new ArrayList<>();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //设置通道就绪事件
        /**
         * 显示上线了的网关
         * */
        Channel channel = ctx.channel();
        System.out.println("客户端："+ channel.remoteAddress().toString().substring(1)+"上线...");
        channels.add(channel);
        System.out.println("目前在线的网关个数：" + channels.size() + "个");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //设置通道关闭事件
        /**
         * 显示离线了的网关
         * */
        Channel channel = ctx.channel();
        System.out.println("客户端："+ channel.remoteAddress().toString().substring(1)+"离线...");
        channels.remove(channel);
        System.out.println("目前在线的网关个数：" + channels.size() + "个");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //设置异常处理事件
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel inChannel = channelHandlerContext.channel();
        if (s != null) {
            //TcpGateWayClient发过来的数据即s
            String[] arrMsg = s.split(",");
            for (int i = 0; i < arrMsg.length; i++) {
                // 在此处解析报文
                String sendMsg = arrMsg[i].substring(arrMsg[i].indexOf("#") + 1, arrMsg[i].indexOf("/"));
                //设置读取事件,并且把读取到的数据存储到数据库中
                String[] result = sendMsg.split(" ");
                //将解析出来的数据封装为industrialMessage(industrialMessageForTcp)
                IndustrialMessage industrialMessageForTcp = new IndustrialMessage();
                industrialMessageForTcp.setUser(result[1]);
                industrialMessageForTcp.setGt(result[3]);
                industrialMessageForTcp.setTime(result[5]);
                industrialMessageForTcp.setA(result[8]);
                industrialMessageForTcp.setB(result[10]);
                industrialMessageForTcp.setC(result[12]);
                industrialMessageForTcp.setD(result[14]);
                TcpGateWayMessageService tcpGateWayMessageService = (TcpGateWayMessageService) SpringBeanFactoryUtils.getBean("TcpGateWayMessageService");
                tcpGateWayMessageService.insertGateWayMessage(industrialMessageForTcp);
                System.out.println("已更新"+(i+1)+"条新的数据到数据库...");
                System.out.println("**************************");
            }
        }
        ByteBuf byteBuf = Unpooled.copiedBuffer("已经收到数据...", CharsetUtil.UTF_8);
        inChannel.writeAndFlush(byteBuf);
    }
}
