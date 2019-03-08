<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index_top.jsp" flush="false" />

<!--  Bootstrap 시작-->
<!-- <link rel="stylesheet"
	href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script> -->
<!-- Bootstrap -->
<script>
	$(function() {

		// 확인버튼
		$("#btn1").on(
				"click",
				function(e) {
					if ($("#id").val() == $("#inputid").val()
							&& $("#pw").val() == $("#inputpw").val()) {
						console.log("11");
						var num = $("#num").val();
						var reqPage = $("#reqPage").val();
						/*var reqPage =  */
						location.href = "/gboard/viewok?num=" + num
								+ "&reqPage=" + reqPage;
					} else {
						$("#inputid").val("");
						$("#inputpw").val("");
						$("#errmsg")
								.text("입력하신 정보가 일치하지 않습니다. 다시 한번 확인해 주십시오.");
					}

				});

		// 목록으로 이동
		$("#btn2").on("click", function(e) {

			location.href = "/gboard/list";
		});
		$("#inputid,#inputpw").on("keyup", function() {
			$("#errmsg").text("");

		})

	});
</script>
<style>
#errmsg {
	color: red;
}
</style>
<div class="block-container">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-4">

				<div class="text-center mb-4">
					<img class="mb-4" src="/resources/images/qnaimg.jpg" alt="qna찾기"
						width="300" height="300">
					<h1 class="h3 mb-3 font-weight-normal">Q & A</h1>
					<!-- <p>Build form controls with floating labels via the <code>:placeholder-shown</code> pseudo-element. <a href="https://caniuse.com/#feat=css-placeholder-shown">Works in latest Chrome, Safari, and Firefox.</a></p> -->
				</div>
				<form action="">
					<div>

						<label> <input type="hidden" id="num" name="num"
							value="${gbbsDTO.num }" /> <input type="hidden" id="reqPage"
							name="reqPage" value="${rc.reqPage}" /> <input type="hidden"
							value="${gbbsDTO.id }" id="id" /> <input type="hidden"
							value="${gbbsDTO.pw }" id="pw" />
						</label> <input type="text" class="form-control" name="reqPage"
							id="inputid" placeholder="작성시 사용하셨던 아이디를 입력하세요"
							style="text-align: center;" required="ture" autofocus="ture" />
						<input type="text" class="form-control" name="reqPage"
							id="inputpw" placeholder="비밀번호를 입력하세요"
							style="text-align: center;" required="ture" autofocus="ture" />
						<span id="errmsg"></span>
						<div class="mb-2"></div>
					</div>
					<div class="mb-10"></div>
					<button id="btn1" class="btn btn-lg btn-dark btn-block"
						type="button">확인</button>
					<p class="mt-5 mb-3 text-muted text-center">&nbsp;</p>
				</form>

			</div>
		</div>
	</div>
</div>
</body>
</html>