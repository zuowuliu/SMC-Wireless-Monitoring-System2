package com.ren.TcpGateWayNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;


import javax.servlet.ServletContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class TcpGateWayServer implements InitializingBean,ServletContextAware {
    /**
     * 实现了InitializingBean和ServletContextAware这两个接口之后可以实现随容器启动而初始化这个类
     * 为IOC容器中的bean的时候应该怎样初始化，初始化逻辑在setServletContext()中
     */
    /*在服务端维护的一个业务线程*/
    public static ExecutorService businessExecutorService = BusinessExecutorService.getInstance().getThreadPoolExecutor(4, 20,
            2000L, TimeUnit.SECONDS, 200);
    /*让业务线程并发的操作*/
    public static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("已Ready..");

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        //设置Server的端口号
        int port = 9090;
        //设置两个线程组(对应的NIO模型下的selector)
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
        //配置
        ServerBootstrap serverConfig = new ServerBootstrap();
        serverConfig.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
                .option(ChannelOption.SO_BACKLOG, 20)
                // 处理新连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel serverSocketChannel) throws Exception {
                        //socketChannel.pipeline().addLast(new ChatServerHandler());
                        ChannelPipeline channelPipeline = serverSocketChannel.pipeline();
                        //往pipeline中添加一个解码器
                        channelPipeline.addLast("Decoder", new StringDecoder());
                        channelPipeline.addLast(new TcpGateWayServerHandlerOut());
                        //往pipeling中添加Server的业务处理类
                        channelPipeline.addLast(new TcpGateWayServerHandler());
                        //往pipeline中添加一个编码器
                        channelPipeline.addLast("Encoder", new StringEncoder());
                    }
                });
//        /**
//         * 优雅地异步退出netty
//         * */
//        /*绑定端口，同步等待成功，是通过web容器的端口绑定*/
//        ChannelFuture channelFuture = serverConfig.bind(port);
//        System.out.println("TcpGateWayServer is Ready...");
//        /*采用异步的方法退出netty 通过异步的方法不会被阻塞*/
//        channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                System.out.println(future.channel().toString() + " 链路关闭");
//                bossGroup.shutdownGracefully();
//                workerGroup.shutdownGracefully();
//            }
//        });

        serverConfig.bind(port);
//            ChannelFuture future = serverConfig.bind(port).sync();
//            future.channel().closeFuture().sync();
//        }
// catch (InterruptedException e) {
//                e.printStackTrace();
//        }
        System.out.println("TcpGateWayServer is Start....");
    }

    }
