<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
   content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v3.8.5">
<title>Signin Template · Bootstrap</title>
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

   <form:form class="form-signin" modelAttribute="login" action="/login">
      <h1 class="h3 mb-3 font-weight-normal">Admin Log-IN</h1>
      <div class="form-label-group">
         <form:input class="form-control" type="text" path="id" placeholder="ID를 입력하세요." required="required" autofocus="autofocus" 
          value="admin" readonly='true' />
      </div>

      <div class="form-label-group">
         <form:input class="form-control" type="password" path="pw"
            placeholder="비밀번호를 입력하세요." required="required" autofocus="autofocus" />
         <p id="errmsg">${errmsg }
         <p>
      </div>
      <div class="checkbox mb-3">
					<label> 
					<input type="checkbox" name="remember-me"/>
						Remember me
					</label>
				</div>
<div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
		</div>     



      <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
   </form:form>


</body>
</html>