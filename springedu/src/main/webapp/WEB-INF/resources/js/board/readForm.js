const replyBtn = document.getElementById("replyBtn");
const cancelBtn = document.getElementById("cancelBtn");

const modifyBtn = document.getElementById("modifyBtn");
const saveBtn = document.getElementById("saveBtn");
const deleteBtn = document.getElementById("deleteBtn");

//공통 버튼
const listBtn = document.getElementById("listBtn");

changeMode(false); //읽기모드

if(replyBtn){replyBtn.addEventListener("click", replyBtn_f);}
if(cancelBtn){cancelBtn.addEventListener("click", cancelBtn_f);}
if(listBtn){listBtn.addEventListener("click", listBtn_f);}
if(modifyBtn){modifyBtn.addEventListener("click", modifyBtn_f);}
if(saveBtn){saveBtn.addEventListener("click", saveBtn_f);}
if(deleteBtn){deleteBtn.addEventListener("click", deleteBtn_f);}


function saveBtn_f(e) {
  console.log("saveBtn_f");
}

function cancelBtn_f(e) {
	e.preventDefault();
	
  console.log("cancelBtn_f");
  
  //수정모드 -> 읽기모드
  changeMode(false);
}

function listBtn_f(e) {
  console.log("listBtn_f");
  //목록리스트로 이동
  location.href = "/portfolio/board/list";
}
//답글
function replyBtn_f(e){
console.log("replyBtn_f");

}
//수정
function modifyBtn_f(e){
console.log("modifyBtn_f");
	changeMode(true);//수정모드
}


function changeMode(modeFlag){
	const rmodes = document.getElementsByClassName("rmode");
	const umodes = document.getElementsByClassName("umode");
	//읽기모드->수정모드
	if(modeFlag){
	
	//제목변경 ->게시글 보기
	document.getElementById('title').textContent = '게시글 수정';
	//카테고리, 제목, 내용 활성화
	document.getElementById('boardCategoryVO.cid').removeAttribute('disabled');
	document.getElementById('btitle').removeAttribute('readonly');
	document.getElementById('bcontent').removeAttribute('readonly');
	
	Array.from(rmodes).forEach(rmodes=>{rmodes.style.display="none";});
	Array.from(umodes).forEach(umodes=>{umodes.style.display="block";});
	
	modeFlag = false;
	//수정모드 ->읽기모드
	}else{
	document.getElementById('title').textContent = '게시글 보기';
	//카테고리, 제목, 내용 비활성화
	document.getElementById('boardCategoryVO.cid').setAttribute('disabled', true);
	document.getElementById('btitle').setAttribute('readonly', true);
	document.getElementById('bcontent').setAttribute('readonly', true);
	
	Array.from(umodes).forEach(umodes=>{umodes.style.display="none";});
	Array.from(rmodes).forEach(rmodes=>{rmodes.style.display="block";});
		modeFlag = true;
	}

}
//삭제
function deleteBtn_f(e){
if(confirm("삭제하시겠습니까?")){
const bnum= e.target.getAttribute("data-bnum");
const url = `/portfolio/board/delete/${bnum}`;
window.location.href=url;
}

}
//유효성 체크
function checkValidation() {
  //분류지정
  const cidTag = document.getElementById("boardCategoryVO.cid");
  if (cidTag.options[cidTag.selectedIndex].value == 0) {
 	document.getElementById('boardCategoryVO.cid.error').textContent="분류를 지정해주세요";
    return false;
  }
  //제목
  const btitleTag = document.getElementById("btitle");
  if (btitleTag.value.trim().length == 0) {
  btitleTag.select();
   	document.getElementById('btitle.error').textContent="제목을 입력하세요";
    return false;
  }
  //작성자
  const bidTag = document.getElementById("bid");
   if (bidTag.value.trim().length == 0) {
     bidTag.select();
   document.getElementById('bid.error').textContent="아이디를 입력하세요";
    return false;
  }
  //정규표현식 
	let idExpReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-A]{2,3}$/i;
	if(!idExpReg.test(bidTag.value)){
	bidTag.select();
	  document.getElementById('bid.error').textContent="이메일 형식에 맞지 않습니다 ex)test@test.com";
	return false;
	}

  //내용
  const bcontentTag = document.getElementById("bcontent");
  if (bcontentTag.value.trim().length < 4) {
  bcontentTag.select();
   document.getElementById('bcontent.error').textContent="내용은 4자 이상으로 입력하세요";
    return false;
  }
	//파일 사이즈
	const filesTag = document.getElementById('files');
	const sum=0;
	//첨부파일 합 구하기
	Array.from(filesTag).forEach(files=>(sum += file.size));
	/*	
	Array.from(filesTag).forEach(
		function(file){
			sum += file.size
		}
	);
*/
	if(sum>10485760){
	document.getElementById('files.error').textContent="10MB 이하로 첨부 가능합니다.";
	return false;
	}
	
	
  return true;
}
