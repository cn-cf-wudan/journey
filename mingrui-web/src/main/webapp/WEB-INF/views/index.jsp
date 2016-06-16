<%--
  Created by IntelliJ IDEA.
  User: wudan-mac
  Date: 16/6/14
  Time: 下午1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="base/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>明瑞汽配</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${ctx}/css/slider.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${ctx}/css/owl.carousel.css" rel="stylesheet" type="text/css" media="all">
</head>
<body>
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
                    <li class="active caption-font"><a href="${ctx}/">首页</a></li> |
                    <li class="caption-font"><a href="${ctx}/toListCarInfo.do">车型查询</a></li> |
                    <li class="caption-font"><a href="#">广告页</a></li>
                </ul>
            </div>
            <div class="top-nav">
                <nav class="nav">
                    <a href="#" id="w3-menu-trigger"> </a>
                    <ul class="nav-list" style="">
                        <li class="nav-item caption-font"><a class="active" href="${ctx}/">首页</a></li>
                        <li class="nav-item caption-font"><a href="${ctx}/toListCarInfo.do">车型查询</a></li>
                        <li class="nav-item caption-font"><a href="#">广告页</a></li>
                    </ul>
                </nav>
                <%--<div class="search_box">
                    <form>
                        <input type="text" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}"><input type="submit" value="">
                    </form>
                </div>--%>
                <div class="clear"> </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!-- start slider -->
<div id="da-slider" class="da-slider">
    <div class="da-slide">
        <h2>明瑞汽配</h2>
        <p>
            &nbsp;&nbsp;&nbsp;&nbsp;公司主要经营微型车全系，另有哈飞，赛马，路宝全车配件。另外还代理国内多家知名配件生产厂家的产品，如川南减震器，耀勇汽缸盖，贵阳汽缸套，内江名正曲轴，内江连发摇臂，三环进排气门，马勒活塞，江峰水泵机油泵，成都正恒汽缸体等。
        </p>
        <%--<a href="details.html" class="da-link">shop now</a>--%>
        <div class="da-img"><img src="${ctx}/images/slider1.png" alt="image01" /></div>
    </div>
    <div class="da-slide">
        <h2>Easy management</h2>
        <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
        <a href="details.html" class="da-link">shop now</a>
        <div class="da-img"><img src="${ctx}/images/slider2.png" alt="image01" /></div>
    </div>
    <div class="da-slide">
        <h2>Revolution</h2>
        <p>A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
        <a href="details.html" class="da-link">shop now</a>
        <div class="da-img"><img src="${ctx}/images/slider3.png" alt="image01" /></div>
    </div>
    <div class="da-slide">
        <h2>Quality Control</h2>
        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.</p>
        <a href="details.html" class="da-link">shop now</a>
        <div class="da-img"><img src="${ctx}/images/slider4.png" alt="image01" /></div>
    </div>
    <nav class="da-arrows">
        <span class="da-arrows-prev"></span>
        <span class="da-arrows-next"></span>
    </nav>
</div>
</div>
<!----start-cursual---->
<div class="wrap">
    <!----start-img-cursual---->
    <div id="owl-demo" class="owl-carousel">
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c1.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>
            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c2.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c3.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c2.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c1.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c2.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
        <div class="item" onClick="location.href='details.html';">
            <div class="cau_left">
                <img class="lazyOwl" data-src="${ctx}/images/c3.jpg" alt="Lazy Owl Image">
            </div>
            <div class="cau_left">
                <h4><a href="details.html">我是个广告</a></h4>

            </div>
        </div>
    </div>
    <!----//End-img-cursual---->
</div>
<!-- start main1 -->
<div class="main_bg1">
    <div class="wrap">
        <div class="main1">
            <h2>featured products</h2>
        </div>
    </div>
</div>
<!-- start main -->
<div class="main_bg">
    <div class="wrap">
        <div class="main">
            <!-- start grids_of_3 -->
            <div class="grids_of_3">
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic1.jpg" alt=""/>
                        <h3>我是个广告</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic2.jpg" alt=""/>
                        <h3>我是个广告</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic3.jpg" alt=""/>
                        <h3>我是个广告</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="clear"></div>
            </div>
            <div class="grids_of_3">
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic4.jpg" alt=""/>
                        <h3>我是个广告</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic5.jpg" alt=""/>
                        <h3>ems women bag</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="grid1_of_3">
                    <a href="details.html">
                        <img src="${ctx}/images/pic6.jpg" alt=""/>
                        <h3>我是个广告</h3>
                        <div class="price">
                            <h4>$300</h4>
                        </div>
                        <span class="b_btm"></span>
                    </a>
                </div>
                <div class="clear"></div>
            </div>
            <!-- end grids_of_3 -->
        </div>
    </div>
</div>
<!-- start footer -->
<%@ include file="base/footbg.jsp"%>
<!-- start footer -->
<%@ include file="base/foot.jsp"%>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/modernizr.custom.28468.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cslider.js"></script>
<script src="${ctx}/js/owl.carousel.js"></script>
<script type="text/javascript" src="${ctx}/js/move-top.js"></script>
<script type="text/javascript" src="${ctx}/js/easing.js"></script>
<script src="${ctx}/js/responsive.menu.js"></script>
<script type="text/javascript">
    $(function() {
        $('#da-slider').cslider();
    });
    $(document).ready(function() {

        $("#owl-demo").owlCarousel({
            items : 4,
            lazyLoad : true,
            autoPlay : true,
            navigation : true,
            navigationText : ["",""],
            rewindNav : false,
            scrollPerPage : false,
            pagination : false,
            paginationNumbers: false,
        });

    });
    jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
            event.preventDefault();
            $('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
        });
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        $().UItoTop({ easingType: 'easeOutQuart' });
    });
</script>
</html>

