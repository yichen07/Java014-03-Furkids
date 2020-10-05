<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS --------------------------------------------------------------------->

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" /> -->

<!-- animate.style CSS --------------------------------------------------------------------->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" /> -->
<!-- Input CSS End---------------------------------------------------------------------->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/blogIndex.css" />

<!-- Input CSS End---------------------------------------------------------------------->

<title>寵物專欄</title>
</head>

<body>
	<jsp:include page="/fragment/navigation.jsp" />
	<div class="container-fliid blogIndexBannerImg d-flex justify-content-center align-items-center" id="imageStellar"
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

	<!-- Banner End------------------------------------------------------------------------->

	<!-- Main -------------------------------------------------------------------------------------------------------------->

	<!-- LeftContainer Start------------------------------------------------------------------------------------------------>

	<section class="container">
		<!-- right cardView start  -->

		<!-- 新增專欄_開始 -->
		<button type="button" class="btn btn-primary" style="margin-top: 10px" data-toggle="modal" data-target="#exampleModalCenter">新增專欄</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
			aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalCenterTitle">新增專欄</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="<c:url value='/_04_Community/SaveBlogIndex' />" method="POST" name="BlogIndexInsert" enctype='multipart/form-data'>
							<div class="form-group">
								<label for="blog-title" class="col-form-label">專欄標題:</label> <input type="text" class="form-control" id="blogTitle"
									name="blogTitle">
							</div>
							<div class="form-group">
								<label for="blog-img" class="col-form-label">上傳專欄圖片:</label> <input type="file" class="form-control" id="blogImg"
									name="blogImg">
							</div>
							<div class="form-group">
								<label for="blog-content" class="col-form-label">專欄內容:</label>
								<textarea class="form-control" id="blogContent" name="blogContent"></textarea>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">刊登</button>
								<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-- 新增專欄_結束 -->
		<div class="row">
			<div class="col-md-8 blog_card">
				<!-- no.1 article start-->
				<c:forEach varStatus="stVar" var="entry" items="${pcbList}">
					<div class="bolg_cardBackground my-4">
						<div class="blogcard_img"
							style="background-image: url(${pageContext.servletContext.contextPath}/_04_Community/getPCImage?PCID=${entry.PCID});">
						</div>
						<div class="line-clamp">
							<h3 class="px-4 pt-3 font-weight-bold">${entry.PCTitle}</h3>
							<p class="px-4 blogCardbArticle">${entry.PCContent}</p>
						</div>
						<a href="<c:url value='/_04_Community/BlogArtical?PCID=${entry.PCID}' />" class="px-4 pt-2 readMoreText">閱讀更多... </a>
						<div class="blogCardFooter px-4 pl-5 d-flex justify-content-end align-items-end">
							<span class="mt-2 mr-3"> 
								<i class="far fa-eye blogIndexBlogCardFooterIcon"> ${entry.PCViews}</i>
								<i class="far fa-comment-dots blogIndexBlogCardFooterIcon"> ${PCNumberOfMessages[entry.PCID]}</i>
								<c:choose>
									<c:when test="${LoginOK.CLASSIFY == 0 }">
										<c:set var="memberAccount" value="${ LoginOK.cusAccount }" />
									</c:when>
									<c:otherwise>
										<c:set var="memberAccount" value="${ LoginOK.busAccount }" />
									</c:otherwise>
								</c:choose> 
								<c:if test="${memberAccount == entry.PCAccount }">
									<i class="fas fa-cog blogIndexBlogCardFooterIcon"></i> 
								</c:if>
							</span>
						</div>
					</div>
				</c:forEach>
				<!-- 				<div class="bolg_cardBackground my-4"> -->
				<!-- 					<div class="blogcard_img" -->
				<%-- 						style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_articlePhoto_01.jpg);"></div> --%>
				<!-- 					<div class="line-clamp"> -->
				<!-- 						<h3 class="px-4 pt-3 font-weight-bold">狗狗愛聽的音樂</h3> -->

				<!-- 						<p class="px-4 blogCardbArticle">如果狗狗會聽音樂，牠們會想要聽什麼樣的音樂呢？科學家發現，狗狗對輕搖滾和雷鬼音樂最有感覺。英國蘇格蘭格拉斯哥大學的生理學家Neil -->
				<!-- 							Evans等人發現，就像人有音樂偏好，我們最好的朋友也是。他們播放了輕搖滾、摩城音樂、流行音樂、雷鬼音樂和古典樂，然後記錄狗狗的心率變異性、皮質醇水平和吠叫或躺下等行為，以測量壓力水平。在人類的研究中顯示，音樂的放鬆效果與節奏或音樂中的重複主題有關，可能對狗狗來說，輕搖滾和雷鬼音樂那樣的節奏和重複主題剛好討喜。而其他音樂的反應則不一。研究人員早在2015年就發現古典樂能讓狗狗放鬆，聽了古典樂的狗狗相較沒聽音樂的狗狗 -->
				<!-- 							壓力水平顯然下降。而新研究顯示，輕搖滾和雷鬼音樂比起古典音樂，更能夠激發牠們的正向行為變化。他們是在蘇格蘭防止虐待動物協會（SPCA）進行該研究，觀察狗狗聆聽不同音樂的生理和行為變化。他們在格拉斯哥和愛丁堡的防止虐待動物協會都已播放音樂給狗狗聽，他們未來希望擴大研究至其他動物。 -->
				<!-- 						</p> -->
				<!-- 					</div> -->
				<!-- 					<a href="/src/main/webapp/blogArtical_1.html" class="px-4 pt-2 readMoreText">閱讀更多... </a> -->
				<!-- 					<div class="blogCardFooter px-4 pl-5 d-flex justify-content-end align-items-end"> -->
				<!-- 						<span class="mt-2 mr-3"><i class="fas fa-share-square blogIndexBlogCardFooterIcon"> 6843</i> </span> <span class="mt-2 mr-3"> -->
				<!-- 							<i class="far fa-eye blogIndexBlogCardFooterIcon"> 946</i> -->
				<!-- 						</span> <span class="mt-2 mr-3"> <i class="far fa-kiss-wink-heart blogIndexBlogCardFooterIcon"> 5768</i> -->
				<!-- 						</span> -->
				<!-- 					</div> -->
				<!-- 				</div> -->

				<!-- 				no.1 article end -->

				<!-- 				no.2 article start -->

				<!-- 				<div class="bolg_cardBackground my-4"> -->
				<!-- 					<div class="blogcard_img" -->
				<%-- 						style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_articlePhoto_02.jpg);"></div> --%>
				<!-- 					<div class="line-clamp"> -->
				<!-- 						<h3 class="px-4 pt-3 font-weight-bold">【寵物短消息】黑白花斑貓的遺傳之謎</h3> -->

				<!-- 						<p class="px-4 blogCardbArticle"> -->
				<!-- 							為何黑白貓身上會有兩種毛色呢？科學家發現，這樣的花斑是動物在子宮內發育時，色素細胞的分裂變慢所造成的。黑白花斑在一些馬身上也可看見。正常來說，色素細胞該從背部擴散到全身包括肚子。在花斑的哺乳動物身上，色素細胞不足以覆蓋全身，所以留下大片白色的毛髮，一般上肚子和額頭會是白色的。英國牛津大學、巴斯大學、愛丁堡大學的科學家，利用了數學模型結合動物實驗來解釋花斑的形成。過去認為那些黑白的花斑是因為色素細胞的移動太慢了，是因為一種稱作KIT的基因突變所導致的。KIT基因編碼了受體酪氨酸激酶（Receptor -->
				<!-- 							Tyrosine Kinase），是一種膜蛋白受體，膜外部分功能為受體，膜內部分為酪氨酸激酶 -->
				<!-- 							，可將目標蛋白的酪胺酸（Tyr）部分磷酸化，藉此傳遞訊號，在細胞生長、增殖、分化中具有重要作用。可是他們發現，原來KIT的突變減緩的是細胞分裂速率，而非移動速率。因為細胞分裂變慢，最終沒有足夠的色素細胞覆蓋全身，所以形成黑白花斑。這個發現發表在《自然通訊》（Nature -->
				<!-- 							Communications）。除了KIT基因，也有其他基因影響花斑的紋路，都可利用數學模型來研究。雖然花斑不會造成疾病，可是更嚴重的突變會造成腸道神經病變（Neurocristopathy）而導致聽力、視覺、消化道和心血管的病變，是神經脊細胞分裂的異常造成的。所以他們的研究方法也可能能夠應用在其他相關疾病的研究上。 -->
				<!-- 						</p> -->
				<!-- 					</div> -->
				<!-- 					<a href="" class="px-4 pt-2 readMoreText">閱讀更多... </a> -->
				<!-- 					<div class="blogCardFooter px-4 pl-5 d-flex justify-content-end align-items-end"> -->
				<!-- 						<span class="mt-2 mr-3"><i class="fas fa-share-square blogIndexBlogCardFooterIcon"> 968767</i> </span> <span -->
				<!-- 							class="mt-2 mr-3"> <i class="far fa-eye blogIndexBlogCardFooterIcon"> 6980</i> -->
				<!-- 						</span> <span class="mt-2 mr-3"> <i class="far fa-kiss-wink-heart blogIndexBlogCardFooterIcon"> 2458</i> -->
				<!-- 						</span> -->
				<!-- 					</div> -->
				<!-- 				</div> -->

				<!-- 				no.2 article end -->
				<!-- 				no.3 article start -->

				<!-- 				<div class="bolg_cardBackground my-4"> -->
				<!-- 					<div class="blogcard_img" -->
				<%-- 						style="background-image: url(${pageContext.request.contextPath}/resources/images/blogIndex/blogIndex_articlePhoto_03.jpg);"></div> --%>
				<!-- 					<div class="line-clamp"> -->
				<!-- 						<h3 class="px-4 pt-3 font-weight-bold">【寵物專欄】貓咪為何特別挑嘴？</h3> -->

				<!-- 						<p class="px-4 blogCardbArticle"> -->
				<!-- 							貓咪是有名的挑嘴，有許多東西根本無法強迫牠們食用，有些我們喜愛的食物，貓一聞到卻會皺眉頭。科學家發現，原來貓咪對一些苦味分子的感知，可能和人類非常不同。能嘗出苦味，有可能是為了避免植物裡的有毒化合物。貓科動物，從猛虎到家貓都是肉食動物，甚少食用植物。可是牠們還是能嘗出苦味，只不過和人類的反應很不相同。過去的研究瞭解到貓咪無法嘗到甜味，可是對家貓的其他味覺所知甚少美國Integral -->
				<!-- 							Molecular Inc.的Joseph Rucker和AFB International的Nancy -->
				<!-- 							Rawson等人利用細胞實驗，探討了貓的苦味受器對苦味分子的反應。和人類的同源受器相比，貓的苦味受器Tas2r38則對苦味分子苯硫脲(Phenylthiocarbamide，PTC)的敏感度少了十倍，而且對另一個苦味分子丙硫氧嘧啶(6-n-Propylthiouracil，PROP)沒有反應。TAS2R38在人類的一些變異，會讓一些人對苦味異常敏感，使得他們通常對椰花菜和甘藍敬謝不敏。可是和人類的受器一樣，貓的Tas2r43也會被蘆薈素(Aloin)和苯甲地那銨(denatonium)活化，後者常用來阻止兒童或寵物吞食一些化學藥劑如抗凍劑等，只不過它對前者較不敏感但對後者更敏感；另外，Tas2r43對會留下苦味餘韻的人工甜味劑糖精(saccharin)不敏感。這個研究將有助寵物食品業者改進他們的貓味食品，並且有可能用來設法讓貓咪對一些藥物不再難以接受。 -->
				<!-- 						</p> -->
				<!-- 					</div> -->

				<!-- 					<a href="" class="px-4 pt-2 readMoreText">閱讀更多... </a> -->
				<!-- 					<div class="blogCardFooter px-4 pl-5 d-flex justify-content-end align-items-end"> -->
				<!-- 						<span class="mt-2 mr-3"><i class="fas fa-share-square blogIndexBlogCardFooterIcon"> 5575</i> </span> <span class="mt-2 mr-3"> -->
				<!-- 							<i class="far fa-eye blogIndexBlogCardFooterIcon"> 3547</i> -->
				<!-- 						</span> <span class="mt-2 mr-3"> <i class="far fa-kiss-wink-heart blogIndexBlogCardFooterIcon"> 6988</i> -->
				<!-- 						</span> -->
				<!-- 					</div> -->
				<!-- 				</div> -->

				<!-- no.3 article end-->
			</div>

			<!-- LeftContainer End   ------------------------------------------------------------------------------------------->

			<!-- right Sidebar start  ------------------------------------------------------------------------------------------>

			<div class="col-md-4 blogRight mt-4">
				<div class="hotArticle mb-5">
					<h3 class="py-3 pl-3 border-bottom">
						<i class="fab fa-hotjar"></i> 熱門文章
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

	<!-- icon -->
	<script src="https://kit.fontawesome.com/8e822d04fb.js" crossorigin="anonymous"></script>

	<!-- banner effect -->
	<script src="../js/jquery.stellar.js"></script>

	<!-- JavaScript Plug-in End------------------------------------------------------------->

	<script src="/src/main/webapp/resources/javascript/blogIndex.js"></script>
</body>
</html>
