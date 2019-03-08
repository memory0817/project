<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../index_top.jsp" flush="false" />



<script>
	$(function() {
		
		
		//취소 : 목록으로 이동
		$("#cancle").on("click", function(e) { 
			console.log("1");
			location.href = "/gboard/list"
		});
		//OK버튼 
		$("#ok").on("click", function(e) {
			var pwchk = $("#pwcheck").val();
			var pw = $("#pw").val();
			console.log(pwchk);
			console.log(pw);
				if($("#pwcheck").val() == $("#pw").val()){
					console.log("11")
					$("#table").removeClass("d-none"); 
					$("#read").removeClass("d-none"); 
					
					$(".modal").modal("hide");
				}else{
					
					console.log("22");
				}
		});

		//수정버튼 : 글수정으로 이동
		$("#btn1").on("click", function(e) {

			location.href = "/gboard/modify/${gbbsDTO.num}/${rc.reqPage}";
		});
		//삭제버튼
		$("#btn2").on("click", function(e) {

			location.href = "/gboard/delete?num=${gbbsDTO.num}&reqPage=${rc.reqPage}";
		});
		//목록버튼 : 목록으로 이동
		$("#btn3").on("click", function(e) {

			location.href = "/gboard/list";
		});

		//유효성체크 오류시 에러메세지 처리
		$("span[id$='.errors']").each(
				function(idx) {
					if ($(this).text().length > 0) {
						$(this).prev().removeClass("is-valid").addClass(
								"is-invalid");
						$(this).removeClass("valid-feedback").addClass(
								"invalid-feedback");
					}
				});

	});

	function valChk() {

		//제목입력값이 없을경우
		if ($("#title").val().length == 0) {
			alert('제목을 입력하세요!');
			$("#title").focus();
			return false;
		}

		//제목입력길이 체크
		if ($("#title").val().length > 30) {
			alert('30자 이상 입력불가!');
			$("#title").focus();
			return false;
		}

		//내용입력값이 없을경우
		if ($("#content").val.length == 0) {
			alert('내용을 입력하세요!');
			$("#content").focus();
			return false;
		}

		//내용입력길이 체크
		if ($("#content").val.length > 100) {
			alert('100자 이상 입력불가!');
			$("#content").focus();
			return false;
		}

		return true;

	};
</script>
<style>
.errmsg {
	color: red;
}
</style>
<body>
<div class="block-container">
	<div class="container" style="padding-top:5%;">
	<div class="text-center mb-4"> 
		<h1 id="read" class="h2">게시글읽기</h1>
		</div>
		<div id="table" class="table-responsive">
			<form:form modelAttribute="gbbsDTO" action=""	method="post">
				
				<form:hidden path="num" value="${gbbsDTO.num }" />
				<input type="hidden" name="reqPage" value="${rc.reqPage }" />
				<table class="table table-sm" summary="게시글 보기">
					<colgroup>
						<col width="5%">
						<col width="">
					</colgroup>
					<tbody>
						<tr>
							<th>제목</th>
							<td>${gbbsDTO.title }</td>
							<form:hidden path="pw" value="${gbbsDTO.pw }" />
							<form:hidden path="num" value="${gbbsDTO.num }" />
						</tr>
						<tr>
							<th>작성자</th>
							<td>${gbbsDTO.id } </td>
						</tr>
						
						<tr>
							<th>내용</th>
							<td>
								<form:textarea class="form-control" path="content" rows="15" cols="30"	
								value="${gbbsDTO.content }" readonly="true" /> 
								<form:errors	class="valid-feedback" path="content"></form:errors>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								
									<button type="button" id="btn1"	class="btn btn-sm btn-outline-dark">수정</button>
									<button type="button" id="btn2"	class="btn btn-sm btn-outline-dark">삭제</button>
									<button type="button" id="btn3"	class="btn btn-sm btn-outline-dark">목록</button>
								
							</td>
						</tr>
					</tbody>
				</table>
				<jsp:include page="../reply/g_reReply.jsp" flush="false" /> 
			</form:form>
		</div> </div>
		</div> 
</body>
</html>