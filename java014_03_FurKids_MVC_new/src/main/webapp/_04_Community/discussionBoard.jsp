<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS --------------------------------------------------------------------->

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />

<!-- animate.style CSS ------------------------------------------------------------------- -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
<!-- Input CSS End-------------------------------------------------------------------- -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/discussionBoard.css" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>寵物討論板</title>
</head>

<body>
	<jsp:include page="/fragment/navigation.jsp" />
	<div class="container-fliid discussionBoardBannerImg d-flex justify-content-center align-items-center" id="imageStellar"
		data-stellar-background-ratio="0.5">
		<div class="row">
			<div class="col-md-12 text-center">
				<h1 class="mt-3 bannerTitle animate__animated animate__fadeInDown">寵物討論板</h1>
				<div class="align-items-center banner-text animate__animated animate__fadeInUp">
					<h5 class="text-center">請偶而對我說說話, 縱使我不懂你說的內容, 但我聽的懂, 那是你的聲音在陪伴我</h5>
				</div>
			</div>
		</div>
	</div>

	<!-- Banner End------------------------------------------------------------------------->

	

	
	<!-- Main --------------------------------------------------------------------------->
	<section class="contain-fluid main-content">
		<!-- Sidebar --------------------------------------------------------------------------->
		<div class="row">
			<div class="col-md-2 sidebar-main">
				<div class="sidebar border-right">
					<div class="sidebar-menu">
						<div class="profile text-center">
							<img src="https://randomuser.me/api/portraits/women/60.jpg" class="userImg mt-5" />
							<p class="user-name my-2 text-secondary">Adora2</p>
						</div>
						<div>
							<ul class="nav navbar-nav ml-5">
								<li class="my-2"><a href="#"><i class="fas fa-clipboard-list sidebar-icon"><span
											class="ml-2">寵物專欄</span></i></a></li>
								<li class="my-2"><a href="#"><i class="fas fa-clipboard-list sidebar-icon"><span
											class="ml-2">熱門看板</span></i></a></li>

								<li class="my-2"><a href="#"><i class="fas fa-clipboard-list sidebar-icon"><span
											class="ml-2">推薦看板</span></i></a></li>

								<!-- Dropdown-->
								<li class="panel panel-default my-2" id="dropdown"><a data-toggle="collapse" href="#dropdown-lvl1"> <i
										class="fas fa-clipboard-list sidebar-icon"><span class="ml-2">所有看板</span></i>
								</a> <!-- Dropdown level 1 -->
									<div id="dropdown-lvl1" class="panel-collapse collapse">
										<div class="panel-body">
											<ul class="nav navbar-nav mt-1">
												<li><a href="#">寵物交流</a></li>
												<li><a href="#">曬狗</a></li>
												<li><a href="#">曬貓</a></li>
											</ul>
										</div>
									</div>
								</li>
								<li><a href="#"><span class="glyphicon glyphicon-signal"></span></a></li>
								
								<!-- 新增留言_開始 -->
								<li><button type="button" class="btn btn-primary" style="margin-top: 10px" data-toggle="modal" data-target="#exampleModalCenter">新增留言</button></li>
								
								<!-- Modal -->
								<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
									aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalCenterTitle">新增留言</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form enctype="multipart/form-data">
													<div class="form-group">
														<label for="msg-title" class="col-form-label">留言標題:</label> 
														<input type="text" class="form-control" id="msg-title">
													</div>
													<div class="form-group">
														<label for="msg-content" class="col-form-label">留言內容:</label>
														<textarea class="form-control" id="msg-content"></textarea>
													</div>
												</form>
											</div>
											<div class="modal-footer">
												<button type="submit" class="btn btn-primary">刊登</button>
												<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
											</div>
										</div>
									</div>
								</div>
								<!-- 新增留言_結束 -->	
							</ul>
						</div>
					</div>
				</div>
				<div class="msb" id="msb">
					<nav class="navbar navbar-default" role="navigation">
						<!-- Brand and toggle get grouped for better mobile display -->
						<!-- Main Menu -->
						<div class="side-menu-container"></div>
						<!-- /.navbar-collapse -->
					</nav>
				</div>
				<!-- 可以刪的 -->

				<!-- 可以刪的 -->
			</div>
		</div>
		<!-- Sidebar End------------------------------------------------------------------------>
		<!-- Main -->
		<div class="container">
			<div class="row">
				<div class="col-10 justify-content-start">
					<!-- one-message-block-start -->
					<ul class="timeline">
						<li class="timeline-inverted">
							<div class="timeline-badge warning"
								style="background-image: url(https://randomuser.me/api/portraits/women/32.jpg) !important;">
								<div class="photo-sticker"></div>
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4 class="timeline-title d-flex justify-content-between font-weight-bold">
										大型犬照超音波+青光眼檢查診所新竹求推薦
										<div class="replyIcon">
											<span class="ml-0"><i class="fas fa-heart"></i></span> <span class="mr-0"><i
												class="fas fa-share-alt"></i></span>
										</div>
									</h4>
								</div>
								<div class="timeline-body">
									<div class="media-body">
										<div class="my-2 text-secondary">
											<small> <span class="mr-3"><i class="far fa-user"></i> Mary</span> <span
												class="mr-3">2020/08/13</span>
											</small>
										</div>
										<p class="mb-4 message-font">請問新竹地區有對大型犬和善一點的 專門照超音波的動物醫院嗎? 之前有被態度不好過，我玻璃心會受傷啊 我們家是黃金獵犬很乖的啊!</p>
										<!-- messag-1 start-->
										<div class="media my-4">
											<a class="pr-3" href="#">
												<div class="messageIcon" style="background-image: url(https://randomuser.me/api/portraits/women/44.jpg) !important;"></div>
											</a>
											<div class="media-body">
												<div class="mt-0 text-secondary">
													<small> <span class="mr-3"><i class="far fa-user"></i> Catherine</span> <span
														class="mr-3">2020/08/13</span>
													</small>
												</div>
												<p class="message-font">竹北的里仁跟佳恩都蠻不錯的，服務跟態度都蠻好的 超音波檢查在里仁有做過，其他的都是一般常規檢查 可以先打電話問問看 竹北還有一家品澤動物醫院，先前有協助救援一隻類拉拉，他們也很幫忙
												</p>
											</div>
										</div>
									</div>
									<!-- reply -->
									<div class="media-body">
										<h5 class="mt-4 mb-0 text-secondary">
											<i class="ml-1 far fa-comment-dots"></i> 回覆留言
										</h5>
										<div class="form-group">
											<textarea class="form-control mt-1" rows="3" placeholder="寫下你的留言"></textarea>
											<button class="btn btn-light btn-block mt-2">送出留言</button>
										</div>
									</div>
									<!-- messag-1 end -->
								</div>
							</div>
						</li>
					</ul>
					<!-- one-message-block-end -->
					<!-- two-message-block-start -->
					<ul class="timeline">
						<li class="timeline-inverted">
							<div class="timeline-badge warning"
								style="background-image: url(https://randomuser.me/api/portraits/women/63.jpg) !important;">
								<div class="photo-sticker"></div>
							</div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4 class="timeline-title d-flex justify-content-between font-weight-bold">
										請問小貓能喝自來水嗎?
										<div class="replyIcon">
											<span class="ml-0"><i class="fas fa-heart"></i></span> <span class="mr-0"><i
												class="fas fas fa-share-alt"></i></span>
										</div>
									</h4>
								</div>
								<div class="timeline-body">
									<div class="media-body">
										<div class="my-2 text-secondary">
											<small> <span class="mr-3"><i class="far fa-user"></i> Leila</span> <span
												class="mr-3">2020/08/13</span>
											</small>
										</div>
										<p class="mb-4 message-font">上網看過點資料 請指教 謝謝</p>
										<!-- messag-1 start-->
										<div class="media my-4">
											<a class="pr-3" href="#">
												<div class="messageIcon" style="background-image: url(https://randomuser.me/api/portraits/women/65.jpg) !important;"></div>
											</a>
											<div class="media-body">
												<div class="mt-0 text-secondary">
													<small> <span class="mr-3"><i class="far fa-user"></i> Janet</span> <span
														class="mr-3">2020/08/13</span>
													</small>
												</div>
												<p class="message-font">買個濾水壺用來過濾自來水就好了 我都這樣餵家裡兩位主子的 但我水不會放太多 會勤換 平常家中飲用水也都是用濾水壺過濾後燒開的 所以沒什麼影響</p>
											</div>
										</div>
										<div class="media my-4">
											<a class="pr-3" href="#">
												<div class="messageIcon" style="background-image: url(https://randomuser.me/api/portraits/women/57.jpg) !important;"></div>
											</a>
											<div class="media-body">
												<div class="mt-0 text-secondary">
													<small> <span class="mr-3"><i class="far fa-user"></i> Mignon</span> <span
														class="mr-3">2020/08/13</span>
													</small>
												</div>
												<p class="message-font">我家主子不喝煮過的水。以前直接喝自來水，自從用了濾水壺就都過濾過的水。 他的水是放一整缸小魚缸加過濾幫浦讓水有流動，沒有流動的水他也不喝... 超好命der..</p>
											</div>
										</div>
									</div>

									<!-- reply -->
									<div class="media-body">
										<h5 class="mt-4 mb-0 text-secondary">
											<i class="ml-1 far fa-comment-dots"></i> 回覆留言
										</h5>
										<div class="form-group">
											<textarea class="form-control mt-1" rows="3" placeholder="寫下你的留言"></textarea>
											<button class="btn btn-light btn-block mt-2">送出留言</button>
										</div>
									</div>
									<!-- one-message-block-end -->
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- second-message -->
	</section>

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
	<script src="https://kit.fontawesome.com/8e822d04fb.js" crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="/src/main/webapp/resources/javascript/jquery.stellar.js"></script>
	<!-- JavaScript Plug-in End------------------------------------------------------------->

	<script src="/src/main/webapp/resources/javascript/discussionBoard.js"></script>
</body>
</html>
