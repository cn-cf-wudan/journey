<%--
  Created by IntelliJ IDEA.
  User: wudan-mac
  Date: 16/6/14
  Time: 下午3:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../base/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>明瑞汽配</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/css/easyui.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="${ctx}/css/font-awesome.css" rel="stylesheet" type="text/css" media="all"/>

</head>
<body>
<!-- start header -->
<div class="header_bg">
    <div class="wrap">
        <div class="header">
            <div class="logo">
                <a href="index.html"><img src="${ctx}/images/logo.png" alt=""/> </a>
            </div>
            <div class="h_icon">
                <ul class="icon1 sub-icon1">
                    <li>
                        <span style="color: #999;">登录</span>
                    </li>
                    <li>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
                    </li>
                    <li>
                        <span style="color: #999;">注册</span>
                    </li>
                </ul>
            </div>
            <%--<div class="h_search">

                <form>
                    <input type="text" value="">
                    <input type="submit" value="">
                </form>
            </div>--%>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="header_btm">
    <div class="wrap">
        <div class="header_sub">
            <div class="h_menu">
                <ul>
                    <li class="caption-font"><a href="${ctx}/">首页</a></li>
                    |
                    <li class="active caption-font"><a href="${ctx}/toListCarInfo.do">车型查询</a></li>
                    |
                    <li class="caption-font"><a href="#">广告页</a></li>
                </ul>
            </div>
            <div class="top-nav">
                <nav class="nav">
                    <a href="#" id="w3-menu-trigger"> </a>
                    <ul class="nav-list" style="">
                        <li class="nav-item caption-font"><a href="${ctx}/">首页</a></li>
                        <li class="nav-item caption-font"><a class="active" href="${ctx}/toListCarInfo.do">车型查询</a></li>
                        <li class="nav-item caption-font"><a href="#">广告页</a></li>
                    </ul>
                </nav>
                <%--<div class="search_box">
                    <form>
                        <input type="text" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
                    </form>
                </div>--%>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!-- start main -->
<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <!-- start service -->
            <div class="service">

                <div class="left_sidebar">
                    <div class="sellers">
                        <h4>查询条件</h4>
                        <div class="single-nav">
                            <div class="form-group">
                                <label>车型代码</label>
                                <input type="text" id="model" name="model" placeholder="请输入车型代码" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>发动机型号</label>
                                <input type="text" id="engine" name="engine" placeholder="请输入发动机型号"
                                       class="form-control">
                            </div>
                            <div>
                                <button onclick="searchCarInfo();" class="btn btn-sm btn-primary pull-right m-t-n-xs"
                                        type="button"><strong>搜索</strong>
                                </button>
                                <label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ser-main">
                    <!-- Easyui列表 -->
                    <table class="easyui-datagrid" id="tablelist"></table>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<!-- start footer -->
<!-- start footer -->
<%@ include file="../base/footbg.jsp" %>
<!-- start footer -->
<%@ include file="../base/foot.jsp" %>

</body>
<script src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/move-top.js"></script>
<script type="text/javascript" src="${ctx}/js/easing.js"></script>
<script src="${ctx}/js/responsive.menu.js"></script>
<script src="${ctx}/js/jquery.easyui.min.js"></script>
<script src="${ctx}/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

    $(".single-nav input").bind("keyup", function (e) {
        if (e.keyCode == 13) {
            searchCarInfo();
        }
        if (this.value == "") {
            searchCarInfo();
        }
        return;
    });

    function searchCarInfo() {
        $('#tablelist').datagrid('load', {
            engine: $('#engine').val(),
            model: $('#model').val()
        });
    }

    $(document).ready(function () {
        $(".scroll").click(function (event) {
            event.preventDefault();
            $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1200);
        });
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        $().UItoTop({easingType: 'easeOutQuart'});
    });

    /**
     * 1.数据表格加载数据
     */
    $(function () {
        $(".easyui-datagrid").datagrid({
            striped: true,
            rownumbers: true,
            url: '${ctx}/listCarInfo.do',
            fitColumns: true,
            checkOnSelect: false,
            pageNumber: 1,
            pagination: true,
            pageSize: 50,
            height: $(window).height() - 140,

            columns: [[
                /*{field: 'id', checkbox: true},*/
                {field: 'model', title: '车型代码', width: 100, align: 'center'},
                {field: 'modelShort', title: '简称', width: 100, align: 'center'},
                {field: 'modelName', title: '车型名称', width: 100, align: 'center'},
                {field: 'engine', title: '发动机型号', width: 100, align: 'center'},
                {field: 'displacement', title: '排量', width: 100, align: 'center'},
                {field: 'power', title: '功率', width: 100, align: 'center'},
                {field: 'engineFactory', title: '发动机厂商', width: 100, align: 'center'},
                {field: 'punchMachine', title: '对应凸机', width: 100, align: 'center'}
            ]]
        });
    });

</script>
</html>
