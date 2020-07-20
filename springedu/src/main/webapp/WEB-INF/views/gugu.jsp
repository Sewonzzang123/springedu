<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/portfolio/gugudan">
	<input type=text " name="dansu" id="dansu" placeholder="구구단 단수 입력하세요">
	<button>계산하기</button>
	<br> 단수: ${requestScope.dansu }
	<br>
	<c:set var="dansu" value="3" scope="page"/>
	<c:remove var="dansu" scope="page"/>
	<c:forEach var="i" begin="1" end="9">
	<c:if test="${pageScope.i%2 ==0}">
	<p>
	${dansu } * ${pageScope.i } =
	 ${dansu * pageScope.i}</p>
	 </c:if>
	 <c:if test="${!(pageScope.i%2 ==0)}"> 
	 	<p>
	${dansu } * ${pageScope.i } =
	 ${dansu * pageScope.i}</p>
	 </c:if>
	</c:forEach>
	</form>
	</body>
</html>