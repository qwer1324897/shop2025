<%@page import="com.ch.shop.dto.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

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
            <h1 class="m-0">상품등록</h1>
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
      
        <!-- Small boxes (Stat box) -->
        <div class="row">
			<div class="col-md-12">
			
	            <div class="card card-info">
	              <div class="card-header">
	                <h3 class="card-title">상품 등록</h3>
	              </div>
	              <!-- /.card-header -->
	              <!-- form start -->
	              <form id="product-form">
	                <div class="card-body">
	             
	                	<div class="form-group row">
							<div class="col-md-6">
		                        <select class="form-control" name="topcategory"></select>
	                      	</div>
                      
							<div class="col-md-6">
	                        	<select class="form-control" name="subcategory"></select>
                      		</div>	       
                      	</div>                    	                
	                
	                  <div class="form-group">
	                    <input type="text" class="form-control" name="product_name" placeholder="상품명">
	                  </div>
	                  
	                  <div class="form-group">
	                    <input type="text" class="form-control" name="brand" placeholder="브랜드">
	                  </div>
	                  
	                  <div class="form-group">
	                    <input type="number" class="form-control" name="price" placeholder="가격(숫자로 입력)">
	                  </div>	   
	                  
	                  <div class="form-group">
	                    <input type="number" class="form-control" name="discount" placeholder="할인가(숫자로 입력)">
	                  </div>	            

				      <div class="form-group">
				      
						<div class="form-group row">
							<div class="col-md-6">
		                        <select multiple class="form-control" name="color"></select>
	                      	</div>
	                      	
							<div class="col-md-6">
	                        	<select multiple class="form-control" name="size"></select>
                      		</div>	       
                      	</div>     
                      	
					  </div>                    

	                  <div class="form-group">
	                    <input type="text" class="form-control" name="introduce" placeholder="간단소개">
	                  </div>

	                  <div class="form-group">
	                    <textarea id="summernote" class="form-control" name="detail" placeholder="상품상세"></textarea>
	                  </div>

	                  <div class="form-group">
	                    <label for="exampleInputFile">File input</label>
	                    <div class="input-group">
	                      <div class="custom-file">
	                        <input type="file" class="custom-file-input" id="product-img" multiple>
	                        <label class="custom-file-label" for="exampleInputFile">Choose file</label>
	                      </div>
	                      <div class="input-group-append">
	                        <span class="input-group-text">Upload</span>
	                      </div>
	                    </div>
	                  </div>
	                  
	                  <div class="form-group row">
	          			<div class="col-md-12" id="product-preview"></div>
	                  </div>
	                  
	                </div>
	                <!-- /.card-body -->
	
	                <div class="card-footer">
	                  <button type="button" class="btn btn-info" id="bt_regist">Submit</button>
	                   <button type="button" class="btn btn-info" id="bt_list">상품목록</button>
	                </div>
	              </form>
	            </div>
	            <!-- /.card -->

			</div>
        </div>
        <!-- /.row -->
        
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
		let selectedFile;		// 외부 영역에서 접근하기 위해 전역변수로 선언
		
		// 상위카테고리, 하위카테고리 전부를 처리하는 함수. 따라서 호출 할 때 상위카테고리인지 하위카테고리인지 구분해야하므로 
		// category 파라미터를 사용해 printCategory("topcategory", list) 이런식으로 구분. 
		function printCategory(title, category, list) {
			let tag = "<option value='0'>"+title+"</option>";
			
			for(let i = 0 ; i < list.length ; i++) {
				if(category == "topcategory") {
					tag += "<option value='"+ list[i].topcategory_id +"'>" + list[i].topname + "</option>"; 	// 위에 html 의 subcategory <option></option> 태그에 누적					
				} else if(category == "subcategory") {
					tag += "<option value='"+ list[i].subcategory_id +"'>" + list[i].subname + "</option>"; 	// 위에 html 의 subcategory <option></option> 태그에 누적
				} else if (category == "color"){
					tag += "<option value='"+ list[i].color_id +"'>" + list[i].color_name + "</option>";
				} else if (category == "size"){
					tag += "<option value='"+ list[i].size_id +"'>" + list[i].size_name + "</option>";
				} 
			}
			$("select[name='"+ category +"']").html(tag);
		}
		
		function getTopCategory() {
			$.ajax({	  // Jquery 의 비동기 통신 문법
				url:"/admin/topcategory/list",
				method:"GET",
				success: function(result,status,xhr) {
					// 서버에서 응답한 정보인 result 를 이용하여 select 박스에 출력
					printCategory("상위 카테고리 선택", "topcategory", result);
				},
				error:function(xhr,status,err) {
					
				}
				
			});
		}
		
		function getSubCategory() {
			$.ajax({     // Jquery 의 비동기 통신 문법
				url:"/admin/subcategory/list?topcategory_id=" +$("select[name='topcategory']").val() ,
				method:"GET", 
				
				// 요청 후 서버에서 응답이 도착했을 때 동작할 속성 및 콜백함수 정의
				// result: 서버에 보낸 데이터, status 서버의 상태, xhr XMLHttpRequest 객체
				success:function(result,status,xhr) {	// 서버의 응답이 200번대이면 success 에 명시된 익명함수가 동작하고,
					// 스프링에서 문자열을 전송 시 content-type 을 json 으로 전송했기 때문에, 클라이언트 측인 자바스크립트에서 별도로 JSON.parse() 를 할 필요 없게 되었음(딸깍으로 끝)
					console.log(result[0].subname);
					printCategory("하위 카테고리 선택", "subcategory", result);    // 자바스크립트의 객체로 이루어진 배열 전달
				},
				error:function(xhr,status,err){		// 서버의 응답이 300번대 이상(문제가 있을 경우)이면 이 error에 명시된 익명함수가 동작.
				}
			});
		}
		
		// 자바스크립트의 이벤트 객체 정보 안에 포함된 유사 배열 객체를 이용하여 화면에 유저가 업로드한 미리보기 이미지를 구현.
		function preview(imgList) {
			
			// imgList 는 배열 유사 객체 이므로, 단순한 읽기 전용이다.
			// 따라서 자바스크립트의 문법이 아니므로, 사용자가 이미지를 제거할 경우, 배열 명단에서 바로 제거가 안 된다.
			// 그렇기 때문에 배열 유사객체인 FileList와 동일하게 생긴 순수 자바스크립트 배열(진짜 배열)로 복사를 해서 사용자가 X표시를 눌러서 이미지를 제거할 때 
			// 복사한 자바스크립트 배열의 한 요소를 제거하고 제거가 완료되고 서버로 전송할 때도 FileList 가 아닌 자바스크립트 배열을 이용해서 서버로 전송해야 한다.
			selectedFile = Array.from(imgList); 
			// 이게 바로 FileList 형식의 읽기전용 유사배열을 진짜 자바스크립트 배열로 변환하는 방법. 굉장히 간단한 API 다.
			console.log("읽기 전용이 아닌 진짜 배열 ", selectedFile)
			
			for(let i = 0 ; i<imgList.length ; i++) {
				let reader = new FileReader();		// 순수 자바스크립트가 아닌 브라우저 API 등장. 파일을 읽어주는 API
				
				// 이미지가 아래의 메서드에서 다 읽혀지면 호출되는 콜백함수 처리
				// 또한 읽혀진 파일 정보는 매개변수로 전달되어진다.
				reader.onload=function(e){
					// 우리가 만든 클래스로부터 인스턴스 생성하기
					console.log(e.target);
					let pImg = new PreviewImg(document.getElementById("product-preview"), selectedFile[i], e.target.result, 100, 100);
				}
				reader.readAsDataURL(imgList[i]);	// 지정한 파일 객체를 읽어들이는 메서드
			}
		}
		
		// jQuery가 지원하는 ajax 기술인 jQuery ajax 를 활용하여 비동기로 가져오기 
		function getColorList() {
			$.ajax({
				url:"/admin/color/list",
				method:"GET",
				success: function(result, status, xhr){
					console.log("서버에서 가져온 색상 목록은 ", result);
					printCategory("색상 선택", "color", result);
				},
				error: function(xhr, status, err) {
					
				}
			});
		}
		
		// 사이즈 목록 비동기로 가져오기
		function getSizeList() {
			$.ajax({
				url:"/admin/size/list",
				method:"GET",
				success: function(result, status, xhr){
					console.log("서버에서 가져온 사이즈 목록은 ", result);
					printCategory("사이즈 선택", "size", result);
				},
				error: function(xhr, status, err) {
					
				}
			});
		}
		
		/*
		파일 업로드 작업은 동기 방식이 아닌 비동기방식으로도 가능하다.
		폼 양식에 바이너리가 포함되었을 경우 비동기로 전송하려면 브라우저 자체에서 제공하는 API 인 FormData 를 사용할 수 있다.
		FormData 자체적으로 자동으로 바이너리 파일 전송 시 사용되는 인코딩 방식인 multipart/form-data 가 처리되어 있다. 
		*/
		function regist() {
			// 파라미터를 채울 때는 append() 메서드를 이용.
			// formData.append("product_name", $("input[name='product_name']").val());  << 이렇게 쓸 수도 있지만
			// 개발자가 일일이 파라미터를 지정하면 Form의 파라미터 수가 많을 경우 코드량이 많아지므로
			// FormData에 기존의 Form을 대입하자
			
			let formData = new FormData(document.getElementById("product-form"));	 // $("#product-from") 적용 안 됨. FormData가 애초에 브라우저 API임. 자바스크립트 문법 아님.	
			
			formData.delete("photo"); 	// 비동기방식이니까 등록버튼을 연타할 경우 페이지 새로고침이 안 되니까 formData 에 photo 파라미터가 계속 누적된다.
														// 따라서 등록버튼을 누를 때마다, 기존에 쌓인 이미지 파라미터를 제거.
			
			// 사용자가 선택한 이미지 배열만큼 formData 에 추가하자.
			for(i=0 ; i<selectedFile.length ; i++) { 	
				formData.append("photo", selectedFile[i]);	
			}
			formData.append("subCategory.subcategory_id", $("select[name='subcategory']").val() );
			
			// 비동기 요청이니까 jQuery Ajax 사용
			$.ajax({
				url:"/admin/product/regist",
				method:"post",
				data: formData,
				processData: false,
				// processData 속성을 주는 이유: 
				// jQuery Ajax 는 파라미터 전송 시 원래 JSON 문자열로 전송.
				// {name:"Jin", age:"20"} 을 name=zino&age=20 이렇게 key=value 형식의 JSON으로 변환해주는데, 지금의 경우
				// 전송할 때 이미지(바이너리 파일)가 포함되어 있다. 따라서 바이너리 파일(이미지)을 대상으로 자동 문자열 변환을 허용하면 당연~히 에러가 난다.
				// 따라서 jQuery로 하여금 문자열 자동변환을 하지 말라고 막는 속성이다.
				contentType: false,
				// contentType 속성을 주는 이유: 
				// jQuery Ajax 는 POST 전송을 할 때 자동으로 contentType 을 application/x-www-form-urlencoded; charset=UTF-8 이렇게 변경해주는데
				// 앞서 말했다시피 지금의 경우 이미지같은 바이너리 파일을 전송하고 있으므로, jQuery로 하여금 자동 인코딩 지정을 못하게 하는 속성이다.
				
				// 서버의 응답 코드가 200(성공)일 경우, 아래의 success 속성에 명시된 콜백함수가 동작함.
				success: function(result, status, xhr) {
					// 서버측에서 응답한 데이터 형식이 순수 문자열일 경우, js의 객체 리터럴로 변환해야 . 찍고 쓸 수 있다.
					// 따라서 문자열을 객체로 바꾸기 위해 JSON.parse() 로 처리
					alert(result.message);
				},
				// 서버측에서 응답한 코드가 에러인 경우(300 이상) 아래의 error 속성에 명시된 콜백함수 호출.
				error: function(xhr, status, err) {
					let msg = xhr.responseJSON.message || xhr.responseJSON.messege;
				    alert(msg);
				}
				
			});
			
			
		}
	
		$(()=> {
			$("#summernote").summernote();
			
			// 상위 카테고리의 select 상자의 값을 변경할 때, 비동기방식으로 즉 새로고침 없이 하위 카테고리를 출력해야한다. 
			// 지금까지는 js의 순수 코드를 이용하여 비동기 통신을 수행했지만, 이번에는 Jquery가 지원하는 비동기 통신 방법을 사용.
			getTopCategory();
			
			$("select[name='topcategory']").change(()=>{
				getSubCategory();
			});
			
			// 상품 등록 시 색상 비동기로 가져오기
			getColorList();
			
			// 상품 등록 시 색상 비동기로 가져오기
			getSizeList();
			
			// 유저가 이미지 컴포넌트에서 이미지 변경할 때 처리하는 이벤트 연결
			$("#product-img").change((e)=>{
				// 사용자가 이미지 컴포넌트를 통해 이미지를 선택하면, 선택한 개수에 따라 배열로 모아서 반환해주는데,
				// 이 때 이미지 정보가 담겨있는 FileList 라는 객체는, 순수 자바스크립트 언어에서 지원하는 객체가 아니라
				// 크롬과 같은 브라우저 API 를 제공하는 객체이다. 특히 FileList(readOnly)는 읽기 전용이어서 개발자가 수정, 삭제 불가능.
				console.log(e);
				
				console.log("이미지 바꿈?", e.target.files);
				preview(e.target.files);	// 배열 유사 객체를 가지고 호출
			});
			
			// 등록 버튼 이벤트
		    $("#bt_regist").click(() => {
		        regist();
		    });

		    // 목록 버튼 이벤트 (확실하게 이동)
		    $("#bt_list").click(() => {
		        location.href = "/admin/product/list";
		    });
		});
	</script>
	
</body>
</html>



