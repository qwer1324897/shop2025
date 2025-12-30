package com.ch.shop.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private List<Color> colorList;   // 색상
	private List<Size> sizeList;   	// 사이즈
	private List<ProductImg> productImgList;
	private String introduce;
	private String detail;
	private SubCategory subCategory;	// 하위 카테고리
	
	// MultipartFile 은 업로드된 이미지 1개에 대한 정보를 가진 객체. 사용 시 반드시 <input type="file" /> 이 일치해야 함에 주의.
	// 즉, 파라미터명과 MultipartFile[]의 변수명이 일치해야 한다.
	private MultipartFile[] photo;
	// 이 멤버변수에 html 컴포넌트와 일치하는 이름으로 객체를 선언하면 자동으로 업로드된 파일과 매핑이 되지만,
	// 아직 하드디스크에 저장된 상태가 아니라 메모리에만 보관된 상태다.
	// 이후 개발자가 transferTo() 메서드를 호출할 때, 임시디렉토리나 메모리에 존재하던 파일이 실제 개발자가 지정한 디렉토리와 파일명으로 존재하게 된다.
	// 이 때 임시디렉토리 안에 있는 파일은 개발자가 제어 안 해도 된다.
}
