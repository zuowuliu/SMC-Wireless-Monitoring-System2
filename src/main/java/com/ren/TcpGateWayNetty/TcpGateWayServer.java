package com.ren.TcpGateWayNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TcpGateWayServer{
    @RequestMapping("/tcpGateWay1ServerOpen")
    public ModelAndView initServer(){
        //设置Server的端口号
        int port = 9090;
        //设置两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //配置
        ServerBootstrap serverConfig = new ServerBootstrap();
        serverConfig.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 15)//设置可以在线的客户端数量为15个
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //socketChannel.pipeline().addLast(new ChatServerHandler());
                        ChannelPipeline channelPipeline = socketChannel.pipeline();
                        //往pipeline中添加一个解码器
                        channelPipeline.addLast("Decoder",new StringDecoder());
                        //往pipeling中添加Server的业务处理类
                        channelPipeline.addLast(new TcpGateWayServerHandler());
                        //往pipeline中添加一个编码器
                        channelPipeline.addLast("Encoder",new StringEncoder());
                    }
                });
        System.out.println("TcpGateWayServer is Ready...");
        try {
            serverConfig.bind(port).sync();//sync方法是同步阻塞的
//            ChannelFuture channelFuture = serverConfig.bind(port).sync();//sync方法是同步阻塞的
//            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TcpGateWayServer is Start....");
//        bossGroup.shutdownGracefully();
//        workerGroup.shutdownGracefully();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
