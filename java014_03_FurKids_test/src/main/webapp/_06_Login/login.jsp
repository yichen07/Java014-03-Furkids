<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FurKids_login</title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
</head>
<body>
<jsp:include page="/fragment/navigation.jsp" />
<main>
    <div class="container focus text-center text-white mb-5 text-shadow">
        <div class="row">
            <div class="col">
                <h1 class="title">會員登入</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-6 mx-auto">
                <!--<form action="<c:url value='/_01_Member/Login' />" method="POST">-->
                    <div class="form-group">
                        <label for="account"><p1>帳號：</p1></label>
                        <input type="text" name="account" value="">
                    </div>
                    <div class="form-group">
                        <label for="password"><p1>密碼：</p1></label>
                        <input type="password" name="password" value="">
                    </div>
                    <div class="form-group form-check">
                        <!--<input type="checkbox" class="form-check-input" id="exampleCheck1"
                            <c:if test='${requestScope.rememberMe==true}'>
                                checked='checked'
                            </c:if> 
                        value="true">-->
                        <label class="form-check-label" for="exampleCheck1"><p1>保持登入</p1></label>
                    </div>
                    <button type="submit" class="btn btn-primary" data-toggle="modal">
                            登入
                    </button>                    
                    <a href="#">
                        <button type="button" class="btn btn-primary" data-toggle="modal">
                            忘記密碼？
                        </button>
                    </a>
                    <div class="form-group">
                        <br><a href="#">註冊會員</a>
                    </div>
                </form>
            </div>   
        </div>
    </div>
</main>
<footer class="fixed-bottom">
    <h4>© 2020 FurKids 著作權所有</h4>
</footer>
</body>
</html>