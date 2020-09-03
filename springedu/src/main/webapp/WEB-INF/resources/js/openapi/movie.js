'use strict'

	const movie_rankTag = document.getElementById('movie_rank');
	const movie_bodyTag = document.getElementById('movie-body');
	const movieDailyBtn = document.querySelector('.movieDailyBtn');
	
	movieDailyBtn.addEventListener('click',(e)=>{
	//	console.log('클릭!'+e.target);
	//	console.log(document.querySelector('.movieDaily').value);
		const day=document.querySelector('.movieDaily').value;
		const searchDay= replaceAll(day,'-','');
		rankDailyMovie(searchDay);		
	});
	

	const today = new Date();
	today.setDate(today.getDate()-1);
	rankDailyMovie(getFormatDate(today));	
	
	//날짜를 입력받아 8자리 날짜포맷으로 변경(ex:20200902)
	function getFormatDate(date){
	const l_year = date.getFullYear();
	
	const l_month_tmp = date.getMonth() + 1 ;
	const l_month = l_month_tmp < 10 ? '0'+ l_month_tmp : l_month_tmp;
	
	
	const l_date = date.getDate() < 10 ? '0'+ date.getDate() : date.getDate();
	
	return l_year+l_month+l_date;
	};
	

	 
	function replaceAll(str, searchStr, replaceStr) {
  	return str.split(searchStr).join(replaceStr);
	}
	

	function rankDailyMovie(day){	
	//1)XMLHTTPRequest 객체 생성
	const xhttp = new XMLHttpRequest();
	//2)서버응답 처리
	//readyState
	// 0 : open()가 호출되지 않은 상태
	// 1 : open()가 실행된 상태 server 연결됨
	// 2 : send()가 실행된 상태,  서버가 클라이언트 요청을 받았음.
	// 3 : 서버가 클라이언트 요청 처리중. 응답헤더는 수신했으나 바디가 수신중인 상태
	// 4 : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된상태
	xhttp.addEventListener("readystatechange",(e)=>{
		if(e.target.readyState == 4 && e.target.status == 200){

			if(!e.target.response) return;

			const jsonObj = JSON.parse(e.target.response);

			let str = "";
			Array.from(jsonObj.boxOfficeResult.dailyBoxOfficeList).forEach((movie)=>{
    			str += `<tr>`;
    			str += `    <td>${movie.rank}</td>`;
    			str += `    <td>${movie.movieNm}</td>`;
    			str += `    <td>${movie.openDt}</td>`;
    			str += `    <td>${movie.salesAmt}</td>`;
    			str += `    <td>${movie.audiCnt}</td>`;
    			str += `  </tr>`;
			});
			movie_bodyTag.innerHTML = str;
		}
	});

	//3) 요청메서드 + 요청URL
	const l_url = "http://localhost:9080/portfolio/movie/rank/"+day;
	xhttp.open('GET', l_url);

	//6) 요청
	xhttp.send(null);
	}