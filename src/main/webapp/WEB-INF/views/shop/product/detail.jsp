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
	
	<!-- BreadCrumb Begin -->
	<div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>상품 상세보기</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- BreadCrumb End -->
	
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
	                
	                    
		             <form id="detail-form">   
		             	<!-- 
		             		장바구니에 담을 상품명과 가격은 현재 텍스트에 불과하기 때문에 전송 시 파라미터를 처리할 때
		             		접근용으로 사용될 히든 컴포넌트를 정의한다. 특히, Jquery AJax를 사용 시 파라미터화를 자동으로 처리할 수 있다.(serialize() 사용가능)
		             	 -->
		                <input type = "hidden" name="product_id" value = "<%=product.getProduct_id() %>">
		             	<input type = "hidden" name="product_name" value = "<%=product.getProduct_name() %>">
		             	<input type = "hidden" name="price" value = "<%=product.getPrice() %>">
		                 	
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
		                                <div class="pro-qty">
		                                    <input type="text" name="ea" value="1">
		                                </div>
		                            </div>
		                            <a href="javascript:addCart(<%=product.getProduct_id() %>)" class="cart-btn"><span class="icon_bag_alt"></span>Add to cart</a>
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
		              
	                </form>
	                
	                
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
	
	<script>
	
		function addCart(product_id) {
			// 장바구니 담기 요청을 비동기 방식으로 진행
			let p = new Promise(function(resolve, reject) {
				$.ajax({
					// url:"/cart/add", 세션 기반 컨트롤러에 요청
					url:"/cart/regist",
					method:"POST",
					// data:"product_id="+product_id+"&product_name="+$("input[name='product_name']").val()+"&price="+$("input[name='price']").val()+"&ea="+$("input[name='ea']").val() ,
					// post 방식으로 전송 할 때 파라미터 문자열을 여기에 대입.
					
					// 근데 이렇게 파라미터를 개발자가 일일이 명시하면 너무 힘들기 때문에, form 에 속한 컴포넌트들을 대상으로 파라미터 문자열로 대신 전송할 수 있는 Jquery Ajax 기술을 활용.
					data: $("#detail-form").serialize(),
					success:function(result, status, xhr) {
						// 비동기 요청이 성공했음을 Promise 에게 알려줘야 하므로, resolve() 호출
						resolve(result.msg);
					},
					error:function(xhr, status, err) {
						// 비동기 요청이 실패했음을 Promise 에게 알려줘야 하므로, reject() 호출
						let obj = JSON.parse(xhr.responseText);
						reject(obj.msg);
					}
				});
			});
			p.then(function(msg) {
				if(confirm(msg + "\n장바구니로 이동하시겠어요?")) {
					location.href="/cart/main";
				}
			})
			p.catch(function(msg) {
				alert(msg);
			})
		}
	</script>
	
	
</body>

</html>