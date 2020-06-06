package com.ren.TcpGateWayNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;

/**
 * @author Ren
 * **/
public class TcpGateWayServerHandlerOut extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        /*将数据的解析和封装以及与数据库的存储封装为了一个Task任务交给业务线程池处理*/
        TcpGateWayServer.businessExecutorService.execute(new NodeMessageTask(msg,ctx));
        /*服务端向客户端反馈消息已收到数据*/
        ByteBuf byteBuf = Unpooled.copiedBuffer("已经收到数据数据...", CharsetUtil.UTF_8);
        ctx.write(byteBuf);
        ctx.flush();
    }




}



