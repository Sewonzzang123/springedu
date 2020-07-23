<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 공통 모듈 -->
<%@ include file="/WEB-INF/views/include/common.jsp" %>

<title>초기화면</title>
<link rel="stylesheet" href="${contextPath }/css/main.css">
</head>
<body>
	<!-- 최상위메뉴 -->
	<%@ include file="./include/uppermost.jsp" %>

  <!-- header -->
  <%@ include file="./include/header.jsp" %>

  <!-- 메뉴 -->
  <%@ include file="./include/menu.jsp" %>

  <!-- 본문 -->
  <%@ include file="./include/main.jsp" %>

  <!-- 푸터 -->
  <%@ include file="./include/footer.jsp" %>  

</body>
</html>




