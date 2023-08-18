<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<link href="resources/css/bootstrap-grid.min.css" rel="stylesheet">
<link href="resources/css/bootstrap-grid.rtl.min.css" rel="stylesheet">
<link href="resources/css/sidebars.css" rel="stylesheet">
<script src="resources/js/sidebars.js"></script>

<%@include file="../user/user_top.jsp" %>

<style>
	.vertical-right-line {
		border-right-style: solid;
	 	border-right-width: 2px;
	  	border-right-color: #dee2e6;
	}
	.img-pick-box {
		border: 0 solid black;
		background-color: #ffffff;
        background-color: rgba(255,255,255,0.5);
	}
	
	#attZone{
		width: 660px;
		min-height:150px;
		padding:10px;
		border:1px #000;
	}
	#attZone:empty:before{
		content : attr(data-placeholder);
		color : #999;
		font-size:.9em;
	}
	
	.imgPreviewCrop{
		position: absolute;
    	top: 0;
    	left: 0;
    	transform: translate(50, 50);
    	width: 100%;
    	height: 100%;
    	object-fit: cover;
    	margin: auto;
	}
	
</style>

<script type="text/javascript">
	let sorted_files = []; //이미지 배열
	let isOpen = '${profile.prof_open}';
	
	window.addEventListener('DOMContentLoaded', function(){
		lock_btn_set(isOpen);
	});
	
	//잠금버튼 설정
	function lock_btn_set(open){
		let lock_svg = document.getElementById('lock_svg');
		let lock_span = document.getElementById('lock_span');
		
		if(open === 'open'){
			lock_svg.innerHTML='<use xlink:href="#unlock"></use>';
			lock_span.innerHTML='전체공개';
		}else{
			lock_svg.innerHTML='<use xlink:href="#lock"></use>';
			lock_span.innerHTML='친구공개';
		}
	}
	//공개여부 변경
	function lockUnlock(){
		if(isOpen === 'open'){
			isOpen = 'secret';
			lock_btn_set(isOpen);
		}else{
			isOpen = 'open';
			lock_btn_set(isOpen);
		}
	}
	//장소 창 열기
	function openplace(){
		var popupX = (document.body.offsetWidth /2) - (1200 /2);
		var popupY= (window.screen.height /2) - (800 /2);
		window.open('mapSelect.do', '지도', 'status=no, width=1200, height=800, left='+popupX+', top='+popupY);
	}
	//임시목록 창 열기
	function opentemplist(feednum){
		var popupX = (document.body.offsetWidth /2) - (600 /2);
		var popupY= (window.screen.height /2) - (800 /2);
		window.open('tempList.do', '임시 글 목록', 'status=no, width=600, height=800, left='+popupX+', top='+popupY);
	}
	//등록
	function submitform(){
		if(sorted_files.length !== 0){//b1. 이미지가 있으면
			encodeImageFiles(afterReading);
		}else{//이미지가 없으면
			inputOpen();
			submitFormAction();
		}
		//b3. imgs 담긴 input 생성
		function afterReading(encodedImages){
			for (let i=0; i<encodedImages.length; ++i){
				let input = document.createElement('input');
				input.type = 'hidden';
				input.name = 'imgs'+i;
				input.value = encodedImages[i];
				f.appendChild(input);
				document.body.appendChild(f);
				if(i===encodedImages.length-1){
					break;
				}
			}
			inputOpen();
			submitFormAction();
		}
		//b4. 공개여부 input에 넣기
		function inputOpen(){
			let input = document.createElement('input');
			input.type = 'hidden';
			input.name = 'feed_open';
			input.value = isOpen;
			f.appendChild(input);
			document.body.appendChild(f);
		}
		//b5. submit동작
		function submitFormAction(){
			f.action = 'feedFormOk.do';
			f.submit();
		}
	}
	//임시저장
	function saveform(){
		f.action='feedSave.do';
		f.submit();
	}
	//b2. 이미지 Base64로 전환
	function encodeImageFiles(callback) {
		let encodedImages = []; //b2-1. base64로 변환된 이미지 배열
		
		for (let i = 0; i < sorted_files.length; i++) {
	        let reader = new FileReader();
	        //b2-3. 하단의 reader.readAsDataURL이 완료되면 실행됨
	        reader.onload = function () {
	            let base64Data = reader.result.split(",")[1];
	            console.log('용량 검사 전: 'base64Data.size);
	            
	            //b2-4. 용량검사, 줄이기
	            const imgSize = base64Data.size;
	            while(imgSize > 524288){
		            const tempCanvas = document.createElement('canvas');
		            const ctx = tempCanvas.getContext('2d');
		            
		            tempCanvas.width = base64Data.target.width;
		            tempCanvas.height = base64Data.target.height;
		            ctx.drawImage(base64Data.target, 0, 0);
		            
		            base64Data = tempCanvas.toDataURL('image/jpeg', 0.5);
		            console.log('용량 변화 후: 'base64Data.size);
	            }
	            
	            encodedImages.push(base64Data); //b2-5. 배열에 추가
	            if(sorted_files.length === encodedImages.length){
			        callback(encodedImages);//b2-6. 이미지 전부 변환하면 콜백으로 afterReading 실행
	            }
	        };
	        reader.readAsDataURL(sorted_files[i]);//b2-2. 다 읽어오면 onload실행
	    }
	}
	//a1-2. 이미지 유효성 검사
	function checkExtension(fileName,fileSize){
		let ext = fileName.toLowerCase().substring(fileName.lastIndexOf(".")+1, fileName.length);
        let maxSize = 20971520;  //20MB

        if(ext != 'jpg' && ext != 'jpeg' && ext != 'png'){
            alert("jpg, jpeg, png 파일만 업로드할 수 있습니다.");
            return false;
        }
        if(fileSize >= maxSize){
            alert('최대 20mb까지 업로드 가능합니다.');
            return false;
        }
        return true;
    }
	
	//이미지 업로드
	window.onload = function(){
		let btnAtt = document.getElementById('btnAtt');
		let attZone = document.getElementById('attZone');
		
		btnAtt.onchange = function(){
			//a1. 추가한 이미지 가져오기
			let files = btnAtt.files;
			//a1-1. 이미지 4개 제한
			if(sorted_files.length + files.length > 4){
				alert('이미지는 4개까지 업로드 가능합니다.');
				return;
			}
			
			for(file of files){
				let fileName = file.name;
				let fileSize = file.size;
				let isOk = checkExtension(fileName,fileSize);
				if(!isOk){
					return;
				}
			}
			
			//a2. 추가한 이미지 목록에 추가
			for(file of files){
				sorted_files.push(file);
			}
			
			function setPriview(){
				//a3. 기존 미리보기 지우기
				attZone.replaceChildren();
				let index = 0;
				//a4. 이미지 미리보기 추가
				for(file of sorted_files){
					const imgBox = document.createElement('div');
					const image = document.createElement('img');
					const delBtn = document.createElement('button');
					imgBox.className = 'col-auto';
					imgBox.id = 'imgBox'+index;
					imgBox.style = 'display:inline-block; position:relative; width:120px; height:120px; margin-right: 10px;';
					image.src = URL.createObjectURL(file);
					image.className = 'imgPreviewCrop';
					delBtn.className = 'btn-close';
					delBtn.type='button';
					delBtn.style='position: absolute; right: 0px; top: 0px;'
					
					delBtn.onclick= function(event){
						let indexNow = event.target.parentNode.getAttribute('id').substring(6,7);
						sorted_files.splice(indexNow, 1);
						setPriview();
					}
					
					imgBox.appendChild(image);
					imgBox.appendChild(delBtn);
					attZone.appendChild(imgBox);
					
					index += 1;
				}
			}
			setPriview();
		};
	};
	
