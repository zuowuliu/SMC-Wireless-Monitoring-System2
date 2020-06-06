package com.ren.TcpGateWayNetty;

import com.ren.bean.IndustrialMessage;
import com.ren.bean.SpringBeanFactoryUtils;
import com.ren.service.TcpGateWayMessageService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeMessageTask implements Runnable {
    private Object msg;
    private ChannelHandlerContext ctx;
    public NodeMessageTask(Object msg, ChannelHandlerContext ctx){
        this.msg=msg;
        this.ctx=ctx;
    }
    @Override
    public void run() {
        try {
            TcpGateWayServer.linkedBlockingQueue.put(1);
            parseAndParckagingNodeMessage(msg);
            ByteBuf byteBuf = Unpooled.copiedBuffer("已经存储数据数据...", CharsetUtil.UTF_8);
            //Thread.sleep(5000);
            /**
             * 当业务线程处理完这个任务之后再调用ctx的write()方法，将反馈的信息通过IO线程返回给客户端
             * */
            ctx.write(byteBuf);
            ctx.flush();
            TcpGateWayServer.linkedBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*解析并封装数据完成数据的插入*/
    private void parseAndParckagingNodeMessage(Object msg){
        String[] arrMsg = msg.toString().split(",");
        for (int i = 0; i < arrMsg.length; i++) {
            String userName="#user:(.*?)gt:";  // user
            String gtName="gt:(.*?)time:";  // gt
            String time="time:(.*?)n001:A";  // 通道B
            String A="n001:A(.*?)B";
            String B="B(.*?)C";
            String C="C(.*?)D";
            String D="D(.*?)E";
            String E="E(.*?)F";
            String F="F(.*?)G";
            String G="G(.*?)H";
            String H="H(.*?)/";
            IndustrialMessage industrialMessageForTcp = new IndustrialMessage();
            industrialMessageForTcp.setUser(getSubUtilSimple(arrMsg[i], userName));
            industrialMessageForTcp.setGt(getSubUtilSimple(arrMsg[i], gtName));
            industrialMessageForTcp.setTime(getSubUtilSimple(arrMsg[i], time));
            industrialMessageForTcp.setA(getSubUtilSimple(arrMsg[i], A));
            industrialMessageForTcp.setB(getSubUtilSimple(arrMsg[i], B));
            industrialMessageForTcp.setC(getSubUtilSimple(arrMsg[i], C));
            industrialMessageForTcp.setD(getSubUtilSimple(arrMsg[i], D));
            industrialMessageForTcp.setE(getSubUtilSimple(arrMsg[i], E));
            industrialMessageForTcp.setF(getSubUtilSimple(arrMsg[i], F));
            industrialMessageForTcp.setG(getSubUtilSimple(arrMsg[i], G));
            industrialMessageForTcp.setH(getSubUtilSimple(arrMsg[i], H));
            insertNodeMessage(industrialMessageForTcp);
        }
    }
    /*插入数据库的方法*/
    private void insertNodeMessage(IndustrialMessage industrialMessageForTcp) {
        TcpGateWayMessageService tcpGateWayMessageService = (TcpGateWayMessageService) SpringBeanFactoryUtils.getBean("TcpGateWayMessageService");
        tcpGateWayMessageService.insertGateWayMessage(industrialMessageForTcp);
        System.out.println("**************************");
    }

    /*用于正则解析数据包*/
    private static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }
}
