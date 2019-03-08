<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../main_top.jsp" flush="false" />
<jsp:include page="../main_sidebar.jsp" flush="false" />

<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
<script>
$(function() {
	
	// 목록버튼
	$("#cancle").on("click",function(e){
		e.preventDefault();
		console.log("1");
		location.href="/member/myinfo";
	});

});
</script>

<script>

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/popup/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(inputYn,roadFullAddr,roadAddrPart1,roadAddrPart2,engAddr,jibunAddr,zipNo,addrDetail,admCd,rnMgtSn,bdMgtSn,detBdNmList,
			bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.	
		document.forms[0].userAddr.value = inputYn,roadFullAddr,roadAddrPart1,roadAddrPart2,engAddr,addrDetail;
	}

	
	
</script>

<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

<div class="container">
	<div class="row justify-content-center">

		<form:form class="form-memberjoin" modelAttribute="mdto"
			action="${pageContext.request.contextPath }/member/memberModify">

				<div class="form-group row">
					<form:hidden  path="id"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
				</div>
				
			<%-- 	<div class="form-group row">
					<form:label path="pw"
						class="col-sm-6 col-form-label col-form-label-sm">현재 비밀번호를 입력해주세요</form:label>
					<form:input  path="pw" type="password"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" value="   "/>
					<form:errors path="pw" cssClass="col-sm-5 errmsg"></form:errors>
				</div> --%>
				
				<div class="form-group row">
					<form:label path="email" class="col-sm-6 col-form-label col-form-label-sm">이메일</form:label>
					<p>(비밀번호를 찾을때 사용됩니다.)</p>
					<form:input type="text" path="email" class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<br>
					<form:errors path="email" cssClass="col-sm-5 errmsg"></form:errors>
				</div>

			
				<div class="form-group row">
					<form:label path="name"
						class="col-sm-6 col-form-label col-form-label-sm">이름</form:label>
					<form:input type="text" path="name"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="true" />
					<form:errors path="name" cssClass="col-sm-5 errmsg"></form:errors>
				</div>


				<div class="form-group row">
					<form:label path="snumber"
						class="col-sm-6 col-form-label col-form-label-sm">주민등록번호</form:label>
					<form:input type="text" path="snumber"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<form:errors path="snumber" cssClass="col-sm-5 errmsg"></form:errors>
				</div>


				<div class="form-group row">
					<form:label path="tel"
						class="col-sm-6 col-form-label col-form-label-sm">전화번호</form:label>
					<form:input type="text" path="tel"
						class="col-sm-10 form-control form-control-sm is-valid"
						placeholder="전화번호를 입력하세요 ex)010-1234-5678" required="true" />
					<form:errors path="tel" cssClass="col-sm-5 errmsg"></form:errors>
				</div>

					<div class="form-group row">
					<form:label path="address"
						class="col-sm-6 col-form-label col-form-label-sm">주소</form:label>
					<form:input type="text" id="userAddr" path="address"
						name="userAddr"
						class="col-sm-10 form-control form-control-sm is-valid"
						placeholder="주소검색" required="true" readonly="true" />
						
						<button type="button" class="btn btn-warning" onclick="goPopup()">주소검색</button>
					<form:errors path="address" cssClass="col-sm-5 errmsg"></form:errors>
				
					
				</div>

		<div class="form-group row">
			<div class="col-sm-10">
				<button class="btn btn-primary" type="submit" id="joinBtn">수정</button>
				
				<button class="btn btn-primary" id="cancle">취소</button>
			</div>
		</div>
		</form:form>

	</div>

</div>
</main>

