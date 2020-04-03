<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>无线通信远程监控系统</title>
    <link rel="stylesheet" href="../../static/css/home.css">

</head>
<body>
<h1>SMC Wireless monitoring system</h1>

<div id="menu">
    <div class="buffer"></div>
    <h2>Main Menu</h2>
    <ul>
        <hr/>
        <li onclick=location.href='http://localhost:8080/loginInfoJump'><span class="trim"></span>用户登录信息<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid" width="21" height="36" viewBox="0 0 21 36">
            <path d="M0.004,25.709 C0.004,25.709 0.004,35.987 0.004,35.987 C0.004,35.987 20.986,18.000 20.986,18.000 C20.986,18.000 0.004,0.013 0.004,0.013 C0.004,0.013 0.004,10.291 0.004,10.291 C0.004,10.291 8.873,18.000 8.873,18.000 C8.873,18.000 0.004,25.709 0.004,25.709 Z" id="path-1" class="cls-2" fill-rule="evenodd"/>
        </svg></li>
        <hr />
        <li onclick=location.href='http://localhost:8080/nodeMessage'><span class="trim"></span>数据实时监控<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid" width="21" height="36" viewBox="0 0 21 36">
            <path d="M0.004,25.709 C0.004,25.709 0.004,35.987 0.004,35.987 C0.004,35.987 20.986,18.000 20.986,18.000 C20.986,18.000 0.004,0.013 0.004,0.013 C0.004,0.013 0.004,10.291 0.004,10.291 C0.004,10.291 8.873,18.000 8.873,18.000 C8.873,18.000 0.004,25.709 0.004,25.709 Z" id="path-1" class="cls-2" fill-rule="evenodd"/>
        </svg></li><br>
        <hr />
        <!--
        <li onclick=location.href='http://localhost:8080/tcpGateWay1ServerOpen'><span class="trim"></span>网关服务端开关<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid" width="21" height="36" viewBox="0 0 21 36">
            <path d="M0.004,25.709 C0.004,25.709 0.004,35.987 0.004,35.987 C0.004,35.987 20.986,18.000 20.986,18.000 C20.986,18.000 0.004,0.013 0.004,0.013 C0.004,0.013 0.004,10.291 0.004,10.291 C0.004,10.291 8.873,18.000 8.873,18.000 C8.873,18.000 0.004,25.709 0.004,25.709 Z" id="path-1" class="cls-2" fill-rule="evenodd"/>
        </svg></li>
        <hr />-->
        <li onclick=location.href='http://localhost:8080/downloadFile'><span class="trim"></span>下载数据信息<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid" width="21" height="36" viewBox="0 0 21 36">
            <path d="M0.004,25.709 C0.004,25.709 0.004,35.987 0.004,35.987 C0.004,35.987 20.986,18.000 20.986,18.000 C20.986,18.000 0.004,0.013 0.004,0.013 C0.004,0.013 0.004,10.291 0.004,10.291 C0.004,10.291 8.873,18.000 8.873,18.000 C8.873,18.000 0.004,25.709 0.004,25.709 Z" id="path-1" class="cls-2" fill-rule="evenodd"/>
        </svg></li><br>
        <hr />
        <li onclick=location.href='http://localhost:8080'><span class="trim"></span>退出监控系统<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" preserveAspectRatio="xMidYMid" width="21" height="36" viewBox="0 0 21 36">
            <path d="M0.004,25.709 C0.004,25.709 0.004,35.987 0.004,35.987 C0.004,35.987 20.986,18.000 20.986,18.000 C20.986,18.000 0.004,0.013 0.004,0.013 C0.004,0.013 0.004,10.291 0.004,10.291 C0.004,10.291 8.873,18.000 8.873,18.000 C8.873,18.000 0.004,25.709 0.004,25.709 Z" id="path-1" class="cls-2" fill-rule="evenodd"/>
        </svg></li>
        <hr/>
    </ul>

</div>

</body>
</html>