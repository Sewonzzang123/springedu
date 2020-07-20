<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>덧셈 계산기</title>
</head>
<body>
<form method="GET" action="/portfolio/cal/result">
<input type="text" name="op1" id="op1"/>+
<input type="text" name="op2" id="op2"/>
<button>전송</button>
결과: ${requestScope.result }
</form>
</body>
</html>