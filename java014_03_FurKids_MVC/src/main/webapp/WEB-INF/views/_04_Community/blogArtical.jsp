<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>寵物健康專欄內文</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/blogArtical.css" />
</head>
<body>
	<jsp:include page="/fragment/navigation.jsp" />
	<div
		class="container-fliid blogArticalBannerImg d-flex justify-content-center align-items-center"
		id="imageStellar" data-stellar-background-ratio="0.5">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">寵物健康專欄</h1>
				<div
					class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">世界是平等的,請愛護動物沒有愛就沒有完美</h5>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<c:choose>
			<c:when test="${LoginOK.CLASSIFY == 0}">
				<c:set var="Account" value="${LoginOK.cusAccount}" />
				<c:set var="Name" value="${LoginOK.cusName}" />
			</c:when>
			<c:otherwise>
				<c:set var="Account" value="${LoginOK.busAccount}" />
				<c:set var="Name" value="${LoginOK.busName}" />
			</c:otherwise>
		</c:choose>
		<!-- ------------------------------------------------------------------- -->
		<h1 class="mt-5">${pcb.PCTitle}</h1>
		<div class="row">
			<div class="col-md-8">
				<!-- ------------------------------------------------------------------- -->
				<article>
					<div class="text-secondary mb-3">
						<small> <span class="mr-3"><i class="far fa-user">
									${pcb.PCFounder}</i></span> <span class="mr-3"><i
								class="far fa-calendar"> ${pcb.PCDateTime}</i></span>
						</small>
					</div>
					<!-- Modal -->
					<div class="modal fade" id="exampleModalCenter" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalCenterTitle"
						aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalCenterTitle">修改專欄</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form
										action="<c:url value="/UpdatePetColumn?PCID=${pcb.PCID}" />"
										method="POST" name="BlogIndexInsert"
										enctype='multipart/form-data'>
										<div class="form-group">
											<label for="blog-title" class="col-form-label">專欄標題:</label>
											<input type="text" class="form-control" id="blogTitle"
												name="blogTitle" value="${pcb.PCTitle}">
										</div>
										<div class="form-group">
											<label for="blog-img" class="col-form-label">上傳專欄圖片:</label>
											<input type="file" class="form-control" id="blogImg"
												name="blogImg">
										</div>
										<div class="form-group">
											<label for="blog-content" class="col-form-label">專欄內容:</label>
											<textarea class="form-control" id="blogContent"
												name="blogContent">${pcb.PCContent}</textarea>
										</div>
										<div class="modal-footer">
											<button type="submit" class="btn btn-primary">刊登</button>
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">取消</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- ------------------------------------------------------------------- -->
					<p>${pcb.PCContent}</p>
					<!-- ------------------------------------------------------------------- -->
					<img class="img-fluid"
						src="${pageContext.request.contextPath}/GetPCImage?PCID=${pcb.PCID}" />
				</article>

				<div class="card mb-5">
					<h5 class="card-header">留言回覆</h5>
					<div class="card-body">
						<c:forEach varStatus="stVar" var="entry" items="${pcbList}">
							<div class="media mb-4">
								<img
									src="<c:url value='/_00_init/getHeadshot?account=${entry.PCAccount}' />"
									height="64" width="64" class="mr-3 img-corner" alt="..." />
								<div class="media-body">
									<h5 class="mt-0">${entry.PCFounder}</h5>
									${entry.PCContent} <br /> <small class="text-secondary">
										<span class="mr-3"> <i class="far fa-calendar">
												${entry.PCDateTime}</i>
									</span> <!-- 									<i class="far fa-thumbs-up mr-2"> 66</i>  --> <!-- 									<i class="far fa-thumbs-down mr-2"> 0</i> -->
										<!-- 									 <i class="far fa-comment-dots"></i>  -->
									</small>
								</div>
							</div>
						</c:forEach>
						<c:if test="${!empty LoginOK}">
							<div class="media mb-4">
								<img
									src="<c:url value='/_00_init/getHeadshot?account=${Account}' />"
									height="64" width="64" class="mr-3 img-corner" alt="..." />
								<div class="media-body">
									<h5 class="mt-0 text-secondary">${Name}</h5>
									<form
										action="<c:url value='/SaveBlogArtical?PCID=${pcb.PCID}' />"
										method="POST">
										<div class="form-group">
											<textarea class="form-control" id="blogContent"
												name="blogContent" rows="3" placeholder="寫下留言"></textarea>
											<button type="submit"
												class="btn btn-light text-secondary btn-block mt-2">送出</button>
										</div>
									</form>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<!-- ------------------------------------------------------------------- -->
			<div class=" blogRight col-md-4">
				<div class="sticky-top addCol" style="z-index: 0">
					<div class="blogArticalHotArticle mb-5">
						<h3 class="py-3 pl-3 border-bottom">
							<i class="far fa-thumbs-up"></i> 你可能還想看				
						</h3>
						
						<!-- no.1 hotArticle Start -->
						<c:forEach varStatus="stVar" var="entry"
							items="${relatedBlogArtical}">
							<div class="d-flex mx-3 mt-4 myMOUSE"
								onclick="location.href='<c:url value="/BlogArtical?PCID=${entry.PCID}" />';">
								<div class="mr-3">
									<div class="hotImage"
										style="background-image: url(${pageContext.servletContext.contextPath}/GetPCImage?PCID=${entry.PCID});"></div>
								</div>
								<div class="d-flex flex-column justify-content-between">
									<h5 class="mb-0 mt-0 sidebar-clamp">${entry.PCTitle}</h5>
									<div class="mt-1 text-secondary mb-0" style="font-size: 10px">
										<span><i class="far fa-calendar-times mr-2">
												${entry.PCDateTime}</i> </span> <span class="mr-3"><i
											class="far fa-user"> ${entry.PCFounder}</i> </span>
									</div>
								</div>
							</div>
						</c:forEach>
						<!-- no.3 hotArticle End -->
						<div class="justify-content-end m-auto">
						<a href="<c:url value='/BlogIndex'/>" type="button" class="btn btn-primary mt-4" >返回健康專欄清單</a>
						</div>
					</div>
					
				</div>
			</div>

		</div>
	</div>

	<!-- 引入簡易的頁尾 -->
	<jsp:include page="/fragment/footer_brief.jsp" />
	<!-- 	<footer class="footerColor py-4 mt-3"> -->
	<!-- 		<div class="container d-flex flex-row justify-content-between"> -->
	<!-- 			<div class="">Copyright © 2020 FurKids Inc.</div> -->
	<!-- 			<div> -->
	<!-- 				<a href="#"><i class="fab fa-instagram"></i></a> <a href="#"><i class="fab fa-facebook-square"></i></a> <a href="#"><i -->
	<!-- 					class="fab fa-twitter"></i></a> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</footer> -->
	<!-- Optional JavaScript -->
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

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js"
		crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="<c:url value='/resources/javascript/jquery.stellar.js' />"></script>

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->
	
	<script type="text/javascript">
		//fixed position
		$(window).scroll(function() {
			if ($(window).scrollTop() > 364) {
				$('.addCol').addClass('addColToTup');
			} else {
				$('.addCol').removeClass('addColToTup');
			}
		});
	</script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
</body>
</html>