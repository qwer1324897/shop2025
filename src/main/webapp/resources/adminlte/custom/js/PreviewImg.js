

// 상품 이미지를 클릭하면 해당 상품 이미지를 화면에 미리보기로 띄워주는 기능 구현을 위한 클래스 성능
class PreviewImg {
	constructor(container, file, src, width, height) {
		this.container = container;
		this.file = file;	// 자바 스크립트 배열 내에서 이 파일이 몇 번째에 들어있는 지 조사하기 위한 용도로 File을 넘겨받아 보관.
		this.src = src;
		this.width = width;
		this.height = height;
		
		this.wrapper = document.createElement("div");
		this.header = document.createElement("div");
		this.img = document.createElement("img");
		this.img.src=this.src;
		
		// style
		this.img.style.width = this.width + "px";
		this.img.style.height = this.height + "px";
		
		this.wrapper.style.width = (this.width+10) + "px";
		this.wrapper.style.height = (this.height+30) + "px";
		this.wrapper.style.display = "inline-block";	// 너비, 높이 등의 block 요소의 특징을 유지하면서 다른 요소와 수평으로 공존하게 하기 위함.
		this.wrapper.style.margin = 5+"px";
		this.wrapper.style.border = "1px solid red";
		this.wrapper.style.borderRadius = "5px";
		this.wrapper.style.textAlign = "center";
		
		this.header.innerHTML = "<a href='#'>X</a>";
		this.header.style.textAlign="right";
		
		// 조립
		this.wrapper.appendChild(this.header);
		this.wrapper.appendChild(this.img);
		this.container.appendChild(this.wrapper);
		
		// X자 이벤트 연결(미리보기 사진 삭제)
		this.header.addEventListener("click", (e)=>{
			
			// 링크(a href 태그)를 사용자가 클릭하면, 기본적으로(default) y축을 0으로 위치시켜 스크롤이 맨 위로 올라간다.
			// 따라서 기본(default) 특징을 제거해야 한다.
			e.preventDefault();	// a 태그에 의해 스크롤이 위로 이동하는 디폴트 현상 방지
			this.remove();
		});
	}
	
	// 삭제 메서드
	remove() {
		// product-preview 컨테이너 안에 있는 나의 wrapper(this.wrapper)를 지운다
		this.container.removeChild(this.wrapper);	// 단순 화면에서 제거.
		
		// 원본 배열도 함께 제거
		selectedFile.splice(1, selectedFile.indexOf(this.file));	// splice(지울개수,몇번째부터) 문법. 배열을 지정한 번째부터 삭제한다.
	}
	
	
}

/**
 * 
 */