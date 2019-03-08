<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="index_top.jsp" flush="false" />

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/xe.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/animate.min.css" />

<!-- JS -->
<script src="/resources/bussiness/js/common.js"></script>

<!-- 애니메이션 효과 -->
<style>
@keyframes fadeInUp {
  0% {
    opacity: 0;
    -webkit-transform: translate3d(0, 100%, 0);
    transform: translate3d(0, 100%, 0)
  }

  100% {
    opacity: 1;
    -webkit-transform: none;
    transform: none
  }
}

.fadeInUp {
  -webkit-animation-name: fadeInUp;
  animation-name: fadeInUp
}

@keyframes fadeIn {
  0% {
    opacity: 0
  }

  100% {
    opacity: 1
  }
}

.fadeIn {
  -webkit-animation-name: fadeIn;
  animation-name: fadeIn
}
</style>

<style type="text/css">
@import url("http://fonts.googleapis.com/earlyaccess/notosanskr.css");

@import
	url('https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700')
	;

.section1 {
	
}

.section2 {
	background-color: #4f4f4f;
}

.section2 h4 , .h2{
	    line-height: 1.6;
	    color:#fff;
}

.section3 h2 {
font-size:1em; 
font-family:'Roboto Condensed';
 letter-spacing:-3.85px;
}

.section3 {
	background-color: write;
}

.section3 p {
   font-size:18px; 
}

.section4 {
	background-color: #CC938E;
}

.section5 {
	background-color: #D2C598;
}

section div {
 	font-family: 'Noto Sans KR'; 
	font-style: normal;
	text-align: center;
	position: relative;
	top:;
	transform: translateY();
}

span {
	font-size: 4em;
	font-style: normal;
	color: #fff;
}

.vision {
	display: inline-block;
	padding: 1%;
}

.vision>img {
	width: 500px;
}
</style>
<!--  백그라운드 영상 시작 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="/resources/js/vidbg.js"></script>
<!-- 백그라운드 영상 끝 -->
<!-- 카카오톡 api -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=LIBRARY"></script>
<!-- services 라이브러리 불러오기 -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services"></script>
<!-- services와 clusterer, drawing 라이브러리 불러오기  -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script>
	$(function() {

		$("#viewmap").on("click", function() {
			window.open("http://map.daum.net/link/map/LKH Co!,35.5350544, 129.3108984");

		});


		$("#find").on("click", function() {
			window.open("http://map.daum.net/link/to/LKH Co!,35.5350544, 129.3108984");

		});

	});
</script>
<script type="text/javascript">
	jQuery(function($) {
		$('.section1').vidbg({
			'mp4': '/resources/images/video1.mp4',
			/*  'webm': '/resources/images/webm_video.webm',*/
			'poster': '/resources/images/intro.png'
		}, {
			// Options
			muted: true,
			loop: true,
			overlay: false,
		});
	});
</script>


<div id="main" class="scroll-container company">
	<section id="px5" class="section1 px_sect text-center ani_delay02 animated fadeIn">

		<div class="block-container">
			<div class="block">
				<h3 class="animated fadeInUp">
					<img src="/resources/images/whitelogo.png" alt="" width="300" />
				</h3>
			</div>
		</div>
	</section>

	<section class="section2">
		<div class="block-container">
			<div class="h2" class="display-5" style="padding-top: 50px">어제보다
				더 나은 세상을 꿈꾸는 LKH, 저희는 새로운 웹 시장을 선도할 것입니다.</div>
			<div class="contianer">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators" style="top: 580px">
						<li data-target="#carouselExampleIndicators" data-slide-to="0"
							class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="" style="padding-top: 10px;">
						<div class="carousel-item ">
							<img
								src="https://t1.kakaocdn.net/kakaocorp/pw/kakao/kakao_ai01.jpg"
								class=" " alt="..." width="1500" height="600">
						</div>
						<div class="carousel-item active">
							<img
								src="http://t1.kakaocdn.net/kakaocorp/pw/kakao/intro_culture01.jpg"
								class="" alt="..." width="1500" height="600">
						</div>
						<div class="carousel-item ">
							<img
								src="https://t1.kakaocdn.net/kakaocorp/pw/kakao/introduce_vision01.jpg"
								class=" " alt="..." width="1500" height="600">
						</div>
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev" style="top: 300px"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next" style="top: 300px"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>
				<div style="padding-top: 600px">
					<div class="area_cont">
						<h4 class="desc_info pt-4">
							아무도 가보지 않은 길을 함께 가는 사람들, LKH는 웹 개발을 새로운 시각과 방식으로 해결하고 싶고,<br />
							나에게 충분한 권한과 책임을 받아서 신나게 일하고 싶은 기업. LKH의 핵심가치입니다.
						</h4>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="section3">
		<div class="block-container">
			<div>
				<div class="display-3" style="padding-top: 100px"><h2>LKH <b>Misson &
					Vision</b></h2></div>
				<div class="vision">
					<img src="/resources/images/main_s_3_1.jpg" />
					<p class="mt-3 pt3">
						<b>명확한 권한과 책임을 가지고<br>일에 몰입합니다.</b>
					</p>
				</div>
				<div class="vision">
					<img src="/resources/images/LKHimg2.jpg" />
					<p class="mt-3 pt3">
						<b>빠른 실행을 위해, 정보 및 의사결정<br>과정을 공개 공유합니다.</b>
					</p>
				</div>
				<div class="vision">
					<img src="/resources/images/LKHimg1.jpg" />
					<p class="mt-3 pt3">
						<b>최선의 의사결정을 위해<br>무엇이든 솔직하게 의견을 냅니다.</b> 
					</p>
				</div>

			</div>

		</div>
</div>
</section>


<script type="text/javascript">
			new fullScroll({
				displayDots: true,
				dotsPosition: 'left',
				animateTime: 0.7,
				animateFunction: 'ease'
			});
		</script>
</div>
<script src="/resources/js/jquery-2.1.1.js"></script>
<script src="/resources/js/main.js"></script>
<!-- Resource jQuery -->


</body>