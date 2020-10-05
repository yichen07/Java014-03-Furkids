<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>專欄內文</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/blogArtical.css" />
</head>
<body>
	<jsp:include page="/fragment/navigation.jsp" />
	<div class="container-fliid blogArticalBannerImg d-flex justify-content-center align-items-center" id="imageStellar"
		data-stellar-background-ratio="0.5">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">寵物專欄</h1>
				<div class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">世界是平等的,請愛護動物沒有愛就沒有完美</h5>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<!-- ------------------------------------------------------------------- -->
		<h1 class="mt-5">${pcb.PCTitle}</h1>
		<div class="row">
			<div class="col-md-8">
				<!-- ------------------------------------------------------------------- -->
				<article>
					<div class="text-secondary mb-3">
						<small> <span class="mr-3"><i class="far fa-user"> ${pcb.PCFounder}</i> </span>
						 <span class="mr-3"> <i class="far fa-calendar"> ${pcb.PCDateTime}</i> </span> 
<!-- 								<span><i class="fas fa-tags"> 狗, 音樂</i> </span> -->
						</small>
					</div>

					<!-- ------------------------------------------------------------------- -->
					<p>${pcb.PCContent}</p>
					<!-- 					<p>如果狗狗會聽音樂，牠們會想要聽什麼樣的音樂呢？科學家發現，狗狗對輕搖滾和雷鬼音樂最有感覺</p> -->

					<!-- 					<p>英國蘇格蘭格拉斯哥大學的生理學家Neil Evans等人發現，就像人有音樂偏好，我們最好的朋友也是。他們播放了輕搖滾、摩城音樂、流行音樂、雷鬼音樂和古典樂，然後記錄狗狗的心率變異性、皮質醇水平和吠叫或躺下等行為，以測量壓力水平。</p> -->

					<!-- 					<p> -->
					<!-- 						在人類的研究中顯示，音樂的放鬆效果與節奏或音樂中的重複主題有關，可能對狗狗來說，輕搖滾和雷鬼音樂那樣的節奏和重複主題剛好討喜。而其他音樂的反應則不一。研究人員早在2015年就發現古典樂能讓狗狗放鬆，聽了古典樂的狗狗相較沒聽音樂的狗狗，壓力水平顯然下降。而新研究顯示，輕搖滾和雷鬼音樂比起古典音樂，更能夠激發牠們的正向行為變化。 -->
					<!-- 					</p> -->
					<!-- 					<p>他們是在蘇格蘭防止虐待動物協會（SPCA）進行該研究，觀察狗狗聆聽不同音樂的生理和行為變化。他們在格拉斯哥和愛丁堡的防止虐待動物協會都已播放音樂給狗狗聽，他們未來希望擴大研究至其他動物。</p> -->
					<!-- ------------------------------------------------------------------- -->
					<img class="img-fluid" src="${pageContext.request.contextPath}/_04_Community/getPCImage?PCID=${pcb.PCID}" />
					<p class="mt-3">
						原學術論文： <br /> Bowman A; Scottish SPCA, Dowell FJ, Evans NP. 'The effect of different genres of music on the stress levels
						of kennelled dogs’. Physiol Behav. 2017 Mar 15;171:207-215. doi: 10.1016/j.physbeh.2017.01.024. Epub 2017 Jan 14.
					</p>
					<p class="mb-0">參考資料：</p>
					<div class="mb-5">
						<ol>
							<li>Sarah Knapton. Dogs are happier listening to soft rock and reggae, study finds. Telegraph.co.uk.</li>
							<li>Karin Brulliard. Music helps dogs chill out, especially if it's reggae or soft rock. Washington Post.</li>
						</ol>
					</div>
				</article>

				<div class="card mb-5">
					<h5 class="card-header">留言回覆</h5>
					<div class="card-body">
						<c:forEach varStatus="stVar" var="entry" items="${pcbList}">
							<div class="media mb-4">
								<img src="<c:url value='/_00_init/getHeadshot?account=${entry.PCAccount}' />" height="64" width="64"
									class="mr-3 img-corner" alt="..." />
								<div class="media-body">
									<h5 class="mt-0">${entry.PCFounder}</h5>
									${entry.PCContent} <br /> 
									<small class="text-secondary">
									<span class="mr-3"> <i class="far fa-calendar"> ${entry.PCDateTime}</i> </span> 
