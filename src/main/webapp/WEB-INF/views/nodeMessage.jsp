<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/19 0019
  Time: 下午 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网关数据</title>
    <link rel="stylesheet" href="../../static/css/nodeMessage.css">
    <script src="static/echarts.min.js"></script>

</head>
<body>

<svg width="380px" height="500px" viewBox="0 0 837 1045" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:sketch="http://www.bohemiancoding.com/sketch/ns">
    <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd" sketch:type="MSPage">
        <path d="M353,9 L626.664028,170 L626.664028,487 L353,642 L79.3359724,487 L79.3359724,170 L353,9 Z" id="Polygon-1" stroke="#007FB2" stroke-width="6" sketch:type="MSShapeGroup" ></path>
        <path d="M78.5,529 L147,569.186414 L147,648.311216 L78.5,687 L10,648.311216 L10,569.186414 L78.5,529 Z" id="Polygon-2" stroke="#EF4A5B" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M773,186 L827,217.538705 L827,279.636651 L773,310 L719,279.636651 L719,217.538705 L773,186 Z" id="Polygon-3" stroke="#795D9C" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M639,529 L773,607.846761 L773,763.091627 L639,839 L505,763.091627 L505,607.846761 L639,529 Z" id="Polygon-4" stroke="#F2773F" stroke-width="6" sketch:type="MSShapeGroup"></path>
        <path d="M281,801 L383,861.025276 L383,979.21169 L281,1037 L179,979.21169 L179,861.025276 L281,801 Z" id="Polygon-5" stroke="#36B455" stroke-width="6" sketch:type="MSShapeGroup"></path>
    </g>
</svg>
<div class="message-box">
    <h1>数据信息</h1>
    <div class="buttons-con">
        <div class="action-link-wrap">
            <a onclick=location.href='http://localhost:8080/backToHome' class="link-button">Go to Home Page</a>
        </div>
    </div>

</div>
<div>
    <!--用于展示表格的盒子div，可以扩展-->
    <div id="NodeMessageChart1" style="width: 500px;height:400px;float:left;position: fixed;top: 250px;left:230px;"></div>
    <!--<div id="output"style="width:500px; height:400px;float:right;"></div>-->
    <div><textarea id="output" style=" text-align:center;width:670px; height:350px;position: fixed;top: 250px;left: 840px;"></textarea></div>
</div>

<script type="text/javascript">
    var node_webSocket = null;
    var wsUri = "ws://localhost:8080/nodeWebSocket";
    var tmpMsg = [];
    var voltageResult = [];
    var timeResult = [];

    for(var i=0;i<1;i++){
        voltageResult.push(0);
        timeResult.push(0);
    }
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        node_webSocket = new WebSocket(wsUri);
    }
    else {
        alert('当前浏览器不支持WebSocket');
    }
    //连接发生错误的回调方法
    node_webSocket.onerror = function () {
        console.log("出现错误！");
    };
    //连接成功建立的回调方法
    node_webSocket.onopen = function () {
        // writeToScreen("WebSocket连接成功！");
        // writeToScreen("Connecting to :" + wsUri);
        $("#output").append("WebSocket连接成功！")
        $("#output").append("\n");
        $("#output").append("Connecting to :" + wsUri+"......")
        $("#output").append("\n");
    }
    //接收到消息的回调方法
    node_webSocket.onmessage = function (event) {
        if(event.data=="1"){
            location.reload();
        }else{
            //event.data数据包装的格式在IndustrialMessage的toString方法中修改
            writeToScreen(event.data);
            tmpMsg = event.data.split(" ");//********此处需要修改
            //往表格中电压填充数据
            voltageResult.push(parseFloat(tmpMsg[7].substring(1)));
            //alert(voltageResult);
            //往表格中电流填充数据
            timeResult.push(tmpMsg[5].substring(9,17));
            //给表格赋完值之后展示用，可用于扩展多个表格如：showEcharts1()
            showEcharts();
            //alert(timeResult);
        }
    }
    //连接关闭的回调方法
    node_webSocket.onclose = function () {
        //连接关闭时的处理代码
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭WebSocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }
    //关闭WebSocket连接
    function closeWebSocket() {
        node_webSocket.close();
    }
    //用于显示在界面的函数
    function writeToScreen(message){
        // var pre = document.createElement("p");
        // pre.style.wordWrap = "break_word";
        // pre.innerHTML = message;
        //output.appendChild(pre);
        output.append("\n");
        output.append(message);
    }


    <!--用于画图-->
    function showEcharts(){
        var NodeMessageChart1 = echarts.init(document.getElementById('NodeMessageChart1'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: '网关采集信息展示：',
                textStyle: {
                            fontWeight: 'normal',
                            color:'#ffffff'
                            }
            },
            tooltip: {
                trigger: 'axis'
            },

            itemStyle : {
                normal : {
                    color:'#32CD32', //改变折线点的颜色
                    lineStyle:{
                        color:'#DC143C' //改变折线颜色
                    }
                }
            },


            legend: {
                data:['网关数据信息展示']
            },
            xAxis: {
                type: 'category',
                data:timeResult,
                axisLine:{
                    lineStyle:{
                        color:'#00FF00',
                        width:2,//这里是为了突出显示加上的
                    }
                }
            },
            yAxis: {
                type: 'value',
                axisLine:{
                    lineStyle:{
                        color:'#00FF00',
                        width:2,//这里是为了突出显示加上的
                    }
                }
            },
            series: [{
                data: voltageResult,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart1.setOption(option1);
    }

    //show
    jQuery(document).ready(function ($) {

        $('#checkbox').change(function(){
            setInterval(function () {
                moveRight();
            }, 3000);
        });

        var slideCount = $('#slider ul li').length;
        var slideWidth = $('#slider ul li').width();
        var slideHeight = $('#slider ul li').height();
        var sliderUlWidth = slideCount * slideWidth;

        $('#slider').css({ width: slideWidth, height: slideHeight });

        $('#slider ul').css({ width: sliderUlWidth, marginLeft: - slideWidth });

        $('#slider ul li:last-child').prependTo('#slider ul');

        function moveLeft() {
            $('#slider ul').animate({
                left: + slideWidth
            }, 200, function () {
                $('#slider ul li:last-child').prependTo('#slider ul');
                $('#slider ul').css('left', '');
            });
        };

        function moveRight() {
            $('#slider ul').animate({
                left: - slideWidth
            }, 200, function () {
                $('#slider ul li:first-child').appendTo('#slider ul');
                $('#slider ul').css('left', '');
            });
        };

        $('a.control_prev').click(function () {
            moveLeft();
        });

        $('a.control_next').click(function () {
            moveRight();
        });

    });

</script>
</body>
</html>
