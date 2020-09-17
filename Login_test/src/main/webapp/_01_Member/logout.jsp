<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登出</title>
</head>
<body>

<!-- 先將使用者名稱取出 -->
<c:if test="${LoginOK.CLASSIFY == 0 }">
	<c:set var="memberName" value="${ LoginOK.cusName }" />
</c:if>
<c:if test="${LoginOK.CLASSIFY == 1 }">
	<c:set var="memberName" value="${ LoginOK.busName }" />
</c:if>


<!-- 移除放在session物件內的屬性物件 -->
<c:remove var="LoginOK" scope="session" />
<c:remove var="ShoppingCart" scope="session" />

<!-- 下列敘述設定變數funcName的值為OUT，top.jsp 會用到此變數 -->
<c:set var="funcName" value="OUT" scope="session"/>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

<!-- 下列六行敘述設定登出後要顯示的感謝訊息 -->
<c:set var="logoutMessage" scope="request"/>
<font color='blue' ><BR>
訪客${ memberName }，感謝您使用本系統。<BR>
您已經登出<BR>
</font>
<jsp:useBean id='logoutBean' class='_01_Member.Login.model.LogoutBean' scope='page' />
    
<c:set target='${logoutBean}' 
   property='session'    value='${pageContext.session}'/> <!-- target 設定某個Bean的性質 -->
   
${ logoutBean.logout }

<%-- <c:redirect url="/index.jsp"/> --%>
<%-- 
有上述敘述登出時會發生以下例外：
Stacktrace:] with root cause
java.lang.IllegalStateException: Cannot call sendRedirect() after the response has been committed
--%>

</body> 
</html>