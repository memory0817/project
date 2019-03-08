<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!-- <link rel="stylesheet" type="text/css" href="/resources/bussiness/css/common.css" />  -->
<link href="/resources/css/fullscreenDemo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />

<!--  Bootstrap 시작-->
<link rel="stylesheet" href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->


<!-- 페이지 스크롤  시-->
<link rel="stylesheet" type="text/css" href="/resources/css/full-page-scroll.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<script src="/resources/js/full-page-scroll.js"></script>	
<!--  스크롤 끝  -->

<!-- font awesome -->
<link rel="stylesheet" 
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">



<script>
// very simple to use!
$('ul.nav li.dropdown').hover(function() {
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
}, function() {
  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
});
</script>

<style>

@import url("http://fonts.googleapis.com/earlyaccess/notosanskr.css");
@import url('https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700');

body{

font-family: 'Roboto', sans-serif;

}

a {
    color:rgba(255, 255, 255, 0.68);
    text-decoration: none;
    background-color: transparent;
}

a:hover {
    color: #fff;
    text-decoration: none;
    background-color: transparent;
}

.nav li {
	font-size:19.2px;
	color:rgba(255, 255, 255, 0.78);
}
.nav-item > a {
	font-weight: 700;
}
.dropdown-menu {
    font-size: 1rem;
} 

#mainmanu{
	position : absolute;
}

.manubar {
 background-color:rgba(0, 0, 0, 0.38); 
/* background: linear-gradient(rgb(0, 0, 0, 0.68),rgb(183, 181, 181,0.48)); */

}
</style>

<!-- <nav class="navbar navbar-light bg-light sticky-top"> -->
 <div class="container-fluid sticky-top" id="mainmanu">
    <div class="row justify-content-between manubar">
    <div class="col-md-1 ml-3 mt-1">
  <li class="nav-item dropdown">
  	<a href="/" >
    <img src="/resources/images/whitelogo.png" width="60" height="60" class="d-inline-block align-top mt-1" alt="로고">
     </a>
  </li>
   </div>
   <div class="col-md-9 mr-5">
    <ul class="nav justify-content-end ml-0 pl-0">
      <li class="nav-item dropdown pt-3">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Company
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        <a class="dropdown-item" href="/">LKH</a>
         <a class="dropdown-item" href="/#1">회사개요</a>
          <a class="dropdown-item" href="/#2">미션&비젼</a>
<!--           <a class="dropdown-item" href="/#3">연혁</a>
          <a class="dropdown-item" href="/#3">오시는 길</a> -->
        </div>
      </li>
      
      <li class="nav-item dropdown pt-3">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Business
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
        	<a class="dropdown-item" href="/main/business">Business</a> 
          <a class="dropdown-item" href="/main/business/#1">사업영역</a>
          <a class="dropdown-item" href="/main/business/#2">운영강점</a>
          <a class="dropdown-item" href="/main/business/#3">사업실적</a>
         <!--  <div class="dropdown-divider"></div> -->
        </div>
      </li>
      
      <li class="nav-item dropdown pt-3">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Recruit
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/main/recruit">Recruit</a>
          <a class="dropdown-item" href="/main/recruit/#1">채용안내</a>
          <a class="dropdown-item" href="/main/recruit/#2">채용분야</a>
        </div>
      </li>
      

      <li class="nav-item dropdown pt-3">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Contact
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/main/Q&A">Q&A</a>
          <a class="dropdown-item" href="/main/come">오시는길</a>
        </div> 
      </li>     
    </ul>
    <div class="col-md-2">&nbsp;</div>
</div></div></div>
<!-- </nav> -->