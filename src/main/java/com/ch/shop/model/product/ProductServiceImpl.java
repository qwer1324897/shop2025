package com.ch.shop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ch.shop.dto.Color;
import com.ch.shop.dto.Product;
import com.ch.shop.dto.ProductColor;
import com.ch.shop.dto.ProductImg;
import com.ch.shop.dto.ProductSize;
import com.ch.shop.dto.Size;
import com.ch.shop.exception.ProductException;
import com.ch.shop.util.FileManager;

import lombok.extern.slf4j.Slf4j;

// 서비스의 존재 이유.

// 1. 컨트롤러가 모델 영역의 디테일한 업무를 하지 못하게 방지.
//		만일 컨트롤러가 디테일한 업무를 하게 되면 모델 영역의 업무를 부담하게 되므로, MVC 경계가 무너져 버린다.
//		또한 모델 영역을 분리시킬 수 없으므로, 재사용성이 떨어지게 된다.

// 2. 트랜잭션 처리 시 핵심 역할
//		서비스는 직접 일하지는 않지만 모델 영역의 DAO 들에게 업무를 분담하는 능력을 가진다.
//		특히 DB와 관련되어서는 각 DAO 들의 업무 수행결과에 따라 트랜잭션을 commit 과 rollback 을 결정짓는 주체이다.

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductColorDAO productColorDAO;
	
	@Autowired
	private ProductSizeDAO productSizeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ProductImgDAO productImgDAO;
	
	ProductColor productColor = new ProductColor();
	ProductSize productSize = new ProductSize();
	
	// 쇼핑몰의 상품이 등록될 외부 저장소의 루트 경로. 앞으로 상품이 등록되면 상품의 pk 값을 따와서 pk 명으로 디렉토리를 생성하고, 그 안에 파일들을 배치할 예정.
	private String rootdirectory = "C:/shopdata/product/";
	
	@Override
	@Transactional   // 트랜잭션을 걸었기 때문에 하단의 4가지 업무 중 하나라도 실패하면 전체 실패.
	public void regist(Product product) throws ProductException{ // 여기서 예외를 잡아버리면 서비스 영역에서 에러 원인이 소멸된다.
		                                                                             // 지금의 목적은 개발자가 아닌 일반 사용자들에게까지 예외 원인을 전달하는 게 목적이므로, 컨트롤러에게까지 예외를 전달해야 한다.
		/* 상품 등록은 뜯어보면 총 5가지의 구성 업무가 있다.  product, product_color, product_size, product_img product_img 파일처리 5개의 업무.
		 ----------------------------------------------------
		 세부업무 1) Product 테이블에 insert하기
		 ---------------------------------------------------*/
		log.debug("insert 하기 직전의 product 의 product_id 값은 " + product.getProduct_id() );
		productDAO.insert(product);		
		log.debug("insert 직후 mybatis selectKey 동작 후 product 의 product_id 값은 " + product.getProduct_id() );
		
		/*----------------------------------------------------
		 세부업무 2) DB 가 아니라 파일 저장(원래는 DB가 아니기 때문에 트랜잭션의 대상이 아니지만, 등록업무의 일부이므로 트랜잭션에 포함시켜버리자)
		 ----------------------------------------------------*/		
		// 파일의 수가 여러개면, 파일업로드 과정 중 에러가 발생했을 때 상품 등록 자체는 DB에서 Service에 의해 rollback 처리가 되지만, 파일 자체는 이미 디렉토리에 저장되어 버린다.
		// 즉 파일의 찌꺼기가 남게되는데, 이 때 개발자가 트랜잭션이 실패할 경우 비단 DB 뿐만 아니라 이 업로드되어버린 파일도 직접 제거해야 한다.
		// 따라서 애초에 파일을 저장할 때 디렉토리를 하나 만들어서 그 안에 저장하게 만들고, 트랜잭션 발생 시 방금 만들어진 디렉토리 하나만 날려버리면 파일들이 한 번에 제거된다.
		
		String directoryName = rootdirectory + "/p" + product.getProduct_id();
		fileManager.makeDirectory(directoryName);
		
		// 사용자가 업로드 한 파일의 수만큼 반복하면서, FileManager의 save()를 호출하자.
		
		for (MultipartFile multipartFile : product.getPhoto()) {
			
			// 유저가 업로드한 파일명은 무시하고, 개발자가 만든 규칙에 의해 파일명 만들기
			long time = System.currentTimeMillis();	// 연월일시분초 표시 메서드.
			String fileName = time + "." +fileManager.getExtend(multipartFile.getOriginalFilename());
			log.debug(fileName);
			
			fileManager.save(multipartFile, directoryName, fileName);
			
			/*----------------------------------------------------
			 세부업무 3) 파일명 insert
			 ----------------------------------------------------*/				
			
			// 위에서 파일명이 생성되었으니까 이제 생성된 파일명을 DB에 insert.
			ProductImg productImg = new ProductImg();
			productImg.setFilename(fileName);
			productImg.setProduct(product);
			
			productImgDAO.insert(productImg);	
		}
													   
		/*----------------------------------------------------
		 세부업무 4) ProductColor 테이블에 insert 하기
		 ----------------------------------------------------*/
		for(Color color : product.getColorList()) {
			productColor.setProduct(product);
			productColor.setColor(color);
			productColorDAO.insert(productColor);
		}
		
		/*----------------------------------------------------
		 세부업무 5) ProductSize 테이블에 insert 하기
		 ----------------------------------------------------*/		
		for(Size Size : product.getSizeList()) {
			productSize.setProduct(product);		// 어떤 상품에 대해
			productSize.setSize(Size);		// 어떤 사이즈를
			productSizeDAO.insert(productSize);
		}
	}

	@Override
	public void cancelUpload(Product product) {
		// 모든 OS 는 디렉토리 안에 파일이 존재할 경우 바로 디렉토리 삭제를 금지하고 있다.
		// 따라서 삭제 대상이 되는 디렉토리 안에 파일이 있다면, 그 파일들을 먼저 제거하고 나서 디렉토리 삭제 업무를 진행해야 한다.
		String directoryName = rootdirectory + "/p" + product.getProduct_id();
		fileManager.remove(directoryName);
	}

	@Override
	public List getList() {
		return productDAO.selectAll();
	}

	@Override
	public List selectBySubCategoryId(int subcategory_id) {
		return productDAO.selectBySubCategoryId(subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		return productDAO.select(product_id);
	}
}