<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<!--  Bootstrap 시작-->
<link rel="stylesheet"
	href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->


<!-- Custom styles for this template -->
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="/resources/css/signin.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700|Noto+Serif+KR:300,400,700|Open+Sans:300i,400,400i,600,700,800&amp;subset=korean"
	rel="stylesheet">

<!-- <!— font awesome —> -->

<link rel="stylesheet" href="/webjars/font-awesome/5.6.3/css/all.css" />
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
	#errmsg {
		color: red;
	}
}
span {
 font-size: 1em;
 font-weight:100;
 letter-spacing:-0.85px; 
}
</style>

</head>

<script>
	$(function() {

		$("#id").on("keyup", function() {
			$("#errmsg").text('');
		});

		//비밀번호 확인
		$("#pw").on("keyup", function() {
			$("#errmsg").text('');

		});
	});
	$(document).ready(function() {
	    $(function ()  {
		/*    $("#rememberme").popover({ content: "개인정보 보호를 위해 개인 PC에서만 사용하세요."});  */
	    	$('[data-toggle="popover"]').popover();   
	    });  
	   });
</script>
<body class="text-center">

	<form:form class="form-signin" modelAttribute="login"
		action="/login">
		<div class="block"> 
		<img src="/resources/images/blacklogo.png" width="100%" alt="lkh로그인로고" />
		</div>
		<h1 class="h3 mb-4 font-weight-bold"><span>LKH사원</span><br>로그인</h1>  
		<div class="form-label-group">
			<form:input class="form-control" type="text" path="id"
				placeholder="ID를 입력하세요." required="required" autofocus="autofocus" value="${tryid }" autocomplete="off"/>
		</div>

		<div class="form-label-group">
			<form:input class="form-control" type="password" path="pw"
				placeholder="비밀번호를 입력하세요." required="required" autofocus="autofocus" />
			<p id="errmsg">${requestScope.errmsg }<p>
		</div>
		<div class="checkbox mb-3">
					<label> 
					<input type="checkbox" name="remember-me" data-placement="left" data-toggle="popover" data-content="개인정보 보호를 위해 개인 PC에서만 사용하세요."/>
						로그인 유지
					</label>
				</div>
		<div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		</div>

		<div align="center" style="margin-top: 10px;">
			<a class="" style="padding-right: 10px;" href="/login/findID">아이디찾기</a>
			<a class="" href="/login/idchk">비밀번호찾기</a>
		</div>


		<p class="mt-5 mb-3 text-muted">&copy; LKH CO.</p>
	</form:form>


</body>
</html>

