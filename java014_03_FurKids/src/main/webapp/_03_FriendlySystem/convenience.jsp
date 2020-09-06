<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>預約上架</title>
</head>
<body>
	<c:forEach var="Convenience" items="${AllConvenience}">
		<div style='width: 200px;  border: 1px solid; margin: 2px; float: left'>
			<table style='text-align: center'>
				<tr><td>${Convenience.busAccount}		
				<img height='100' width='180' class="img-responsive center-block"
     src='${pageContext.servletContext.contextPath}/_03_/FriendlyService/getBusChildImage?busChildNo=${Convenience.busChildNo}' />
				</td></tr>
				<tr><td>${Convenience.merchantChildBean.busChildName}</td></tr>
				
				<tr><td>${Convenience.conItem}</td></tr>
				
				<tr><td>${Convenience.conItemList}</td></tr>
				
				<tr><td>${Convenience.conCloseDay}</td></tr>
				
				<tr><td>${Convenience.conOpenTime}</td></tr>
				
				<tr><td>${Convenience.conCloseTime}</td></tr>
						
				<tr><td>${Convenience.merchantChildBean.busChildTel}</td></tr>
				
				<tr><td>${Convenience.merchantChildBean.busChildAddress}</td></tr>
				
				<tr><td>${Convenience.merchantChildBean.busChildDescription}</td></tr>
				
				<tr><td>${Convenience.merchantChildBean.merchantbean.busEmail}</td></tr>
			</table>
		</div>
	</c:forEach>



<!-- 下拉式按鈕 -->
<div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    新增服務
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
  	<c:forEach var="NoConvenience" items="${NotConvenience}">
    	<button name="submit1" value="${NoConvenience.busChildName}" class="dropdown-item" type="button" data-toggle="modal" data-target="#exampleModal">${NoConvenience.busChildName}</button>
    </c:forEach>
  </div>
</div>
	
<!-- 彈出表格	 -->
	<div id='dv' style="display:none;">
		<c:forEach var="NoConvenience" items="${NotConvenience}">
			<div class="ppp" style="border: 1px solid;float: left;display:none;">
			<form method="POST" action="<c:url value='/_03_ConvenienceProcess/ConInsert.do' />">
				<table>
						<tr><td><input type="hidden" name="busChildNo" value="${NoConvenience.busChildNo}" ></td></tr>
						<tr><td class="aaa" style='text-align: center'>${NoConvenience.busChildName}</td></tr>
						<tr><td><label>服務項目：</label></td></tr>
						<tr><td><input type="text" name="convenience"></td></tr>
						<tr><td><label>服務細項：</label></td></tr>
						<tr><td><input type="text" name="conveniencelist"></td></tr>
						<tr><td><label>公休日：</label></td></tr>
						<tr><td>
						<select name="condcloseday">
   							<option value="星期一">星期一</option>
   							<option value="星期二">星期二</option>
    						<option value="星期三">星期三</option>
    						<option value="星期四">星期四</option>
   							<option value="星期五">星期五</option>
    						<option value="星期六">星期六</option>
    						<option value="星期日">星期日</option>
						</select>
						</td></tr>
						<tr><td><label>開始營業時間：</label></td></tr>
						<tr><td><input type="text" name="conopentime"></td></tr>
						<tr><td><label>結束營業時間：</label></td></tr>
						<tr><td><input type="text" name="conclosetime"></td></tr>
						<tr>
							<td><input type="submit" name="submit" id="submit" value="新增"/><td>
							<td><input type="button" class="cancel" value="取消"><td>
						</tr>
					
				</table>
			</form>
			</div>
		</c:forEach>
	</div>
		





	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>
<script src="../resources/javascript/Convenience.js"></script>
</body>
</html>