</script>



<body>
	<!-- 전체 -->
  	<main class="d-flex flex-nowrap">
  	
  		<!-- 사이드 바 -->
  		<%@include file="../user/sns_top.jsp" %>
		
		<!-- 상단 배젤 -->
		<div class="flex-shrink-0 p-3 bg-white" style="width: 70%; min-width: 30rem; max-width: 60rem;">
			<div class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold">새 글 쓰기</span>
			</div>
			
			<!-- 글쓰기 폼 -->
			<form name="f" id="feedForm" method="POST" action="" enctype="multipart/form-data">
				<!-- 이부분 el로 받아오기 -->
				<input name="feed_num" type="hidden" value="${feed_num}"/><!-- feed_num 임시저장 불러올 경우 덮어씌워짐. 아니면 null -->
				<input name="mem_num" type="hidden" value="${login_mem.mem_num}"/><!-- mem_num 지금은 데이터 1 하나만 존재-->
				
				<div class="container">
					<!-- 글 상자 -->
	   				<div class="row mb-3">
		   				<div class="col" align="center">
		   					<textarea class="form-control" name="feed_content" id="textarea" rows="10"></textarea>
		   				</div>
		   			</div>
		   			<!-- 태그 버튼 -->
		   			<div class="row mb-1" style="padding: 10px">
			   			<c:forEach var="theme" items="${listTheme}" varStatus="status"><!-- 테마 리스트 listTheme로 넘겨받기 -->
		   					<div class="col-auto" align="left" style="margin: 5px -5px;">
						   		<input type="checkbox" class="btn-check h-75" id="btn-check-${status.count}" name="theme" value="${theme.theme_num}" autocomplete="off">
								<label class="btn btn-outline-secondary" for="btn-check-${status.count}">#${theme.theme_name}</label>
							</div>
						</c:forEach>
					</div>
		   			<!-- 이미지업로드, 임시목록, 보드게임 카페 선택, 잠금설정 줄 -->
		   			<div class="row mb-3">
		   				<div class="col" align="left">
		   					<button type="button" class="btn btn-sm btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#image-collapse" aria-expanded="false">
			         			<svg class="bi pe-none me-2" fill="#A6A6A6" width="24" height="24"><use xlink:href="#img-select"></use></svg>
			         			사진 첨부
			        		</button>
			        		<button type="button" class="btn btn-sm rounded border-0" onclick="javascript:openplace()">
			         			<svg class="bi pe-none me-2" fill="#A6A6A6" width="20" height="20"><use xlink:href="#location"></use></svg>
			         			장소 추가
			        		</button>
			        		<button type="button" class="btn btn-sm rounded border-0" onclick="javascript:opentemplist()">
			         			<svg class="bi pe-none me-2" fill="#A6A6A6" width="20" height="20"><use xlink:href="#collection"></use></svg>
			         			임시 글 목록
			        		</button>
			        		<button id="lock_btn" type="button" class="btn btn-sm rounded border-0" onclick="javascript:lockUnlock()">
			         			<svg id="lock_svg" class="bi pe-none me-2" fill="#A6A6A6" width="20" height="20"></svg>
			         			<span id="lock_span"></span>
			        		</button>
			        		<!-- 이미지 선택 박스 -->
			        		<div class="collapse" id="image-collapse">
				        		<div id="image_preview">
									<input type="file" id="btnAtt" multiple="multiple" class="mt-2 small" style="margin-left: 20px;" accept=".png, .jpg, .jpeg" enctype="multipart/form-data"/>
									<div id="attZone" class="row small mt-2" style="margin-left: 20px;" data-placeholder="파일을 첨부 하려면 파일 선택 버튼을 클릭하거나 파일을 드래그앤드롭 하세요"></div>
								</div>
							</div>
						</div>
	   				</div>
	   				<div class="row">
	   					<div class="col" align="right">
		   					<button type="button" class="btn btn-outline-primary btn-sm" onclick="history.back()">취소</button>&nbsp;&nbsp;
		   					<button type="button" class="btn btn-outline-primary btn-sm" onclick="javascript:saveform()">임시저장</button>&nbsp;&nbsp;
		   					<button type="button" class="btn btn-outline-primary btn-sm" onclick="javascript:submitform()">피드 작성</button>
		   				</div>
	   				</div>
	   			</div>
   			</form>
		</div>
	
	</main>

</body>
<%@include file="../user/user_bottom.jsp" %>