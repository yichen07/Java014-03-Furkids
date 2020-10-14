<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
<link rel="icon"
	href="<c:url value='/resources/images/logo_08_iP6_6.ico' />"
	type="image/x-icon" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS --------------------------------------------------------------------->

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" /> -->

<!-- animate.style CSS --------------------------------------------------------------------->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" /> -->
<!-- Input CSS End---------------------------------------------------------------------->

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/blogIndex.css" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>寵物健康專欄</title>
</head>

<body>
	
	<jsp:include page="/fragment/navigation.jsp" />

	<div
		class="container-fliid blogIndexBannerImg d-flex justify-content-center align-items-center"
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

	<!-- Banner End------------------------------------------------------------------------->

	<!-- Main -------------------------------------------------------------------------------------------------------------->

	<!-- LeftContainer Start------------------------------------------------------------------------------------------------>

	<section class="container">
		<!-- right cardView start  -->

		<!-- 新增專欄_開始 -->
		<!-- Modal -->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">

						<h5 class="modal-title" id="exampleModalCenterTitle">新增專欄</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>

					</div>
					<div class="modal-body">
						<form action="<c:url value='/SaveBlogIndex' />" method="POST"
							name="BlogIndexInsert" enctype='multipart/form-data'>
							<div class="form-group">
								<label for="blog-title" class="col-form-label">專欄標題:</label> <input
									type="text" class="form-control" id="blogTitle"
									name="blogTitle">
							</div>
							<div class="form-group">
								<label for="blog-img" class="col-form-label">上傳專欄圖片:</label> <input
									type="file" class="form-control" id="blogImg" name="blogImg">
							</div>
							<div class="form-group">
								<label for="blog-content" class="col-form-label">專欄內容:</label>
								<textarea class="form-control" id="blogContent"
									name="blogContent"></textarea>
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
		<c:choose>
			<c:when test="${LoginOK.CLASSIFY == 0}">
				<c:set var="Account" value="${LoginOK.cusAccount}" />
			</c:when>
			<c:otherwise>
				<c:set var="Account" value="${LoginOK.busAccount}" />
			</c:otherwise>
		</c:choose>
		<!-- 新增專欄_結束 -->
		<div class="row">



			<div class="col-md-8 blog_card" >
				<!-- 新增專欄_按鈕Start -->
				<div class="sticky-top addCol">
<%-- 					<c:if test="${!empty LoginOK}"> --%>
					<c:if test="${LoginOK.CLASSIFY == 1}">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModalCenter">新增專欄</button>
					</c:if>
				</div>
				<!-- 新增專欄_按鈕End -->


				<!-- no.1 article start-->
				<c:forEach varStatus="stVar" var="entry" items="${pcbList}" >
					<div class="bolg_cardBackground my-4"  style="z-index:-1">
						<div class="blogcard_img"
							style="background-image: url(${pageContext.servletContext.contextPath}/GetPCImage?PCID=${entry.PCID});">
						</div>
						<div class="line-clamp">
							<h3 class="px-4 pt-3 font-weight-bold">${entry.PCTitle}</h3>
							<p class="px-4 blogCardbArticle">${entry.PCContent}</p>
						</div>
						<a href="<c:url value='/BlogArtical?PCID=${entry.PCID}' />"
							class="px-4 pt-2 readMoreText">閱讀更多... </a>
						<div
							class="blogCardFooter px-4 pl-5 d-flex justify-content-end align-items-end">
							<span class="mt-2 mr-3"> <c:if
									test="${Account == entry.PCAccount }">
									
									<i class="fas fa-cog myMOUSE blogIndexBlogCardFooterIcon"
										data-toggle="modal" data-target="#exampleModalCenterUpdate">
										修改</i>


									<a
										href="javascript: if(window.confirm('是否刪除此專欄?')){window.location.href='${pageContext.servletContext.contextPath}/deleteBlogArtical?PCID=${entry.PCID}'}"
										style="text-decoration: none;"> <i
										class="fas fa-trash-alt myMOUSE blogIndexBlogCardFooterIcon">
											刪除</i>
									</a>

								</c:if> <i class="far fa-eye blogIndexBlogCardFooterIcon">
									${entry.PCViews}</i> <i
								class="far fa-comment-dots blogIndexBlogCardFooterIcon">
									${PCNumberOfMessages[entry.PCID]}</i>
							</span>
						</div>
					</div>
					<div class="modal fade" id="exampleModalCenterUpdate" tabindex="-1"
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
										action="<c:url value="/UpdatePetColumn?PCID=${entry.PCID}" />"
										method="POST" name="BlogIndexInsert"
										enctype='multipart/form-data'>
										<div class="form-group">
											<label for="blog-title" class="col-form-label">專欄標題:</label>
											<input type="text" class="form-control" id="blogTitle"
												name="blogTitle" value="${entry.PCTitle}">
										</div>
										<div class="form-group">
											<label for="blog-img" class="col-form-label">上傳專欄圖片:</label>
											<input type="file" class="form-control" id="blogImg"
												name="blogImg">
										</div>
										<div class="form-group">
											<label for="blog-content" class="col-form-label">專欄內容:</label>
											<textarea class="form-control" id="blogContent"
												name="blogContent">${entry.PCContent}</textarea>
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
				</c:forEach>
				<!-- no.3 article end-->
			</div>

			<!-- LeftContainer End   ------------------------------------------------------------------------------------------->

			<!-- right Sidebar start  ------------------------------------------------------------------------------------------>
			
			<div class="blogRight col-md-4">
				<div class="sticky-top addCol" style="z-index:0">
				<div class="blogIndexHotArticle mb-5">
					<h3 class="py-3 pl-3 border-bottom">
						<i class="fab fa-hotjar"></i> 熱門文章
					</h3>
					<!-- no.1 hotArticle Start -->
					<c:forEach varStatus="stVar" var="entry" items="${popularPcbList}">
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
				</div>
				
				</div>
				
			</div>
			
		</div>
	</section>

	<!-- 引入簡易的頁尾 -->
	<jsp:include page="/fragment/footer_brief.jsp" />

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

	<!-- JavaScript Plug-in End------------------------------------------------------------->

	<!-- navigation bar js ------------------------------------->
	<jsp:include page="/fragment/navigation_determine.jsp" />
	<!-- navigation bar js End------------------------------------->

	<script src="<c:url value='/resources/javascript/blogIndex.js' />"></script>

	<script type="text/javascript">
		//fixed position
		$(window).scroll(function() {
			if ($(window).scrollTop() > 364) {
				$('.addCol').addClass('addColToTup');
			} else {
				$('.addCol').removeClass('addColToTup');
			}
		});

		$('.modal-open').find('.modal-backdrop').remove();
	</script>
</body>
</html>
