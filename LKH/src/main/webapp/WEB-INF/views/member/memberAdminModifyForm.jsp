<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../admin_top.jsp" flush="false" />
<jsp:include page="../admin_sidebar.jsp" flush="false" />

<script language="javascript">

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/popup/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
	}

	function jusoCallBack(inputYn,roadFullAddr,roadAddrPart1,roadAddrPart2,engAddr,jibunAddr,zipNo,addrDetail,admCd,rnMgtSn,bdMgtSn,detBdNmList){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.	
		document.forms[0].userAddr.value = inputYn,roadFullAddr,roadAddrPart1,roadAddrPart2,engAddr,addrDetail;
	}
</script>



<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

<div class="container">
	<div class="row justify-content-center">
		
			<form:form class="form-memberjoin" modelAttribute="mdto"
				action="${pageContext.request.contextPath }/admin/adminModify">

				<div class="form-group row">
					<form:label path="id"
						class="col-sm-6 col-form-label col-form-label-sm">아이디</form:label>
					<form:input type="text" path="id"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<h5>@lkh.com</h5>
					<form:errors path="id" cssClass="col-sm-5 errmsg"></form:errors>
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
					<form:label path="division"
						class="col-sm-6 col-form-label col-form-label-sm">부서</form:label>
					<form:select path="division"
						class="col-sm-10 form-control form-control-sm is-valid">
						<option value="${mdto.division}">${mdto.division}</option>
						<option value="경영지원">경영지원</option>
						<option value="영업">영업</option>
						<option value="개발">개발</option>
						<option value="테스트">테스트</option>
					</form:select>
					<form:errors path="division" cssClass="col-sm-5 errmsg"></form:errors>
				</div>

				<div class="form-group row">
					<form:label path="position"
						class="col-sm-6 col-form-label col-form-label-sm">직급</form:label>
					<form:select path="position"
						class="col-sm-10 form-control form-control-sm is-valid">
						<option value="${mdto.position}">${mdto.position}</option>
						<option value="사원">사원</option>
						<option value="대리">대리</option>
						<option value="과장">과장</option>
						<option value="차장">차장</option>
						<option value="부장">부장</option>
						<option value="상무">상무</option>
					</form:select>
					<form:errors path="position" cssClass="col-sm-5 errmsg"></form:errors>
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
					<form:label path="memberno"
						class="col-sm-6 col-form-label col-form-label-sm">사번</form:label>
					<form:input type="text" path="memberno"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<form:errors path="memberno" cssClass="col-sm-5 errmsg"></form:errors>
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
					<form:label path="cdate" class="col-sm-6 col-form-label col-form-label-sm">입사일</form:label>
					<form:input type="text" path="cdate"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="true" value="${mdto.cdate}"></form:input>
					<form:errors path="cdate" cssClass="col-sm-5 errmsg"></form:errors>
				</div>


		<div class="form-group row">
			<div class="col-sm"></div>
			<div class="col-sm-10">
				<button class="btn btn-primary" type="submit" id="joinBtn">수정</button>
				
				<button class="btn btn-primary" type="submit" href="/admin/memberlist">취소</button>
			</div>
		</div>
		</form:form>

	</div>

</div>
</div>
</main>