<!-- 									<i class="far fa-thumbs-up mr-2"> 66</i>  -->
<!-- 									<i class="far fa-thumbs-down mr-2"> 0</i> -->
<!-- 									 <i class="far fa-comment-dots"></i>  -->
									 </small>
								</div>
							</div>
						</c:forEach>
						<div class="media mb-4">
							<c:choose>
								<c:when test="${LoginOK.CLASSIFY == 0 }">
									<img src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.cusAccount}' />" height="64" width="64"
										class="mr-3 img-corner" alt="..." />
								</c:when>
								<c:otherwise>
									<img src="<c:url value='/_00_init/getHeadshot?account=${LoginOK.busAccount}' />" height="64" width="64"
										class="mr-3 img-corner" alt="..." />
								</c:otherwise>
							</c:choose>
							<div class="media-body">
								<c:choose>
									<c:when test="${LoginOK.CLASSIFY == 0 }">
										<h5 class="mt-0 text-secondary">${ LoginOK.cusName }</h5>
									</c:when>
									<c:otherwise>
										<h5 class="mt-0 text-secondary">${ LoginOK.busName }</h5>
									</c:otherwise>
								</c:choose>
								<form action="<c:url value='/_04_Community/SaveBlogArtical' />" method="POST">
									<div class="form-group">
										<textarea class="form-control" id="blogContent" name="blogContent" rows="3" placeholder="寫下留言"></textarea>
										<button type="submit" class="btn btn-light text-secondary btn-block mt-2">Submit form</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- ------------------------------------------------------------------- -->
			<div class="col-md-4 blogRight mt-3">
				<div class="hotArticle mb-5">
					<h3 class="py-3 pl-3 border-bottom">
						<i class="far fa-thumbs-up"></i> 你可能還想看
					</h3>
					<!-- no.1 hotArticle Start -->
					<div class="d-flex mx-3 mt-4">
						<div class="mr-3">
							<div class="hotImage"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_hotArticle_01.jpg);"></div>
						</div>
						<div class="d-flex flex-column justify-content-between">
							<h5 class="mb-0 mt-0 sidebar-clamp">狗狗愛的眼神為何讓人欲罷不能？</h5>
							<div class="mt-1 text-secondary mb-0" style="font-size: 10px">
								<span><i class="far fa-calendar-times mr-2"> 2020/09/11</i> </span> <span class="mr-3"><i class="far fa-user">
										黃貞祥</i> </span>
							</div>
						</div>
					</div>
					<!-- no.1 hotArticle End -->
					<!-- no.2 hotArticle Start -->
					<div class="d-flex mx-3 mt-4">
						<div class="mr-3">
							<div class="hotImage"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_hotArticle_02.jpg);"></div>
						</div>
						<div class="d-flex flex-column justify-content-between">
							<h5 class="mb-0 mt-0 sidebar-clamp">狗狗真的聽得懂人話</h5>
							<div class="mt-1 text-secondary" style="max-width: 150px; font-size: 10px">
								<span><i class="far fa-calendar-times mr-2"> 2014/12/25</i> </span> <span class="mr-3"><i class="far fa-user">
										黃貞祥</i> </span>
							</div>
						</div>
					</div>
					<!-- no.2 hotArticle End -->
					<!-- no.3 hotArticle Start -->
					<div class="d-flex mx-3 mt-4 mb-5">
						<div class="mr-3">
							<div class="hotImage"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_hotArticle_03.jpg);"></div>
						</div>
						<div class="d-flex flex-column justify-content-between">
							<h5 class="mb-0 mt-0 sidebar-clamp">帶來滿滿正面能量的可愛貓咪影片？</h5>
							<div class="mt-1 text-secondary" style="max-width: 150px; font-size: 10px">
								<span><i class="far fa-calendar-times mr-2"> 2015/08/21</i> </span> <span class="mr-3"><i class="far fa-user">
										黃貞祥</i> </span>
							</div>
						</div>
					</div>
					<!-- no.3 hotArticle End -->
				</div>
			</div>
		</div>
	</div>

	<footer class="footerColor py-4 mt-3">
		<div class="container d-flex flex-row justify-content-between">
			<div class="">Copyright © 2020 FurKids Inc.</div>
			<div>
				<a href="#"><i class="fab fa-instagram"></i></a> <a href="#"><i class="fab fa-facebook-square"></i></a> <a href="#"><i
					class="fab fa-twitter"></i></a>
			</div>
		</div>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS ----------------------------------->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
	<!-- jQuery first, then Popper.js, then Bootstrap JS End-------------------------------->

	<!-- JavaScript Plug-in ---------------------------------------------------------------->

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js" crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="/src/main/webapp/resources/javascript/jquery.stellar.js"></script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->
</body>
</html>