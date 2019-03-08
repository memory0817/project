<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../main_top.jsp" flush="false" />
<jsp:include page="../main_sidebar.jsp" flush="false" />
<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">


<script>
	$(function() {
	

		//비밀번호 확인
		$("#newpw,#newpwchk").on("keyup", function() {
			console.log("1");
			if ($("#newpw").val() != $("#newpwchk").val()) {
				console.log($(this).val());  
				$(".pwErr").text('비밀번호가 일치하지 않습니다.');
				$("#pwokmsg").text('');

				$(this).focus();
			} else {
				$(".pwErr").text('');
				$("#pwokmsg").text('사용하셔도 좋습니다.');
			}
		})


	});
</script>
<style>
.pwErr{
	color : red;
}
#pwokmsg{color:green;}
</style>


<div class="container">
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

	<div class="row justify-content-center">

		<form:form class="form-memberjoin" modelAttribute="mdto"
			action="${pageContext.request.contextPath }/member/memberPwModify">

			<div class="form-group row ">
				
				<form:input type="hidden" path="id"
					class="col-sm-10 form-control form-control-sm is-valid"
					required="required" />
			
			</div>
		<%-- 	
			<div class="form-group row ">
				<form:label path="pw"
					class="col-sm-6 col-form-label col-form-label-sm">현재 비밀번호</form:label>
				<form:input type="password" path="pw"
					class="col-sm-10 form-control form-control-sm is-valid"
					required="required" />
				<form:errors path="pw" cssClass="col-sm-5 errmsg"></form:errors>
			</div> --%>

			<div class="form-group row ">
				<form:label path="newpw"
					class="col-sm-6 col-form-label col-form-label-sm">새 비밀번호</form:label>
				<form:input type="password" path="newpw"
					class="col-sm-10 form-control form-control-sm is-valid"
					required="required" />
				<form:errors path="newpw" cssClass="col-sm-5 errmsg"></form:errors>
			</div>

			<div class="form-group row">
				<label for="newpwchk" class="col-sm-6 col-form-label col-form-label-sm">새
					비밀번호확인</label> <input type="password" id="newpwchk"
					class="col-sm-10 form-control form-control-sm is-valid" required="required" />
			</div>
				<span class="pwErr"></span>
				<span id="pwokmsg"></span>
				<div>
					<button class="btn btn-primary" type="submit" id="modifypwbtn">변경</button>
				</div>

		</form:form>

	</div>

</div>
</main>

