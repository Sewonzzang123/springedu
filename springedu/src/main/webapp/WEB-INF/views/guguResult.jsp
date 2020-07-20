<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<form method="POST" action="/portfolio/gugu/guguResult">
<input type="text" name="op1" id="op1"placeholder="구구단 단수 입력하세요"/>
<button>계산하기</button><br>
결과: ${requestScope.result }
</form>
</body>
</html>