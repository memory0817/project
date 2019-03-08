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

<!-- 애니메이션 효과 -->
<style type="text/css">

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



.section1 {
	background-image: url("/resources/images/re_main.jpg");
}

.section1 h2 { 
color:#fff; 
font-size:6.5em; 
font-family:'Roboto Condensed';
} 

.section2 h2 { 
font-weight: 500;
letter-spacing:-3.85px;
} 

.section2 h2 span { 
letter-spacing:-8.85px; 
} 


.section2 {
	/* background-color: #white;
	<!-- <img src="/resources/images/r4.png"
						style="width: 80%; height: 60%;"> --> */
	/* background-image:url("/resources/images/r4.png"); */
	
}

.section3 {
	background-color: #white;
}

.section4 {
	background-color: #dddddd;
	color:#000;
}

.section4 h2 { 
font-weight: 500;
letter-spacing:-3.85px;
} 

.section4 h2 span { 
letter-spacing:-8.85px; 
} 

.section5 {
	background-color: #D2C598;
}

section div {
	font-family: 'Open Sans';
	font-style: normal;
	text-align: center;
	position: relative;
	top:;
	transform: translateY();
}

span {
	font-size: 4em;
}
</style>

<script>


function popup(){
    var url="/main/popup";
    var option="width=650, height=830,toolbar=no,directories=nostatus=no,menubar=no,resizable=no,scrollbars=no"
    window.open(url, "채용공고",option);
}

</script>

<body onLoad="popup()">
	<div id="main" class="scroll-container business">


			<section id="px5" class="section1 px_sect text-center ani_delay02 animated fadeIn">
				<div class="block-container">


					<h2 class="animated fadeInUp">LKH <b>Recruit</b></h2>


				</div>
			</section>




			<section class="section2">
				<div class="block-container">
					<div>
						<div class="row featurette" style="margin-top: 30px;">
							<label class="col-md-5"></label>
							<div class="col-md-7 ">
								<h2 class="featurette-heading ">
									Join LKH . <span class="text-dark">채용안내</span>
								</h2>
								</br>
							</div>
						</div>
						<img class="img-responsive" src="/resources/images/rc2.png"
							style="width: 70%; height: 30%;">
 
					</div>
					<div style="margin-top: 50px;">
						<a href="/resources/file/LKH자기소개서양식.doc" class="btn btn-outline-dark"
							download>양식다운로드</a>

					</div>
					</br>
					<div>
						<ul>* 작성된 이력서는 duddl154@naver.com로 보내주시길 바랍니다.
						</ul>
						<ul>* 저희 회사에 지원해주셔서 다시 한번 감사드립니다.
						</ul>
					</div>
				</div>
			</section>


			<section class="section4">
				<div class="block-container">
					<div class="row featurette">
						<label class="col-md-5"></label>
						<div class="col-md-7 mb-5">
							<h2 class="featurette-heading ">
								Join LKH . <span class="text-dark">직무소개</span>
							</h2>
							</br>
						</div>
					</div>
					<div class="container">
						<div class="row justify-content-around">
						<a data-toggle="collapse" data-target="#demo1"> <img class="img-responsive"
							src="/resources/images/iticon.png"
							style="width: 250px; height: 250px;"></a>
						
						 <a data-toggle="collapse" data-target="#demo2"> 
						 <img class="img-responsive"
							src="/resources/images/testicon.png"
							style="width: 250px; height: 250px;">
						</a>
						<a data-toggle="collapse" data-target="#demo3"> <img class="img-responsive"
							src="/resources/images/saleicon.png"
							style="width: 250px; height: 250px;">
						</a>
						</div>
						<div class="row justify-content-around pt-3">
						<h3>IT</h3>
						<h3>TEST</h3>
						<h3>SALE</h3>
						</div>
					</div>

					<div style="margin-top: 50px;">

						<div id="demo1" class="collapse">

							<table class="table table-dark">
								<thead>
									<tr>
										<th scope="col">IT부서</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>ABOUT IT: 인터넷이 일반화되면서 정보기술산업은 통신분야를 포함하게 되어 현재 IT라고
											하면 정보기술에 통신을 더하여 정보통신기술이라는 의미로 이해되고 있다.‘정보화 기본법’에 따르면 정보통신은
											정보의 수집, 가공, 저장, 검색, 송신, 수신 및 그 활용과 이에 관련되는 기기, 기술, 역무, 기타
											정보화를 촉진하기 위한 일련의 활동과 수단을 말한다. 전 세계 경제에서 IT 산업이 차지하는 비중은 꾸준히
											증가하고 있다.</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div id="demo2" class="collapse">

							<table class="table table-dark">
								<thead>
									<tr>
										<th scope="col">TEST부서</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>ABOUT TEST: 사람의 학력, 지능, 능력이나 제품의 성능 따위를 알아보기 위하여
											검사하거나 시험함. 또는 그런 검사나 시험. ‘검사3’, ‘시험3’으로 순화.</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div id="demo3" class="collapse">

							<table class="table table-dark">
								<thead>
									<tr>
										<th scope="col">SALE부서</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>ABOUT SALE: 영업이란 일정한 영업목적에 의하여 조직화된 유기적 일체로서의 기능적
											재산으로서 유기적 일체로서의 기능적 재산이란 영업을 구성하는 유형·무형의 재산과 경제적 가치를 갖는 사실관계가
											서로 유기적으로 결합하여 수익의 원천으로 기능한다는 것과 이와 같이 유기적으로 결합한 수익의 원천으로서의
											기능적 재산이 마치 하나의 재화와 같이 거래의 객체가 된다는 것을 뜻한다.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</section>







	<script type="text/javascript">
		new fullScroll({
			displayDots : true,
			dotsPosition : 'left',
			animateTime : 0.7,
			animateFunction : 'ease'
		});
	</script>
</div>
</body>
