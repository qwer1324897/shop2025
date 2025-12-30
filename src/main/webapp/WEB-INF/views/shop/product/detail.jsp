<%@page import="com.ch.shop.dto.Size"%>
<%@page import="com.ch.shop.dto.Color"%>
<%@page import="com.ch.shop.dto.ProductImg"%>
<%@page import="com.ch.shop.util.MoneyConverter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.ch.shop.dto.SubCategory" %>
<%@ page import="com.ch.shop.dto.Product" %>
<%
	Product product = (Product)request.getAttribute("product");
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
	
	<!-- 상품 상세 Begin -->
	<section class="product-details spad">
	        <div class="container">
	            <div class="row">
	                <div class="col-lg-6">
	                    <div class="product__details__pic">
	                        <div class="product__details__pic__left product__thumb nice-scroll" tabindex="1" style="overflow-y: hidden; outline: none;">
	                        
	                        	<%for(ProductImg productImg : product.getProductImgList()) {%>
	                            <a class="pt active" href="#product-1">
	                                <img src="/photo/p<%=product.getProduct_id() %>/<%=productImg.getFilename() %>" alt="">
	                            </a>
	                        	<%} %>
	                            
	                        </div>
	                        <div class="product__details__slider__content">
	                            <div class="product__details__pic__slider owl-carousel owl-loaded">
		                            <div class="owl-stage-outer">
		                            	<div class="owl-stage" style="transform: translate3d(0px, 0px, 0px); transition: all; width: 1324px;">
		                            		
		                            		<%for(ProductImg productImg : product.getProductImgList()) { %>
				                            <div class="owl-item active" style="width: 331px;">
				                            	<img data-hash="product-1" class="product__big__img" src="/photo/p<%=product.getProduct_id() %>/<%=productImg.getFilename() %>" alt="">
				                            </div>
											<%} %>
											
			                            </div>
		                            </div>
		                            <div class="owl-nav">
			                            <button type="button" role="presentation" class="owl-prev disabled">
				                            <i class="arrow_carrot-left"></i>
			                            </button>
			                            <button type="button" role="presentation" class="owl-next">
				                            <i class="arrow_carrot-right"></i>
			                            </button>
		                            </div>
		                            <div class="owl-dots disabled"></div>
	                            </div>
	                        </div>
	                        
	                    </div>
	                </div>
	                <div class="col-lg-6">
	                    <div class="product__details__text">
	                        <h3><%=product.getProduct_name() %> <span>Brand: <%=product.getBrand() %></span></h3>
	                        <div class="rating">
	                            <i class="fa fa-star"></i>
	                            <i class="fa fa-star"></i>
	                            <i class="fa fa-star"></i>
	                            <i class="fa fa-star"></i>
	                            <i class="fa fa-star"></i>
	                            <span>( 138 reviews )</span>
	                        </div>
	                        <div class="product__details__price"><%=MoneyConverter.format(product.getPrice()) %><span><%=MoneyConverter.format(product.getDiscount()) %></span></div>
	                        <p>Nemo enim ipsam voluptatem quia aspernatur aut odit aut loret fugit, sed quia consequuntur
	                        magni lores eos qui ratione voluptatem sequi nesciunt.</p>
	                        <div class="product__details__button">
	                            <div class="quantity">
	                                <span>Quantity:</span>
	                                <div class="pro-qty"><span class="dec qtybtn">-</span>
	                                    <input type="text" value="1">
	                                <span class="inc qtybtn">+</span></div>
	                            </div>
	                            <a href="#" class="cart-btn"><span class="icon_bag_alt"></span> Add to cart</a>
	                            <ul>
	                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
	                                <li><a href="#"><span class="icon_adjust-horiz"></span></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__details__widget">
	                            <ul>
	                                <li>
	                                    <span>Availability:</span>
	                                    <div class="stock__checkbox">
	                                        <label for="stockin">
	                                            In Stock
	                                            <input type="checkbox" id="stockin">
	                                            <span class="checkmark"></span>
	                                        </label>
	                                    </div>
	                                </li>
	                                <li>
	                                    <span>Available color:</span>
	                                    <div class="color__checkbox">
	                                    
	                                    	<%for(Color color : product.getColorList()) { %>
	                                        <label for="<%=color.getColor_name() %>">
	                                            <input type="radio" name="color__radio" id="<%=color.getColor_name() %>" checked="">
	                                            <span class="checkmark"></span>
	                                        </label>
	                                        <%} %>
	                                        
	                                    </div>
	                                </li>
	                                <li>
	                                    <span>Available size:</span>
	                                    <div class="size__btn">
	                                    
	                                    	<%for(Size size : product.getSizeList()) {%>
	                                        <label for="<%=size.getSize_name() %>-btn" class="active">
	                                            <input type="radio" id="<%=size.getSize_name() %>">
	                                            <%=size.getSize_name() %>
	                                        </label>
	                       					<%} %>
	                       					
	                                    </div>
	                                </li>
	                                <li>
	                                    <span>Promotions:</span>
	                                    <p>Free shipping</p>
	                                </li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-12">
	                    <div class="product__details__tab">
	                        <ul class="nav nav-tabs" role="tablist">
	                            <li class="nav-item">
	                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab">Description</a>
	                            </li>
	                            <li class="nav-item">
	                                <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab">Specification</a>
	                            </li>
	                            <li class="nav-item">
	                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab">Reviews ( 2 )</a>
	                            </li>
	                        </ul>
	                        <div class="tab-content">
	                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
	                                <h6>Description</h6>
	                                <p><%=product.getDetail() %></p>
	                            </div>
	                            <div class="tab-pane" id="tabs-2" role="tabpanel">
	                                <h6>Specification</h6>
	                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
	                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
	                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
	                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
	                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
	                                consequat massa quis enim.</p>
	                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
	                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
	                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
	                                quis, sem.</p>
	                            </div>
	                            <div class="tab-pane" id="tabs-3" role="tabpanel">
	                                <h6>Reviews ( 2 )</h6>
	                                <p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed
	                                    quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt loret.
	                                    Neque porro lorem quisquam est, qui dolorem ipsum quia dolor si. Nemo enim ipsam
	                                    voluptatem quia voluptas sit aspernatur aut odit aut loret fugit, sed quia ipsu
	                                    consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Nulla
	                                consequat massa quis enim.</p>
	                                <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget
	                                    dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes,
	                                    nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium
	                                quis, sem.</p>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	                <div class="col-lg-12 text-center">
	                    <div class="related__title">
	                        <h5>RELATED PRODUCTS</h5>
	                    </div>
	                </div>
	                <div class="col-lg-3 col-md-4 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-1.jpg" style="background-image: url(&quot;img/product/related/rp-1.jpg&quot;);">
	                            <div class="label new">New</div>
	                            <ul class="product__hover">
	                                <li><a href="img/product/related/rp-1.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
	                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
	                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6><a href="#">Buttons tweed blazer</a></h6>
	                            <div class="rating">
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                            </div>
	                            <div class="product__price">$ 59.0</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-3 col-md-4 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-2.jpg" style="background-image: url(&quot;img/product/related/rp-2.jpg&quot;);">
	                            <ul class="product__hover">
	                                <li><a href="img/product/related/rp-2.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
	                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
	                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6><a href="#">Flowy striped skirt</a></h6>
	                            <div class="rating">
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                            </div>
	                            <div class="product__price">$ 49.0</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-3 col-md-4 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-3.jpg" style="background-image: url(&quot;img/product/related/rp-3.jpg&quot;);">
	                            <div class="label stockout">out of stock</div>
	                            <ul class="product__hover">
	                                <li><a href="img/product/related/rp-3.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
	                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
	                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6><a href="#">Cotton T-Shirt</a></h6>
	                            <div class="rating">
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                            </div>
	                            <div class="product__price">$ 59.0</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-lg-3 col-md-4 col-sm-6">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" data-setbg="img/product/related/rp-4.jpg" style="background-image: url(&quot;img/product/related/rp-4.jpg&quot;);">
	                            <ul class="product__hover">
	                                <li><a href="img/product/related/rp-4.jpg" class="image-popup"><span class="arrow_expand"></span></a></li>
	                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
	                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6><a href="#">Slim striped pocket shirt</a></h6>
	                            <div class="rating">
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                                <i class="fa fa-star"></i>
	                            </div>
	                            <div class="product__price">$ 59.0</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
    <!-- 상품 상세 End -->

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