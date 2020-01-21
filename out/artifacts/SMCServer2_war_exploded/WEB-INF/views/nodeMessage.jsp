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
    <h1>网关节点数据</h1>
    <div class="buttons-con">
        <div class="action-link-wrap">
            <a onclick=location.href='http://localhost:8080/backToHome' class="link-button">Go to Home Page</a>
        </div>
    </div>

</div>
<div style="float:left;">
    <!--用于展示表格的盒子div，可以扩展-->
    <div id="NodeMessageChart1" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <%--<div id="NodeMessageChart2" style="width: 500px;height:400px;float:left;position: fixed;top: 250px;left:230px;"></div>--%>
    <div id="NodeMessageChart2" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <!--<div id="output"style="width:500px; height:400px;float:right;"></div>-->
    <div id="NodeMessageChart3" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <div id="NodeMessageChart4" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>


</div>

<div style="float:left;">
    <div id="NodeMessageChart5" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <div id="NodeMessageChart6" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <div id="NodeMessageChart7" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>
    <div id="NodeMessageChart8" style="width: 300px;height:300px;float:left;position: fixed;top: 200px;left:230px;"></div>

</div>
<div >
    <div><textarea id="output" style=" text-align:center;width:670px; height:350px;float:left;position: fixed;top: 850px;left: 500px;"></textarea></div>
</div>
<script type="text/javascript">
    var node_webSocket = null;
    var wsUri = "ws://localhost:8080/nodeWebSocket";
    var tmpMsg = [];
    var voltageResult1 = [];
    var timeResult1 = [];
    var voltageResult2= [];
    var timeResult2= [];
    var voltageResult3= [];
    var timeResult3= [];
    var voltageResult4= [];
    var timeResult4= [];
    var voltageResult5= [];
    var timeResult5= [];
    var voltageResult6= [];
    var timeResult6= [];
    var voltageResult7= [];
    var timeResult7= [];
    var voltageResult8= [];
    var timeResult8= [];

    for(var i=0;i<1;i++){
        voltageResult1.push(0);
        timeResult1.push(0);
        voltageResult2.push(0);
        timeResult2.push(0);
        voltageResult3.push(0);
        timeResult3.push(0);
        voltageResult4.push(0);
        timeResult4.push(0);
        voltageResult5.push(0);
        timeResult5.push(0);
        voltageResult6.push(0);
        timeResult6.push(0);
        voltageResult7.push(0);
        timeResult7.push(0);
        voltageResult8.push(0);
        timeResult8.push(0);
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
        writeToScreen("WebSocket连接成功！");
        writeToScreen("Connecting to :" + wsUri);
        $("#output").append("WebSocket连接成功！")
        $("#output").append("\n");
        $("#output").append("Connecting to :" + wsUri+"......")
        $("#output").append("\n");
        console.log("WebSocket连接成功！");
    }
    //接收到消息的回调方法
    node_webSocket.onmessage = function (event) {
        if(event.data=="1"){
            location.reload();
        }else{
            event.data数据包装的格式在IndustrialMessage的toString方法中修改
            writeToScreen(event.data);
            tmpMsg = event.data.split(" ");//********此处需要修改
            //往表格中电压填充数据
            voltageResult1.push(parseFloat(tmpMsg[7].substring(1)));
            voltageResult2.push(parseFloat(tmpMsg[9].substring(1)));
            voltageResult3.push(parseFloat(tmpMsg[11].substring(1)));
            voltageResult4.push(parseFloat(tmpMsg[13].substring(1)));
            voltageResult5.push(parseFloat(tmpMsg[15].substring(1)));
            voltageResult6.push(parseFloat(tmpMsg[17].substring(1)));
            voltageResult7.push(parseFloat(tmpMsg[19].substring(1)));
            voltageResult8.push(parseFloat(tmpMsg[21].substring(1)));


            //alert(voltageResult);
            //往表格中电流填充数据
            timeResult1.push(tmpMsg[5].substring(9,17));
            timeResult2.push(tmpMsg[5].substring(9,17));
            timeResult3.push(tmpMsg[5].substring(9, 17));
            timeResult4.push(tmpMsg[5].substring(9,17));
            timeResult5.push(tmpMsg[5].substring(9,17));
            timeResult6.push(tmpMsg[5].substring(9,17));
            timeResult7.push(tmpMsg[5].substring(9, 17));
            timeResult8.push(tmpMsg[5].substring(9,17));


            //给表格赋完值之后展示用，可用于扩展多个表格如：showEcharts1()
            showEcharts1();
            showEcharts2();
            showEcharts3();
            showEcharts4();
            showEcharts5();
            showEcharts6();
            showEcharts7();
            showEcharts8();

            //alert(timeResult);
        }
    }
    //连接关闭的回调方法
    node_webSocket.onclose = function () {
        //连接关闭时的处理代码
        console.log("WebSocket连接关闭！");
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


    <!--用于画图1-->
    function showEcharts1(){
        var NodeMessageChart1 = echarts.init(document.getElementById('NodeMessageChart1'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'A通道采集信息展示：',
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
                data:timeResult1,
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
                data: voltageResult1,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart1.setOption(option1);
    }

    <!--用于画图2-->
    function showEcharts2(){
        var NodeMessageChart2 = echarts.init(document.getElementById('NodeMessageChart2'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'B通道采集信息展示：',
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
                data:timeResult2,
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
                data: voltageResult2,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart2.setOption(option1);
    }

    <!--用于画图3-->
    function showEcharts3(){
        var NodeMessageChart3 = echarts.init(document.getElementById('NodeMessageChart3'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'C通道采集信息展示：',
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
                data:timeResult3,
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
                data: voltageResult3,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart3.setOption(option1);
    }

    <!--用于画图4-->
    function showEcharts4(){
        var NodeMessageChart4 = echarts.init(document.getElementById('NodeMessageChart4'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'D通道采集信息展示：',
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
                data:timeResult4,
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
                data: voltageResult4,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart4.setOption(option1);
    }

    <!--用于画图5-->
    function showEcharts5(){
        var NodeMessageChart5 = echarts.init(document.getElementById('NodeMessageChart5'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'E通道采集信息展示：',
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
                data:timeResult5,
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
                data: voltageResult5,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart5.setOption(option1);
    }

    <!--用于画图6-->
    function showEcharts6(){
        var NodeMessageChart6 = echarts.init(document.getElementById('NodeMessageChart6'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'F通道采集信息展示：',
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
                data:timeResult6,
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
                data: voltageResult6,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart6.setOption(option1);
    }

    <!--用于画图7-->
    function showEcharts7(){
        var NodeMessageChart7 = echarts.init(document.getElementById('NodeMessageChart7'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'G通道采集信息展示：',
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
                data:timeResult7,
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
                data: voltageResult7,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart7.setOption(option1);
    }

    <!--用于画图8-->
    function showEcharts8(){
        var NodeMessageChart8 = echarts.init(document.getElementById('NodeMessageChart8'));
        var  option1 ={
            //color:['#DC143C','#00FF00'],
            title: {
                text: 'H通道采集信息展示：',
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
                data:timeResult8,
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
                data: voltageResult8,
                type: 'line',
                smooth: true
            }]
        };
        NodeMessageChart8.setOption(option1);
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
