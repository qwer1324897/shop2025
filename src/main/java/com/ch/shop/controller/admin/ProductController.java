package com.ch.shop.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ch.shop.dto.Color;
import com.ch.shop.dto.Product;
import com.ch.shop.dto.Size;
import com.ch.shop.exception.DirectoryException;
import com.ch.shop.exception.ProductColorException;
import com.ch.shop.exception.ProductException;
import com.ch.shop.exception.ProductImgException;
import com.ch.shop.exception.ProductSizeException;
import com.ch.shop.exception.UploadException;
import com.ch.shop.model.product.ProductService;
import com.ch.shop.model.topcategory.TopCategoryService;

import lombok.extern.slf4j.Slf4j;

// 쇼핑몰의 관리자에서 상품과 관련된 요청을 처리하는 하위 컨트롤러
@Controller
@Slf4j
public class ProductController {
	
	// 서비스 보유(느슨하게 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	
	@Autowired
	private ProductService productService;
	
	// 상품 등록 폼 요청 처리
	@GetMapping("/product/registform")
	public String getRegistForm(Model model) {

		// 3단계: 상품페이지에 출력할 상위카테고리 가져오기
		//List topList = topCategoryService.getList();
		
		// 4단계: 결과 저장	(스프링에서는 Model 객체를 사용하면 간접적으로 Request 저장이 됨)
		// jsp까지 topList 를 살려서 가야하므로, 포워딩 처리해야 한다. 스프링 개발자가 redirect 를 명시하지 않으면 디폴트가 포워딩
		//model.addAttribute("topList", topList);	// << 이게 request.setAttribute("topList", topList); 와 동일.
		
		return "admin/product/regist";
	}
	
	// 상품 등록 요청 처리(상품 자체)
	// 클라이언트가 전송한 데이터의 content-Type 이 mutipart/form-data 즉, 바이너리까지 포함될 경우
	// 기존의 HttpServletRequest 로 바로 받지 못한다. 따라서 개발자가 스트림을 직접 제어하거나 기존에 개발된 파일 업로드 컴포넌트를 이용해야 하는데
	// 자바 분야에서는 Aparch 에서 개발한 common fileupload 라이브러리를 많이 사용한다.
	// 따라서 스프링 프레임웤도 apache commons fileupload 를 내부적으로 사용한다.
	
	@PostMapping("/product/regist")	// admin은 빼고. 서블릿 매핑을 admin~ 으로 했기 때문.
	@ResponseBody 	// 이렇게 해야 JSON으로 변환
	public Map<String, String> regist(Product product, int[] color, int[] size) {
		// 매개변수로 지정된 객체와 html 문서의 폼에 지정된 파라미터명이 일치한다면, 자동 매핑이 이루어짐.
		// 파라미터 중 DTO 와 일치하지 않아 자동 매핑이 되지 않을 경우 개발자가 수동으로 직접 나서서 해결.
		
		log.debug("선택하신 하위 카테고리는 " + product.getSubCategory().getSubcategory_id());
		log.debug("상품명은 " + product.getProduct_name());
		log.debug("브랜드는 " + product.getBrand());
		log.debug("가격은 " + product.getPrice());
		log.debug("할인가는 " + product.getDiscount());
		
		// 색상에 대한 수동 처리
		List colorList = new ArrayList();
		for(int c : color) {
			log.debug("넘겨받은 색상은 " + c);
			Color dto = new Color();
			dto.setColor_id(c);
			colorList.add(dto);
		}
		
		// 사이즈에 대한 수동 처리
		List sizeList = new ArrayList();
		for(int s : size) {
			log.debug("넘겨받은 사이즈는 " + s);
			Size dto = new Size();
			dto.setSize_id(s);
			sizeList.add(dto);
		}
		product.setColorList(colorList);
		product.setSizeList(sizeList);
		
		log.debug("colorList 는" + product.getColorList());
		log.debug("sizeList 는" + product.getSizeList());
		
		log.debug("간단소개: " + product.getIntroduce());
		log.debug("상세설명: " + product.getDetail());
		
		/*
		 넘겨받은 파라미터를 이용하여 상품등록
		 상품등록의 1개의 업무 단위는 product, product_img, product_size, product_color 까지 4개의 업무가 포함된다.
		 하지만 컨트롤러는 이 4개로 이루어져있다는 사실을 몰라야 한다.(서비스가 알아야 한다)
		 */
		
		try {
			productService.regist(product);
		} catch (Exception e) {
			productService.cancelUpload(product);
			e.printStackTrace();
			throw e;
		}
		
//		StringBuffer sb = new StringBuffer();
//		/*
//		 {
//		 	"message" : "상품등록"
//		 } 
//		 */
//		sb.append("{");
//		sb.append("\"message\" : \"상품등록\"");
//		sb.append("}");														>> 이렇게 할 수도 있지만 누가 이렇게 하나. Jackson 쓰자.
		
		// JSON 표기를 자바로 표현하면 결국 Map 이기 때문에 응답 정보를 만들 때 개발자는 일일이 JSON 문자열을 생성할 필요 없다.
		// Jackson 라이브러리를 이용한 자바 객체의 반환으로 효율적인 처리를 하자.)
		
		Map<String, String> body = new HashMap<String, String>();
		body.put("message", "상품 등록 성공");
		
		return body;
		
	}
	
	// 상품 목록 페이지 요청 처리
	@GetMapping("/product/list")		// 얘는 매핑
	public String getListPage(Model model) {
		List productList = productService.getList();	// 3단계 모델에 일 시키기
		model.addAttribute("productList", productList);		// 4단계 저장.
		return "admin/product/list";		// 얘는 jsp
	}
	
	// 비동기 상품 목록 요청 처리
	@GetMapping("/product/async/list")
	@ResponseBody 	// @ResponseBody 를 명시하면 DispatcherServlet 이 응답 정보를 ViewResolver 에게 의뢰하지 않음.
								// 결과를 JSP 로 보여줄 필요 없는 비동기 요청의 경우에 사용한다.
	public List<Product> getList(Model model) {
		
		// 3단계: 서비스에게 일 시키기
		List productList = productService.getList();
		
		// 4단계: 저장하기 > 비동기의 경우 별도의 디자인 페이지에서 결과를 보여주는 방식이 아니니까 데이터를 JSON 문자열로 응답해야 하니, 4단계를 생략해야 한다.
		return productList;
	}

	
	// 스프링에서는 컨트롤러의 요청 처리 메서드들 중, 예외가 발생할 경우 @ExceptionHandler 가 명시된 메서드를 자동으로 호출한다.
	@ExceptionHandler({ProductException.class,UploadException.class,DirectoryException.class,ProductColorException.class,ProductImgException.class,ProductSizeException.class})
	@ResponseBody
	public ResponseEntity<Map<String, String>> handle(Exception e) {
		log.debug("상품 등록 시 예외가 발생하여 handler 메서드가 호출됨");
		// 예외가 발생하면 찌꺼기 파일을 삭제하자
		// productService.cancelUpload();
		Map<String, String> body = new HashMap<String, String>();
		body.put("message", "등록실패");
		
		// 클라이언트에게 응답코드를 보내지 않으면 클라이언트는 성공이라고 생각해서
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

	
}
