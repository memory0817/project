<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>



<jsp:include page="../main_top.jsp" flush="false"/>
<jsp:include page="../main_sidebar.jsp" flush="false"/>

<script>
	$(function () {
		
		//답글버튼 
		$("#btn1").on("click", function (e) {
			location.href = "/TEST/replyForm/${test_bbsDTO.num}/${rc.reqPage}";
		});
		
		//수정버튼 클릭시 
		$("#btn2").on("click", function (e) {
			location.href = "/TEST/modifyform/${test_bbsDTO.num}/${rc.reqPage}";
			
		});

		//삭제버튼
		$("#btn3").on("click", function (e) {
			location.href = "/TEST/delete?num=${test_bbsDTO.num}&reqPage=${rc.reqPage}";
		});


		//목록버튼 : 목록으로 이동
		$("#btn4").on("click", function (e) {

			location.href="/TEST/list"
		});


		//유효성체크 오류시 에러메세지 처리
		$("span[id$='.errors']").each(function (idx) {
			if ($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
				$(this).removeClass("valid-feedback").addClass("invalid-feedback");
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
<sec:authorize var="admin" access="hasRole('ROLE_ADMIN')"/>
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">게시글읽기</h1>
		</div>
		<div class="table-responsive">
			<form:form modelAttribute="test_bbsDTO" action=""	method="post">
				<form:hidden path="num" value="${test_bbsDTO.num }" />
				<input type="hidden" name="reqPage" value="${rc.reqPage }" />
				<table class="table table-sm" summary="게시글 보기">
					<colgroup>
						<col width="5%">
						<col width="">
					</colgroup>
					<tbody>
						<tr>
							<th>제목</th>
							<td><form:input class="form-control is-valid" path="title"
									type="text" placeholder="제목을 입력하세요" readonly="true"	value="${test_bbsDTO.title }" /> 
									<form:errors class="valid-feedback" path="title"></form:errors></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>&nbsp;${test_bbsDTO.name } </td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>&nbsp;${test_bbsDTO.position } </td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<form:textarea class="form-control is-valid" path="content" rows="15" cols="30"	
								value="${test_bbsDTO.content }" readonly="true" /> 
								<form:errors	class="valid-feedback" path="content"></form:errors>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								
								
									<button type="button" id="btn1"	class="btn btn-sm btn-outline-dark">답글</button>	
									<c:if test="${test_bbsDTO.name eq user.name or admin}">								
									<button type="button" id="btn2" class="btn btn-sm btn-outline-dark">수정</button>
									<button type="button" id="btn3"	class="btn btn-sm btn-outline-dark">삭제</button>
									</c:if>
							
									<button type="button" id="btn4"	class="btn btn-sm btn-outline-dark">목록</button>
								
							</td>
						</tr>
					</tbody>
				</table>
				<jsp:include page="../reply/TEST_reReply.jsp" flush="false" />
			</form:form>
		</div>
	</main>
</body>
</html>