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
</script>
<body class="text-center">

	<form:form class="form-signin" modelAttribute="login"
		action="/login/findpwok">
		<h1 class="h3 mb-3 font-weight-normal">비밀번호 찾기</h1>
		
		<div class="form-label-group">
			<form:input class="form-control" type="text" path="id"
				value="${id }" required="required" autofocus="autofocus" readonly="true"/>
		</div>
		
		<div class="form-label-group">
			<form:input class="form-control" type="text" path="name"
				placeholder="이름을 입력하세요." required="required" autofocus="autofocus" />
		</div>

		<div class="form-label-group">
			<form:input class="form-control" type="text" path="division"
				placeholder="부서를 입력하세요." required="required" autofocus="autofocus" />
			<p id="errmsg">${errmsg }<p>
		</div>
		
		<div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">비밀번호 찾기</button>
		</div>

		<p class="mt-5 mb-3 text-muted">&copy; LKH CO.</p>
	</form:form>


</body>
</html>

