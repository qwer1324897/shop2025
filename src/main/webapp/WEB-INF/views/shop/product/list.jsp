<%@page import="com.ch.shop.util.MoneyConverter"%>
<%@page import="com.ch.shop.dto.ProductImg"%>
<%@page import="com.ch.shop.dto.Product"%>
<%@page import="com.ch.shop.dto.SubCategory"%>
<%@page import="com.ch.shop.dto.TopCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%
	// ProductController 에서 저장해놓은 productList 를 꺼내서 사용.
	List<Product> producList = (List)request.getAttribute("productList");
%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

    <%@ include file="../inc/head_link.jsp" %>
</head>

<body>
    <!-- Page Preloder -->
    <%@ include file="../inc/preloader.jsp" %>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="#"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="./index.html"><img src="/static/template/img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
            <a href="#">Login</a>
            <a href="#">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <%@ include file="../inc/header.jsp" %>
    <!-- Header Section End -->
	
	<!-- 상품 목록 Begin -->
	<section class="shop spad">
		<div class="container">
		            <div class="row">
		                <div class="col-lg-3 col-md-3">
		                    <div class="shop__sidebar">
		                        <div class="sidebar__categories">
		                            <div class="section-title">
		                                <h4>Categories</h4>
		                            </div>
		                            <div class="categories__accordion">
		                                <div class="accordion" id="accordionExample">
		                                    <%for(int i=0;i<topList.size();i++){%>
		                                    <%TopCategory topCategory=topList.get(i); %>
		                                    <div class="card">
		                                        <div class="card-heading">
		                                            <a data-toggle="collapse" data-target="#collapse<%=i%>" class="collapsed" aria-expanded="false"><%=topCategory.getTopname() %></a>
		                                        </div>
		                                        <div id="collapse<%=i%>" class="collapse" data-parent="#accordionExample" style="">
		                                            <div class="card-body">
		                                                <ul>
		                                                    <%for(SubCategory subCategory : topCategory.getSubList()){%>
		                                                    <li><a href="/product/list?subcategory_id=<%=subCategory.getSubcategory_id() %>"><%=subCategory.getSubname() %></a></li>
		                                                    <%} %>
		                                                </ul>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <%} %>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="sidebar__filter">
		                            <div class="section-title">
		                                <h4>Shop by price</h4>
		                            </div>
		                            <div class="filter-range-wrap">
		                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content" data-min="33" data-max="99"><div class="ui-slider-range ui-corner-all ui-widget-header" style="left: 0%; width: 100%;"></div><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="left: 0%;"></span><span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default" style="left: 100%;"></span></div>
		                                <div class="range-slider">
		                                    <div class="price-input">
		                                        <p>Price:</p>
		                                        <input type="text" id="minamount">
		                                        <input type="text" id="maxamount">
		                                    </div>
		                                </div>
		                            </div>
		                            <a href="#">Filter</a>
		                        </div>
		                        <div class="sidebar__sizes">
		                            <div class="section-title">
		                                <h4>Shop by size</h4>
		                            </div>
		                            <div class="size__list">
		                                <label for="xxs">
		                                    xxs
		                                    <input type="checkbox" id="xxs">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="xs">
		                                    xs
		                                    <input type="checkbox" id="xs">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="xss">
		                                    xs-s
		                                    <input type="checkbox" id="xss">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="s">
		                                    s
		                                    <input type="checkbox" id="s">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="m">
		                                    m
		                                    <input type="checkbox" id="m">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="ml">
		                                    m-l
		                                    <input type="checkbox" id="ml">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="l">
		                                    l
		                                    <input type="checkbox" id="l">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="xl">
		                                    xl
		                                    <input type="checkbox" id="xl">
		                                    <span class="checkmark"></span>
		                                </label>
		                            </div>
		                        </div>
		                        <div class="sidebar__color">
		                            <div class="section-title">
		                                <h4>Shop by size</h4>
		                            </div>
		                            <div class="size__list color__list">
		                                <label for="black">
		                                    Blacks
		                                    <input type="checkbox" id="black">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="whites">
		                                    Whites
		                                    <input type="checkbox" id="whites">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="reds">
		                                    Reds
		                                    <input type="checkbox" id="reds">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="greys">
		                                    Greys
		                                    <input type="checkbox" id="greys">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="blues">
		                                    Blues
		                                    <input type="checkbox" id="blues">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="beige">
		                                    Beige Tones
		                                    <input type="checkbox" id="beige">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="greens">
		                                    Greens
		                                    <input type="checkbox" id="greens">
		                                    <span class="checkmark"></span>
		                                </label>
		                                <label for="yellows">
		                                    Yellows
		                                    <input type="checkbox" id="yellows">
		                                    <span class="checkmark"></span>
		                                </label>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		                <div class="col-lg-9 col-md-9">
		                    <div class="row">
		                    	<%for(Product product : producList){%>
		                        <div class="col-lg-4 col-md-6">
		                            <div class="product__item">
		                                <div class="product__item__pic set-bg" data-setbg="/photo/p<%=product.getProduct_id()%>/<%=product.getProductImgList().get(0).getFilename() %>" style="background-image: url(&quot;img/shop/shop-1.jpg&quot;);">
		                                    <div class="label new">New</div>
		                                    <ul class="product__hover">
		                                        <li><a href="/photo/p<%=product.getProduct_id()%>/<%=product.getProductImgList().get(0).getFilename() %>" class="image-popup"><span class="arrow_expand"></span></a></li>
		                                        <li><a href="#"><span class="icon_heart_alt"></span></a></li>
		                                        <li><a href="#"><span class="icon_bag_alt"></span></a></li>
		                                    </ul>
		                                </div>
		                                <div class="product__item__text">
		                                    <h6><a href="/product/detail?product_id=<%=product.getProduct_id()%>"><%=product.getProduct_name() %></a></h6>
		                                    <div class="rating">
		                                        <i class="fa fa-star"></i>
		                                        <i class="fa fa-star"></i>
		                                        <i class="fa fa-star"></i>
		                                        <i class="fa fa-star"></i>
		                                        <i class="fa fa-star"></i>
		                                    </div>
		                                    <div class="product__price"><%=MoneyConverter.format(product.getPrice()) %></div>
		                                </div>
		                            </div>
		                        </div>
		                        <%} %>
		                        <div class="col-lg-12 text-center">
		                            <div class="pagination__option">
		                                <a href="#">1</a>
		                                <a href="#">2</a>
		                                <a href="#">3</a>
		                                <a href="#"><i class="fa fa-angle-right"></i></a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
		        </div>
	</section>
    <!-- 상품 목록 End -->

	<!-- Instagram Begin -->
	<%@ include file="../inc/insta.jsp" %>
	<!-- Instagram End -->
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
</body>

</html>