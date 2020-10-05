<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="icon" href="<c:url value='/resources/images/logo_08_iP6_6.ico' />" type="image/x-icon" />
<title>商家分店新增</title>
<style type="text/css">
span.error {
	color: red;
	display: inline-block;
	font-size: 10pt;
}

.error {
	color: red;
	display: inline-block;
	font-size: 10pt;
}

input[type=text]{
	font-size: 12pt;
}
	
body {
	background-attachment: fixed;
	background-color: #EBFFEB;
	background-repeat: no-repeat;
	background-position: 20px 50px;
}

h1 {
	font-family: "標楷體", "新細明體", sans-serif;
	font-size: 24px;
}
.formBkgnd {
	color: #FFFFFF;
	background-color: #666666;
}
label {
	float:left;
	width:8em;
	font-weight:bold;
	color:#000000;
	margin-top:10px;
	margin-bottom:2px;
	margin-right:10px;
	text-align: right;
}

br {
	clear:both;
}
.fieldWidth {
    margin-top:10px;
	margin-bottom: 2px;
	width: 200px;
	background:#F6E497;
	font-size:1.1em;
}
/* 設定字體大小 */
.fontSize {
	font-size:1.1em;
}

#main {
    position:relative;
	left:70px;
	width:600px;
	height:543px;	
	top: 0px;
	z-index:2;
	font-size:0.9em; 
}
/* 設定傳送鈕的樣式 */
#submit {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#FFFFFF;
	margin-right:1.5em;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#A9A9A9;
}
/* 設定取消鈕的樣式 */
#cancel {
	width:64px;
	height:30px;
	font-size:1.2em
	color:#ffffff;
	border-width:2px;
	border-color: #FFEDAF #B2A268 #B2A268 #FFEDAF;
	background:#a9a9a9;
}

/* #errorMsg { */
/*     position:relative; */
/*     top:0px;  */
/*     left:0px;     */
/* 	color:#FF0000; */
/* 	font-size:0.8em; */
/* } */

</style>
<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId(){   
	 document.forms[0].mid.focus();   // 將游標放在mid欄位內
}
</script>
</head>


<body onLoad="setFocusToUserId()" >

<c:set var="funcName" value="REG" scope="session"/>

<!-- 引入共同的頁首 -->
<jsp:include page="/fragment/navigation.jsp" />

  <div align='center' id="content"> 
  
 <form:form method="POST" modelAttribute="merchantChildBean" enctype='multipart/form-data'>
  
  <Table  style="width:900px ;background-color: #E7CDFF; cellspacing:0; border:2px solid black; " >
	<tr height="40" >
		<td colspan='4' style="text-align: center; vertical-align: middle;">
			<Font color="#006600" size='6' face="標楷體">${AppName}</Font>
		</td>
	</tr>
	
	<tr height="36" >
		<td colspan='4' style="text-align: center; vertical-align: middle;">
        	<Font color="#006600" size='5' face="標楷體">商家分店新增</Font>
		</td>
	</tr>
	                    
    <tr height="16" >
    <td colspan='4'  style="text-align: center; vertical-align: middle;">
	    	<div class="error">${errorSaveData}<br>
	    	</div>
    </td>
    </tr>
       
     <tr height="52">
     	<td style="width: 90px;">
        	<label class="fontSize" >商家分店名稱：</label><br>&nbsp;
        </td>
        <td style="width: 290px;">
      		<form:input path='busChildName' class="fieldWidth" style="width: 200px;"/><br>&nbsp;
      		<form:errors path="busChildName" cssClass="error" />
      	</td>
      	<td>
      	 	<label class="fontSize" >分店電子信箱：</label><br>&nbsp;
      	</td>
      	<td>
      		<form:input type="email" path='busChildEmail'  class="fieldWidth" style="width: 200px;"/><br>&nbsp;
      		<form:errors path="busChildEmail" cssClass="error" />      
      	</td>
      <tr height="52">
        <td> 
      		<label class="fontSize" >分店電話：</label><br>&nbsp;
      	</td>
      	<td>
      		<form:input path='busChildTel' class="fieldWidth" style="width: 200px;"/><br>&nbsp;
      		<form:errors path="busChildTel" cssClass="error" /> 
      	</td>
        <td>
      		<label class="fontSize" >分店地址：</label><br>&nbsp;
      	</td>
      	<td>	
      		<form:input path='busChildAddress' class="fieldWidth" style="width: 200px;"/><br>&nbsp;
 			<form:errors path="busChildAddress" cssClass="error" />       		      
      	</td>
      
     </tr>
     <tr height="52">
      	<td>
      		<label class="fontSize" >照片：</label><br>&nbsp;
      	</td>
      	<td>	
      		 <form:input type='file' path="merchantChildMultipartFile" /><br>&nbsp;
	   	  	 <form:errors path="merchantChildMultipartFile"  cssClass="error" />
        </td>      	
     	<td>
      		<label class="fontSize" >分店描述：</label><br>&nbsp;
      	</td>
      	<td>	
      		<form:textarea path='busChildDescription' class="fieldWidth" style="width: 200px;"/><br>&nbsp;
      		<form:errors path="busChildDescription" cssClass="error" /> 
        </td>
      </tr>
     <tr height="42">
        <td colspan='4'>
      		<div id="btnArea" align="center">
        	 	<input type="submit" name="submit" id="submit" value="儲存"/>
         		<input type="reset" name="cancel" id="cancel" value="重填">
      		</div>
		</td>
	</tr>
	
</Table>
</form:form>
</div>

	<!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
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
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->
	
	
	<!-- navigation bar js ------------------------------------->
		<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->

</body>
</html>