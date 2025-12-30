<%@page import="com.ch.shop.dto.ProductImg"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.ch.shop.dto.Product"%>
<%@page import="com.ch.shop.dto.TopCategory"%>
<%@page import="java.util.List"%>

<%
	List<Product> productList = (List)request.getAttribute("productList");
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

	<%@ include file="../inc/head_link.jsp" %>
	
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

  <!-- Preloader -->

	<%@ include file="../inc/preloader.jsp" %>

  <!-- Navbar -->
  
  	<%@ include file="../inc/navbar.jsp" %>
  
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  
  <%@ include file="../inc/sidebar.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">상품 목록</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">상품관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
	
	    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
       <!-- 메인 컨텐츠 시작 -->
        <div class="row">
        	<div class="col-md-12">
				<!-- 상품 목록 시작 -->
			<div class="card">
              <div class="card-header">
                <h3 class="card-title">Responsive Hover Table</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                      <button type="submit" class="btn btn-default">
                        <i class="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>No</th>
                      <th>이미지</th>
                      <th>하위카테고리</th>
                      <th>상품명</th>
                      <th>브랜드</th>
                      <th>가격</th>
                      <th>할인가</th>
                    </tr>
                  </thead>
                  <tbody>
                  
                  	<%for(int i = 0 ; i < productList.size() ; i++) { %>
                  	<%Product product = productList.get(i); %>
                    <tr>
                      <td>1</td>
                      <%
                      ProductImg productImg = product.getProductImgList().get(0);
                      %>
                      <td><%//="c:/shopdata/product/p" + product.getProduct_id()+"/" + productImg.getFilename()%>
                      	<img src="/photo/p<%=product.getProduct_id()%>/<%=productImg.getFilename()%>" width="30px">
                      </td>
                      <td><%=product.getSubCategory().getSubcategory_id() %></td>
                      <td><%=product.getProduct_name() %></td>
                      <td><%=product.getBrand() %></td>
                      <td><%=product.getPrice() %></td>
                      <td><%=product.getDiscount() %></td>
                    </tr>
                    <%} %>
                  </tbody>
                </table>
              </div>
	              <!-- /.card-body -->
	            </div>				
				<!-- 상품 목록 끝 -->
        	</div>
        </div>
        <!-- 메인 컨텐츠 끝 -->
        
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

	<%@ include file="../inc/footer.jsp" %>

  <!-- Control Sidebar -->

	<%@ include file="../inc/control_sidebar.jsp" %>

  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

	<%@ include file="../inc/footer_link.jsp" %>
	<script src="/static/adminlte/custom/js/PreviewImg.js"></script>
	<script>
		function getList() {
			$.ajax({
				url: "/admin/product/async/list",	// web.xml 의 admin 을 먼저 만나야 하므로 admin 을 붙인다.
				method: "GET",
				success: function(result, status, xhr){
					console.log("서버에서 받아온 상품 목록은 " + result);
				}
			});
		}
		
		
	</script>
	
</body>
</html>



