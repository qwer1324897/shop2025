<%@page import="com.ch.shop.dto.TopCategory"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
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
    
    <style>
	.btn-google {
	  background: #DB4437;
	  border: 1px solid #dee2e6;
	  color: #ffffff;
	}
	.btn-google:hover {
	  background: #f8f9fa;
	}
	
	.btn-naver {
	  background: #03C75A;
	  color: #fff;
	  border: none;
	}
	.btn-naver:hover {
	  filter: brightness(0.95);
	  color: #fff;
	}
	
	.btn-kakao {
	  background: #FEE500;
	  color: #191919;
	  border: none;
	}
	.btn-kakao:hover {
	  filter: brightness(0.95);
	}   
 	.google-icon {
  		width: 18px;
  		height: 18px;
	}
    </style>    
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

	<!-- Discount Section Begin -->
	<section class="discount">
	    <div class="container">
	        <div class="row">
	            <div class="col-lg-12 p-10 mt-5 mb-5">
 					<!-- 로그인/회원가입 폼 시작 -->
					<div class="mx-auto" style="max-width:400px;">
			          <!-- 아이디 / 비밀번호 로그인 -->
			          <form>
			            <div class="mb-2">
			              <input type="text" class="form-control" placeholder="아이디" required>
			            </div>
			            <div class="mb-3">
			              <input type="password" class="form-control" placeholder="비밀번호" required>
			            </div>
			
			            <button type="submit" class="btn btn-dark w-100 mb-3">
			              로그인
			            </button>
			          </form>
			
			
			          <!-- SNS 로그인 -->
			          <div class="text-center">
			            <button type="button" class="btn btn-google">Google</button>
			            <button type="button" class="btn btn-naver">Naver</button>
			            <button type="button" class="btn btn-kakao">Kakao</button>
			          </div>
			
			          <!-- 회원가입 -->
			          <div class="text-center mt-3">
			            <a href="/member/join" class="text-decoration-none p-2">홈페이지 회원가입</a> |
			            <a href="/member/join" class="text-decoration-none p-2">아이디 찾기</a> | 
			            <a href="/member/join" class="text-decoration-none p-2">비밀번호 찾기</a>
			          </div>
			
			        </div>	                
	                <!-- 로그인/회원가입 폼 끝 -->  	                
	            </div>
	        </div>
	    </div>
	</section>
	<!-- Discount Section End -->

	<!-- Footer Section Begin -->
	<%@ include file="../inc/footer.jsp" %>
	<!-- Footer Section End -->

	<!-- Js Plugins -->
	<%@ include file="../inc/footer_link.jsp" %>
	
	<script type="text/javascript">
	
		// 모든 프로바이더에 대한 sns 로그인 요청
		function requestProviderUrl(provider) {
			
			$.ajax({
				url:"/oauth2/authorize/" + provider, 		// 서버에 요청해서 주소를 받은 이후, 사용자가 그 주소로 Provider 에 요청
				method:"GET",
				success: function (result, status, xhr) {
					console.log("서버로부터 받은 인증 요청 url 은 ", result);
					alert(result);
					location.href=result;	// << 마지막은 동기 방식
				}
			});
		}
		$(()=> {
			
			$(".btn-google").click(()=> {
				requestProviderUrl("google");
			});
			
			$(".btn-naver").click(()=> {
				requestProviderUrl("naver");
			});
			
			$(".btn-kakao").click(()=> {
				requestProviderUrl("kakao");
			});
			
		});
	</script>
	
</body>

</html>