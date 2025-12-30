<%@page import="java.util.List"%>
<%@page import="com.ch.shop.dto.TopCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ch.shop.dto.Member" %>
<%
	List<TopCategory> topList = (List)request.getAttribute("topList");
%>
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="/"><img src="/static/template/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="./index.html">Home</a></li>
                            <%for(TopCategory topCategory  : topList){%>
                            	<li><a href="#"><%=topCategory.getTopname()%></a></li>
                            <%} %>
                            <li><a href="/product/list">Shop</a></li>
                            <li><a href="#">Pages</a>
                                <ul class="dropdown">
                                    <li><a href="./product-details.html">Product Details</a></li>
                                    <li><a href="./shop-cart.html">Shop Cart</a></li>
                                    <li><a href="./checkout.html">Checkout</a></li>
                                    <li><a href="./blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                            <li><a href="./blog.html">Blog</a></li>
                            <li><a href="./contact.html">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <div class="header__right__auth">
                        	<%if(session.getAttribute("member")==null){//로그인 하지 않았다면.. %>
	                            <a href="/member/loginform">Login</a>
	                            <a href="/member/loginform">Register</a>
                            <%}else{ %>
                            	<a href="/member/loginform">MyPage</a>
                            	<%Member member = (Member)session.getAttribute("member"); %>
                            	<a href="#"><%=member.getName() %></a>
                            <%} %>
                        </div>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                            <li><a href="#"><span class="icon_bag_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    