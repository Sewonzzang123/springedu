<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<title>게시글 작성</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet" href="${contextPath }/css/board/board.css">
<link rel="stylesheet" href="${contextPath }/css/board/writeForm.css">
<script defer src="${contextPath }/js/board/writeForm.js?ver=22"></script>

</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="/WEB-INF/views/include/uppermost.jsp"%>

	<!-- header -->
	<%@ include file="/WEB-INF/views/include/header.jsp"%>

	<!-- 메뉴 -->
	<%@ include file="/WEB-INF/views/include/menu.jsp"%>

	<!-- spring form 태그를 사용해보자 ! form:form-->
	<!-- modelAttribute가 boardVO값을 읽어서 display해준다. -->
	<!-- 작성한 내용이 유효성체크에서 어긋날경우, 그 내용이 boardVO에 남아있어서 사라지지않게해준다. -->
	<!-- 본문 -->
	<main>
		<div class="container">
			<div class="content">
				<!-- 아이디는 파일이름이랑 다르게 설정해야한다. -->
				<form:form id="writeFrm" method="POST"
					action="${contextPath }/board/write" 
					enctype="multipart/form-data"
					modelAttribute="boardVO">
					<legend>게시글 작성</legend>
					<ul>
						<li><form:label path="boardCategoryVO.cid">분류</form:label> 
						<form:select path="boardCategoryVO.cid">
								<option value="0">선택<option>
								<form:options path="boardCategoryVO.cid" 
															items="${boardCategory }" 
															itemValue="cid"
															itemLabel="cname"/>
							</form:select> 
							<span class="client_msg" id="boardCategoryVO.cid.error"></span>
							<form:errors cssClass="svr_msg" path="boardCategoryVO.cid" /></li>
						<li><form:label path="btitle">제목</form:label> <form:input
								type="text" path="btitle" /> 
								<span class="client_msg" id="btitle.error"></span><form:errors cssClass="svr_msg"
								path="btitle" /></li>
						<li><form:label path="bid">작성자</form:label> <form:input
								type="text" path="bid" /> 
								<span class="client_msg" id="bid.error"></span>
									<form:errors cssClass="svr_msg"
								path="bid" /></li>
						<li><form:label path="bcontent">내용</form:label> <form:textarea
								path="bcontent" rows="10"></form:textarea> 
									<span class="client_msg" id="bcontent.error"></span>
									<form:errors
								cssClass="svr_msg" path="bcontent" /></li>
						<li><form:label path="files">첨부파일</form:label> <form:input
								type="file" path="files" multiple="multiple" /> 
								<span class="client_msg" id="files.error"></span><form:errors
								path="files" /></li>
						<li><form:button type="button" id="writeBtn"
								class="btn btn-outline-success">등록</form:button> <form:button
								type="button" id="cancelBtn" class="btn btn-outline-danger">취소</form:button>
							<form:button type="button" id="listBtn"
								class="btn btn-outline-warning">목록</form:button></li>
					</ul>
				</form:form>
			</div>
		</div>
	</main>



	<!-- 푸터 -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>




