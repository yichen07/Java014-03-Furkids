<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FurKids_welcome</title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="/fragment/navigation.jsp" />
<main>
    <h3>歡迎[${requestScope.user.userName }]登入</h3>
</main>
<footer class="fixed-bottom">
    <h4>© 2020 FurKids 著作權所有</h4>
</footer>
</body>
</html>