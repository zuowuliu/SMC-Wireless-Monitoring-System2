<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>无线气动监控系统-登录历史信息</title>
    <script type="text/javascript" src="../../static/js2/jquery-2.0.0.min.js"></script>
    <link rel="stylesheet" href="../../static/bootstrap-3.3.7/dist/css/bootstrap.css">
    <script type="text/javascript" src="../../static/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../static/css/loginRecords.css">
</head>
<body>
    <section class="head">
        <a onclick=location.href='http://localhost:8080/backToHome' data-bf="无线气动" data-af="">SMC监控系统</a>
    </section>
    <!--<div class="col-md-12">
        <button class="btn btn-default glyphicon glyphicon-triangle-left" onclick=location.href='http://localhost:8080/backToHome' style="text-align: center">Back</button>
    </div>-->
    <div class="container">
        <!-- 表格 -->
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover" id="loginRecords_table">
                    <thead>
                    <tr>
                        <th>userName</th>
                        <th>loginTime</th>
                    <tr/>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <!-- 分页信息 -->
            <div class="row">
                <!-- 显示分页文字信息 -->
                <div class="col-md-6" id="page_info_area">

                </div>
                <!-- 显示分页条信息 -->
                <div class="col-md-6" id="page_nav_area">

                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        //*************************************
        //显示登录信息
        var totalRecord;
        $(function(){
            //页面一进来就去首页
            toPage(1);
            /**
             * 记录当前的时间
             * */
        });
        function toPage(pn){
            $.ajax({
                url:"http://localhost:8080/loginInfo",
                type:"GET",
                data:"pn="+pn,
                success:function(result){
                    //解析并显示登录信息
                    build_loginRecords_table(result);
                    //解析并显示分页信息
                    build_page_info(result);
                    //解析并显示分页条数据
                    build_page_nav(result);
                }
            });
        }

        //构建员工信息表
        function build_loginRecords_table(result){
            //每次执行以前先清空之前的数据信息
            $("#loginRecords_table tbody").empty();
            $.each(result.extend.pageInfo.list,function(index,item){
                    var userName = $("<td></td>").append(item.userName);
                    var loginTime = $("<td></td>").append(item.loginTime);
                    $("<tr></tr>").append(userName).append(loginTime).appendTo("#loginRecords_table tbody");
                }
            )
        }
        //新建分页记录
        function build_page_info(result){
            //清空分页信息
            $("#page_info_area").empty();
            $("#page_info_area").append("当前"+result.extend.pageInfo.pageNum+"页，总共"+result.extend.pageInfo.pages+"页,总共"+result.extend.pageInfo.total+"条记录");
            totalRecord = result.extend.pageInfo.total;
        }
        //新建分页信息
        function build_page_nav(result){
            $("#page_nav_area").empty();
            var ul = $("<ul></ul>").addClass("pagination");
            var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
            var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
            if(result.extend.pageInfo.hasPreviousPage == false){
                firstPageLi.addClass("disabled");
                prePageLi.addClass("disabled");
            }else{
                prePageLi.click(function(){
                    toPage(result.extend.pageInfo.pageNum-1);
                });
                firstPageLi.click(function(){
                    toPage(1);
                });
            }

            var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));
            var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
            if(result.extend.pageInfo.hasNextPage == false){
                nextPageLi.addClass("disabled");
                lastPageLi.addClass("disabled");
            }else{
                nextPageLi.click(function(){
                    toPage(result.extend.pageInfo.pageNum+1);
                });
                lastPageLi.click(function(){
                    toPage(result.extend.pageInfo.pages);
                });
            }
            ul.append(firstPageLi).append(prePageLi);
            $.each(result.extend.pageInfo.navigatepageNums,function(index,item){
                var numLi =  $("<li></li>").append($("<a></a>").append(item));
                if(result.extend.pageInfo.pageNum == item){
                    numLi.addClass("active");
                }
                numLi.click(function(){
                    toPage(item);
                })
                ul.append(numLi);
            });
            ul.append(nextPageLi).append(lastPageLi);
            var nav = $("<nav></nav>").append(ul);
            nav.appendTo("#page_nav_area");
        }
    </script>
</body>
</html>
