/**
 * 
 */
 const writeBtn = document.getElementById("writeBtn");
 writeBtn.addEventListener("click", writeBtn_f);
  const findBtn = document.getElementById("findBtn");
 findBtn.addEventListener("click", findBtn_f);
 
function writeBtn_f(e) {
   window.location.href="./writeForm";
   }
   
function findBtn_f(e){
	const searchTypeTag = document.getElementById('searchType');
	const keywordTag = document.getElementById('keyword');
	if(keywordTag.value.trim().length == 0){
		alert("검색어를 입력하세요");
		keywordTag.select();
		return false;
		};
		console.log(searchTypeTag.value,keywordTag.vlaue);
		const url =`/portfolio/board/list/1/${searchTypeTag.value}/${keywordTag.value}`;
		window.location.href=url;
}
