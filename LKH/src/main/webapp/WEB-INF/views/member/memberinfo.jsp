<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../main_top.jsp" flush="false" />
<jsp:include page="../main_sidebar.jsp" flush="false" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<script>
	$(function() {
		var id = $("#id").val();
		$("#modifyinfo").on("click", function() {
			location.href = "/member/memberModifyForm/"+id;
		});
		$("#modifypw").on("click", function() {
			location.href = "/member/memberPwModifyForm/"+id;
		});
	
	});
</script>
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

<div class="container mt-5">
<input type="hidden" name="id" id="id" value="${user.id }"/>
	<div class="card-deck mb-3 text-center">
		<div class="card mb-4 shadow-sm">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">내정보수정</h4>
			</div>
			<div class="card-body">

				<ul class="list-unstyled mt-3 mb-4">
					<li>개인정보를 수정합니다.</li>
					<li>이름, 주민등록번호, 전화번호, 주소 만 수정 가능합니다.</li> 
					<li>그 외 정보 수정은 관리자에게 문의해주십시오.</li>
					<li>비밀번호 변경 비밀번호 변경 페이지에서 가능합니다.</li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary" id="modifyinfo">수정페이지로
					이동</button>
			</div>
		</div>
		<div class="card mb-4 shadow-sm">
			<div class="card-header">
				<h4 class="my-0 font-weight-normal">비밀번호변경</h4>
			</div>
			<div class="card-body">

				<ul class="list-unstyled mt-3 mb-4">
					<li>비밀번호를 변경 합니다.</li>
					<li>현재 비밀번호를 모르실 경우</li>
					<li>비밀번호 찾기를 통해 비밀번호를 찾으시거나</li>
					<li>관리자에게 문의주십시오.</li>
				</ul>
				<button type="button"
					class="btn btn-lg btn-block btn-outline-primary" id="modifypw">변경페이지로 이동</button>
			</div>
		</div>
	</div>
</div>
</main>