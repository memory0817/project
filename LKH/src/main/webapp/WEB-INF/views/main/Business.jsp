<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../index_top.jsp" flush="false" />

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/xe.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/resources/bussiness/css/animate.min.css" />
<link rel="stylesheet" 
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<!-- JS -->
<script src="/resources/bussiness/js/common.js"></script>

<!-- 페이지 스크롤  시-->
<link rel="stylesheet" type="text/css"
	href="/resources/css/full-page-scroll.css">

<!-- 애니메이션 효과 -->
<style>

@import url("http://fonts.googleapis.com/earlyaccess/notosanskr.css");
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700');



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
<script src="/resources/js/full-page-scroll.js"></script>

<!--  스크롤 끝  -->




<link href="/resources/css/dashboard.css" rel="stylesheet">
<style type="text/css">

.section1 {
   background-image: url(/resources/images/business/sub_tit_img.png); 
   background-size: cover;
}

#px5 .media-body {overflow:visible}

.section1 h2 { 
color:#fff; 
font-size:6.5em; 
font-family:'Roboto Condensed';
} 

.section2 {
background-color: #424242;
}

.section2 h2 {
color:#fff; 
font-size:5em; 
font-family:'Roboto Condensed';
}

.section3 {
   background-color: #d6d6d6;
}




.section3 h2 {
   	color:#000; 
	font-size:5em; 
	font-family:'Roboto Condensed';
}

.section3 span {
 font-size: 57px;
 font-weight:100;
 letter-spacing:-2.85px;
}

.section3 i,.section3 p, .section3 h4 {
color:#000;
}
.section4 {
   background-color: #e8e8e8;
}

.section4 h5 {
   font-size:18px; 
}

.section4 h3 {
   color:#000; 
	font-size:5em; 
	 letter-spacing:-3.85px;
	font-family:'Roboto Condensed';
}

.section4 span {
 font-size: 57px;
 font-weight:100;
 letter-spacing:-4.85px;
 font-family:'Roboto Condensed';
}

.section5 {
   background-color: #000;
}

.infobox .date { 
font-size:16px;font-weight:700;color:#0066b3;
letter-spacing:-0.85px;
}

section div {
   font-style: normal;
   text-align: center;
   position: relative;
   top:;
   transform: translateY();
}

.vision{
	display: inline-block;
	padding : 1%;
	width: 23%;
}
.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
  max-width: 100%;
  height: auto
}




</style>

<div id="main" class="scroll-container recruit">
 
	<section id="px5" class="section1 px_sect text-center ani_delay02 animated fadeIn">
			<div class="block-container">
					<div class="block">
						<h2 class="animated fadeInUp">LKH <b>Business</b></h2>
					</div>
				</div>
				
	

	</section>


	<section id="px2" class="section2 px_sect"> 
		<div class="block-container">
			
					<div class="block">
						<h2 class="animated fadeIn ani_delay05 ani_duration02">LKH <b>Business</b></h2>
					</div>
					<img class="img-responsive" src="/resources/images/business/section2.png" alt="lkh기업설명" />

				</div>	
	</section>


	<section class="section3 px_sect text-center">
		<div class="block-container">
			<div class="block">
				<h2 class="animated fadeIn ani_delay05">
					LKH<span> 회사의 경영관리비법</span><br>
					<b>Unique</b>
				</h2>
			</div>
				<div class="vision animated fadeInUp ani_delay"><i
					class="fas fa-child fa-10x mb-3"></i>
					<h4>최고의 인재</h4>
					<p>
						최고의 인사 담당자들의 철저한 영입에서 시작합니다. <br> LKH의 인재영입이 최고의 회사를
						<br>만들어내고 있습니다.
					</p></div>
				<div class="vision animated fadeInUp ani_delay02"><i
					class="fas fa-laptop fa-10x mb-3"></i>
					<h4>코드관리</h4>
					<p>
						체계적인 코드관리를 통해 <br> 가장효율적인 코딩결과를 <br> 만들어냅니다. <br>
					</p></div>
				<div class="vision animated fadeInUp ani_delay05"><i
					class="fas fa-mobile-alt fa-10x mb-3"></i>
					<h4>안드로이드 앱개발</h4>
					<p>
						고도화된 한국IT사업만의 <br>특색을 살려 실용적인 <br>어플을 개발합니다.
					</p></div>
		</div>
	</section>


	<section id="business" class="section4">
	<div class="block-container">
	<div class="sect-tit v2 text-center">
	<h3 class="color-wh animated fadeIn ani_delay05"><span>최고의 품질로 관리되는</span><br><b>사이트들</b></h3>
	</div>
	<div class="d-flex bx-wrapper justify-content-center pt-5">
	<div class="vision">   
	<img class="img-responsive" src="/resources/images/business/box/box1.jpg" />
		<div class="infobox mt-3">
			<h5><b>CJ홈페이지 관리운영</b></h5>
			<span class="date">2018.07.02</span>
		</div>
	</div>
	<div class="vision">
	<img class="img-responsive" src="/resources/images/business/box/box2.jpg" />
		<div class="infobox mt-3">
			<h5><b>한화홈페이지 관리운영</b></h5>
			<span class="date">2018.09.02</span>
		</div>
	</div>	
	<div class="vision">
	<img class="img-responsive" src="/resources/images/business/box/box3.jpg" /> 
	<div class="infobox mt-3">
			<h5><b>삼성홈페이지 관리운영</b></h5>
			<span class="date">2018.11.02</span>
		</div>
	</div>
	<div class="vision">
	<img class="img-responsive" src="/resources/images/business/box/box4.jpg" /> 
	<div class="infobox mt-3">
			<h5><b>롯데시네마홈페이지 관리운영</b></h5>
			<span class="date">2019.01.02</span>
		</div>
	</div>
    </div>
	
	
	</div>	
	</section>
</div>
<!-- //페이지 끝 -->


<!-- //container -->




<script type="text/javascript">
		new fullScroll({
			displayDots : true,
			dotsPosition : 'left',
			animateTime : 0.7,
			animateFunction : 'ease'
		});
	</script>


</body>
</html>
