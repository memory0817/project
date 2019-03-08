<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!--  Bootstrap 시작-->
<link rel="stylesheet"
	href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->
<style>
.errmsg
,
{
color
 
:
 
red
;


}
</style>
<script>
	$(function() {

		// 글자수 제한-------------------------------
		var $content = $("#content");
		$("#content").on("keyup", function() {
			var content = $("#content").val();
			var cnt = content.trim().length;

			$("#count").html(cnt + '/300')
			if (cnt > 300) {
				$("#content").addClass("is-invalid");
				content = content.substring(content.length - 300);
				$content.val(content);

				$("#count").html(cnt + '/300')
				return false;
			}
			$("#content").removeClass("is-invalid");
		});

		//유효성체크 오류시 에러표시 css적용 
		$("span[id$='.errors']").each(
				function(idx) {
					if ($(this).text().length > 0) {
						$(this).prev().removeClass("is-valid").addClass(
								"is-invalid");
						$(this).removeClass("valid-feedback").addClass(
								"invalid-feedback");
					}
				});

		// 게시글 등록
		$("#btn1").on("click", function(e) {
			e.preventDefault();
			//유효성체크

			if (valchk()) {

				$("form").submit();
			}

		});

		// 취소버튼
		$("#btn2").on("click", function(e) {
			e.preventDefault();
			//유효성체크

			location.href = "/HR/list";

		});

	});

	//유효성체크 함수
	function valchk() {
		if ($("#title").val().length == 0) {
			alert('제목을 입력하세요.');
			$("#title").focus();
			return false;
		}
		if ($("#title").val().length > 30) {
			alert('제목이 너무 긴데요?????(30자 이하로 입력해주세요.)');
			$("#title").focus();
			return false;
		}

		if ($("#content").val().length == 0) {
			alert('내용을 입력하세요.');
			$("#content").focus();
			return false;
		}
		if ($("#content").val().length > 300) {
			alert('내용도 좀 많군요.......(300자 이하로 입력해주세요.)');
			$("#content").focus();
			return false;
		}

		return true;

	}
</script>
<body>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">Q&A 작성</h1>
	</div>

	<form:form modelAttribute="gbbsDTO" action="/gboard/writeOK"
		method="post">

		<table class="table table-sm" summary="게시글 쓰기">
			<colgroup>
				<col width="20%">
				<col width="">
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td><form:input class="form-control is-valid" path="title"
							type="text" placeholder="제목을 입력하세요" /> <form:errors
							class="valid-feedback" path="title"></form:errors></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><form:input class="form-control" path="id" type="text" />
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><form:input class="form-control" path="pw" type="password" />
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><form:textarea class="form-control is-valid"
							path="content" rows="15" cols="30" placeholder="내용을 입력하세요" /> <form:errors
							class="valid-feedback" path="content"></form:errors>
						<p class="text-right" id="count">(0/300)</p></td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<button type="button" id="btn1"
							class="btn btn-sm btn-outline-dark">등록</button>
						<button type="button" id="btn2"
							class="btn btn-sm btn-outline-dark">취소</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form> </main>
</body>
</html>