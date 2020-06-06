package com.ren.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/nodeWebSocket")
/**
 * 建立WS连接与我的客户端的浏览器之间
 * */
public class NodeMessageWebsocket {

    //建立连接的会话
    private Session session = null;

    //加载监听线程
    MessageUpdateListener thread1 = new MessageUpdateListener();
    Thread thread = new Thread(thread1);

    //配置所有连接到此WebSocket节点的客户端的集合
    private static CopyOnWriteArraySet<NodeMessageWebsocket> nodeMessageWebsocketsSet = new CopyOnWriteArraySet<NodeMessageWebsocket>();

    //当连接建立的时候就开启监听线程
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        nodeMessageWebsocketsSet.add(this);
        thread.start();
    }

    //当关闭网页的时候关闭线程，同时在集合中移除此连接的客户端s
    @OnClose
    public void onClose() {
        /**
         * 1.第一种情况是退出监控空页面的时候会断开我的websocket链接，所以此时会设置一次标志位从而退出我的监听线程
         * 2.还有就是更新数据的时候，会冲刷一次页面即断开websocket然后再重连一次，所以会有一次断开监听线程的过程
         * 所以每次更新完数据之后控制台会有一条监听线程已关闭的信息
         * */
        thread1.stopMe();
        nodeMessageWebsocketsSet.remove(this);
    }

    //向客户端发送信息及指令
    @OnMessage
    public synchronized void onMessage(String message) {
        try {
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @throws IOException 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     *                     当没有发生变化的时候就正常输出
     */
    //如果在线程中已经判断出来是数据库已经发生了变化就会传入change的指令，同时向客户端发送“1”的指令告知
    //如果没有发生改变的话就直接正常的把数据中数据信息发送给客户端
    public synchronized void sendMessage(String message) throws IOException {
        if (message == "change") {
            for (NodeMessageWebsocket item : nodeMessageWebsocketsSet) {
                item.session.getBasicRemote().sendText("1");
            }
        }else {
            for (NodeMessageWebsocket item : nodeMessageWebsocketsSet) {
                item.session.getBasicRemote().sendText(message);
            }
        }

    }
}