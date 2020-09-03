<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<title>최신영화 순위 보기</title>
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<script defer src="${contextPath }/js/openapi/movie.js?ver=2"></script>
</head>
<style>
caption{
	
}
table {
	border-collapse: collapse;
}

th, td {
	border: 1px solid;
}

td {
	font-size: 1rem;
	letter-spacing: normal;
}

thead th {
	text-align: center;
	font-size: 1.2em;
	font-width: bold;
	letter-spacing: 0.1em;
}

caption {
	font-style: italic;
	font-width: bold;
	caption-side: top;
	padding: 20px;
	color: #666;
	text-align: center;
	letter-spacing: 0.2em;
}
.search{
	margin-top:10px;
	display:flex;
	justify-content:flex-end;
	}
</style>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

	<!-- header -->
	<%--   <%@ include file="/WEB-INF/views/include/header.jsp" %> --%>

	<!-- 메뉴 -->
	<%@ include file="/WEB-INF/views/include/menu.jsp"%>

	<!-- 본문 -->
	<main>
		<div class="container">
			<div class="content">
				<div id="movie_rank" class="cont_reserve">
					<table>
						<caption>영화 순위</caption>
				
						<thead>
							<tr>
								<th>순위</th>
								<th>제목</th>
								<th>개봉일</th>
								<th>당일매출액</th>
								<th>관객수</th>
							</tr>
						</thead>
						<tbody id="movie-body">
							<%--	
						<tr>
							<td>1</td>
							<td>테넷</td>
							<td>2020.09.01</td>
							<td>1000</td>
							<td>100</td>
						</tr>
						 --%>
						</tbody>
					</table>
					<div class="search">
							<input type="date" class="movieDaily"/><button class="movieDailyBtn">조회</button>
							</div>
				</div>
			</div>
		</div>
	</main>
	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>