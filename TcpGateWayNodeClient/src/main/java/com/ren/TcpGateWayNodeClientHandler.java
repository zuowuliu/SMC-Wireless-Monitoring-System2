package com.ren;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
public class TcpGateWayNodeClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String o) throws Exception {
        System.out.println("客户端 Client：" + channelHandlerContext.channel().localAddress().toString().substring(1));
        System.out.println("服务端 response：" + o.trim());
        System.out.println(Thread.currentThread().getName()+"当前线程名字");
    }
}