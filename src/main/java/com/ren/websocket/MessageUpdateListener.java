package com.ren.websocket;

import com.ren.bean.IndustrialMessage;
import com.ren.bean.SpringBeanFactoryUtils;
import com.ren.service.IndustrialService;

import java.util.List;

public class MessageUpdateListener implements Runnable{
    //用于初次缓存数据库中信息的个数
    private int num;

    //用于轮询数据库中信息的个数用于和num来做比较，以判断是否数据发生了更新
    private int new_num;

    //用于作为页面是否发生关闭的flag
    private boolean stopMe = true;

    public void stopMe(){
        stopMe = false;
    }

    @Override
    public void run() {
        //用于在webSocket中得到service对象用于对于数据库进行操作
        IndustrialService industrialService =(IndustrialService) SpringBeanFactoryUtils.getBean("IndustrialService");

        //查找当前网关的数据信息的个数
        num = industrialService.selectIndustrialMessage();

        //查找数据库中的所有网关信息
        List<IndustrialMessage> list = industrialService.checkIndustrialMessage();

        //新建WebSocket对象
        NodeMessageWebsocket nodeMessageWebsocket = new NodeMessageWebsocket();

            //用于遍历输出网关的信息
            for(int i=0; i<list.size(); i++){
                String resp = list.get(i).toString();
                nodeMessageWebsocket.onMessage(resp);
            }
            //开启轮询
            while(stopMe){
                new_num = industrialService.selectIndustrialMessage();
                if(num != new_num) {
                    //为了在控制台看信息更直观，把这条注释掉，不动态看数据库是否发生变化了
                    //System.out.println("change");
                    num = new_num;

                    //若数据个数发生了变化就通过onMessage向客户端发送更新指令
                    nodeMessageWebsocket.onMessage("change");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }


    }
}
