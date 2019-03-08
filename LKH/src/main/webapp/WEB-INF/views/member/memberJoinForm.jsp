<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../admin_top.jsp" flush="false" />
<jsp:include page="../admin_sidebar.jsp" flush="false" />




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


<script>
	$(function() {
		
		// 아이디 중복검사
		$("#idcheck").on("click", function(e) {
			var id = $("#id").val();
			e.preventDefault();
			console.log("idcheckbtn");
			if ($("#id").val().length == 0) {
				$("#checkmsg").text("아이디를 입력하세요.");
			} else {
				console.log("else");
				console.log(id);
				//AJAX 비동기 처리기술
				$.ajax({
					type : "PUT", //post, get, put, delete
					url : "/member/idcheck/"+id,
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "PUT"
					},
					dataType : "text",
					data : JSON.stringify({
						id : id

					}),

					success : function(result) {
						console.log(result);
						if (result) {
							$("#checkmsg").text("중복된 아이디가 존재합니다.");
							$("#okmsg").text("");
						} else {
							$("#okmsg").text("사용가능한 아이디 입니다.");
							$("#checkmsg").text("");
						}

					},
					error : function(xhr, status, err) {
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}

				});
			}

		});

		//유효성체크 오류시 에러표시 css적용 
		$(".errmsg").each(function(idx) {
			if ($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
			}
		});

		//비밀번호 확인
		$("#pw,#pwchk").on("keyup", function() {
			if ($("#pw").val() != $("#pwchk").val()) {
				console.log($(this).val());
				$(".pwErr").text('비밀번호가 일치하지 않습니다.');
				$("#pwokmsg").text('');

				$(this).focus();
			} else {
				$(".pwErr").text('');
				$("#pwokmsg").text('사용하셔도 좋습니다.');
			}
		})

		// 가입버튼
		/* $("#joinBtn").on("click", function(e) {
			e.preventDefault();

			$("form").submit();
		}); */

		// 가입초기화버튼
		$("#joinClearBtn").on("click", function(e) {
			e.preventDefault();
			$("form").each(function() {
				this.reset();
			});
		});

		// 가입취소버튼
		$("#joinCancelBtn").on("click", function(e) {
			e.preventDefault();
			location.href = "/login/loginForm";
		});

		// 회원목록버튼
		$("#listBtn").on("click", function(e) {
			e.preventDefault();
			location.href = "/member/memberList";
		});

	});
</script>

<style>
	.errmsg, .pwErr {color:red;}
	#checkmsg{color: #f00;} 
	#okmsg, #pwokmsg{color:green;}
</style>       


<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 mt-5">

<div class="container">
	<div class="row justify-content-center">
		<div class="">
				<h1>회원가입</h1>
			<form:form class="form-memberjoin" modelAttribute="mdto"
				action="${pageContext.request.contextPath }/member/memberJoin">

				<div class="form-group row">
					<form:label path="id" class="col-sm-6 col-form-label col-form-label-sm">아이디</form:label>
					<form:input type="text" path="id" class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<br>
					<form:errors path="id" cssClass="col-sm-5 errmsg"></form:errors>
					<div>
					<span id="checkmsg"></span>
					<span id="okmsg"></span>
					
				</div>
				</div>
				
				<div class="form-group ">
						<button class="btn btn-primary" id="idcheck">중복검사</button>
					</div>
				
				<div class="form-group row ">
					<form:label path="pw"
						class="col-sm-6 col-form-label col-form-label-sm">비밀번호</form:label>
					<form:input type="password" path="pw"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="required" />
					<form:errors path="pw" cssClass="col-sm-5 errmsg"></form:errors>
				</div>

				<div class="form-group row">
					<label for="pwchk"
						class="col-sm-6 col-form-label col-form-label-sm">비밀번호확인</label> <input
						type="password" id="pwchk"
						class="col-sm-10 form-control form-control-sm is-valid "
						 required /> 
				</div>
				<label class="pwErr" style="font-color:red;"></label>
				<span id="pwokmsg"></span>
				<div class="form-group row">
					<form:label path="email" class="col-sm-6 col-form-label col-form-label-sm">이메일</form:label>
					<form:input type="text" path="email" class="col-sm-10 form-control form-control-sm is-valid"
						required="required" placeholder="*비밀번호를 찾을때 사용됩니다!"/>
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
					<form:label path="division" class="col-sm-6 col-form-label col-form-label-sm">부서</form:label>
					<form:select path="division" class="col-sm-10 form-control form-control-sm is-valid">
						<option value="">부서</option>
						<option value="경영지원">경영지원</option>
						<option value="영업">영업</option>
						<option value="개발">개발</option>
						<option value="테스트">테스트</option>
					</form:select>
					<form:errors path="division" cssClass="col-sm-5 errmsg"></form:errors>
				</div>
				
				<div class="form-group row">
					<form:label path="position" class="col-sm-6 col-form-label col-form-label-sm">직급</form:label>
					<form:select path="position" class="col-sm-10 form-control form-control-sm is-valid">
						<option value="">직급</option>
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
						
						
					<form:errors path="address" cssClass="col-sm-5 errmsg"></form:errors>
				</div>
				
				<div class="form-group ">
						<button type="button" class="btn btn-warning" onclick="goPopup()">주소검색</button>
					</div>


				<div class="form-group row">
					<form:label path="cdate" class="col-sm-6 col-form-label col-form-label-sm">입사일</form:label>
					<form:input type="date" path="cdate"
						class="col-sm-10 form-control form-control-sm is-valid"
						required="true" />
					<form:errors path="cdate" cssClass="col-sm-5 errmsg"></form:errors>
				</div>



				<div class="form-group row">
					<div class="col-sm"></div>
					<div class="col-sm-10 mt-4">
						<button class="btn btn-primary" type="submit" id="joinBtn">가입</button>
						<button class="btn btn-primary" type="submit" id="joinClearBtn">초기화</button>
					</div>
				</div>
			</form:form>

		</div>

	</div>
</div>
</main>


