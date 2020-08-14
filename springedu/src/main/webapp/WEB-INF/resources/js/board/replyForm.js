const writeBtn = document.getElementById("writeBtn");
const cancelBtn = document.getElementById("cancelBtn");
const listBtn = document.getElementById("listBtn");
const writeFrm = document.getElementById("writeFrm");

writeBtn.addEventListener("click", writeBtn_f);
cancelBtn.addEventListener("click", cancelBtn_f);
listBtn.addEventListener("click", listBtn_f);

function writeBtn_f(e) {
  e.preventDefault();
  console.log("writeBtn_f");

  //1)유효성 체크

  if (!checkValidation()) {
    return false;
  }
  //2)서버 전송
	writeFrm.submit();
}
function cancelBtn_f(e) {
  console.log("cancelBtn_f");
  //입력한 내용 clear
  writeFrm.reset();
}
function listBtn_f(e) {
  console.log("listBtn_f");
  //목록리스트로 이동
  location.href = "/portfolio/board/list";
}
//유효성 체크
function checkValidation() {
  //분류지정
  /*
  const cidTag = document.getElementById("boardCategoryVO.cid");
  if (cidTag.options[cidTag.selectedIndex].value == 0) {
 	document.getElementById('boardCategoryVO.cid.error').textContent="분류를 지정해주세요";
    return false; 
  } */
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
	//let idExpReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-A]{2,3}$/i;
	//if(!idExpReg.test(bidTag.value)){
	//bidTag.select();
	//  document.getElementById('bid.error').textContent="이메일 형식에 맞지 않습니다 ex)test@test.com";
	//return false;
	//}

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
