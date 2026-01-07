<%@ page contentType="text/html; charset=UTF-8"%>
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
                        <span>Shopping cart</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<!-- BreadCrumb End -->
	
	<!-- 장바구니 시작 -->
	<section class="shop-cart spad" id="app">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            
                            	<!-- 아래의 div 안에 있는 id "app" 영역을 Vue 의 영향 아래 둔다. -->
                            	
                                <tr v-for="변수암거나 in cartList">
                                    <td class="cart__product__item">
                                        <img src="img/shop-cart/cp-1.jpg" alt="">
                                        <div class="cart__product__item__title">
                                            <h6>가디건</h6>
                                        </div>
                                    </td>
                                    <td class="cart__price">가격이 들어갈 자리</td>
                                    <td class="cart__quantity">
                                        <div class="pro-qty"><span class="dec qtybtn">-</span>
                                            <input type="text" value="0">
                                        <span class="inc qtybtn">+</span></div>
                                    </td>
                                    <td class="cart__total">서브토탈</td>
                                    <td class="cart__close"><span class="icon_close"></span></td>
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="#">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="#"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                            <li>Subtotal <span>$ 750.0</span></li>
                            <li>Total <span>$ 750.0</span></li>
                        </ul>
                        <a href="#" class="primary-btn">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<!-- 장바구니 끝 -->

	<!-- Instagram Begin -->
	<%@ include file="../inc/insta.jsp" %>
	<!-- Instagram End -->
	
	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->
	
	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
	
	<!-- Vue 를 이용하면 개발자가 DOM 렌더링 시 전통적인 DOM 제어보다 훨씬 효율적으로 처리가 가능하다. -->
	<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
	
	<script>
		// Vue 애플리케이션 객체를 생성하고, 원하는 렌더링 영역인 div="app" 과 연결하자
		
		const app = Vue.createApp({
			// 아래의 data() 메서드는, Vue 영역에서 사용할 데이터를 반환하는 역할. >> 필수
			data() {
				return {	// Vue 렌더링 영역에서 사용될 데이터를 반환.
					cartList:5
				}
			}
		});
		let vm = app.mount('#app');
		
		// 비동기 방식으로 장바구니 목록을 가져오기
		
		// 	function renderList() {
		// 	let tag = "";
			
		// 	for() {
		// 		tag += "<tr>"
		// 		tag += "<td class=\"cart__product__item\">";
		// 	}
			
		// 	$("#app").html(tag);
		// }
		
	</script>
	
</body>

</html>




