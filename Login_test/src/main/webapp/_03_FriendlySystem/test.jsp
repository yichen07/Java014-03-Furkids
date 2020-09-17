<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script
	src="${pageContext.request.contextPath}/javascript/Convenience.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約上架</title>
</head>
<body>
	<c:forEach var="Convenience" items="${AllConvenience}">
		<div  style='width:10%;text-align:center;border:1px solid;margin:2px;float:left'>
			<tr>
				<td>${Convenience.value.busAccount}</td><br>
				<td>${Convenience.value.conItem}</td><br>
				<td>${Convenience.value.conItemList}</td><br>
				<td>${Convenience.value.conDateTime}</td><br>
				<td>${Convenience.value.busName}</td><br>
				<td>${Convenience.value.busTel}</td><br>
				<td>${Convenience.value.busAddress}</td><br>
				<td>${Convenience.value.busDescription}</td><br>
				<td>${Convenience.value.busEmail}</td><br>	
			</tr>
		</div>
	</c:forEach>
		<div>
			<button id="adConvenience">新增</button>
		</div>
	
</body>
</html>