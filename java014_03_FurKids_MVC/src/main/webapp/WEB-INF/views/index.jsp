<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Furkids Main Page</title>
</head>
<body>

<!-- 引入共同的頁首 -->
<%-- <%@ include file="/fragment/navigation.jsp" %> --%>
<jsp:include page="/fragment/navigation.jsp" />
<%-- <jsp:include page="/WEB-INF/views/fragment/navigation.jsp" /> --%>


<!-- 首頁內容 -->
<div style="height: 800px">

</div>



<!-- 引入詳細的頁尾 -->
<jsp:include page="/fragment/footer_detail.jsp" />

</body>
</html>