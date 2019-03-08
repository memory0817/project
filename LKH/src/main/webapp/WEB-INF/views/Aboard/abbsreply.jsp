<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../main_top.jsp" flush="false"/>
<jsp:include page="../main_sidebar.jsp" flush="false"/>

<style>
.errmsg, {
	color: red;
}

.active {
  z-index: 2;
  color: #fff;
  background-color: #000;
  border-color: #000;
}
</style>

<script>
  $(function(){
	  

		//유효성체크 오류시 에러메세지 처리
 		$("span[id$='.errors']").each(function(idx){
			if($(this).text().length > 0) {
				$(this).prev().removeClass("is-valid").addClass("is-invalid");
				$(this).removeClass("valid-feedback").addClass("invalid-feedback");
			}
		});	  
		
		
		
		
	  
	  //게시글 등록
	  $("#btn1").on("click",function(e){
		  e.preventDefault();
		  //유효성 체크
		  if( valChk()) {				  
		  //form 태그의 action="/bbs/write" 서버전송
		  	$("form").submit(); 
		  }
	  });
	  
	  //게시글 취소
	  $("#btn2").on("click",function(e){
		  e.preventDefault();
		  location.href = "/Aboard/view?num=${itbbsdto.num }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} ";
	  });
	//목록으로 이동	  
	  $("#btn3").on("click", function(e) {
			e.preventDefault();
			location.href = "/Aboard/list";
		});
	  
  });

  function valChk(){

	    //제목입력값이 없을경우
	   if($("#title").val().length == 0){
	      alert('제목을 입력하세요!');
	      $("#title").focus();
	      return false;
	    }

	    //제목입력길이 체크
	    if($("#title").val().length > 30){
	      alert('30자 이상 입력불가!');
	      $("#title").focus();
	      return false;
	    }


	    //내용입력값이 없을경우
	    if($("#content").val.length == 0){
	      alert('내용을 입력하세요!');
	      $("#content").focus();
	      return false;
	    }

	    //내용입력길이 체크
	    if($("#content").val.length > 100){
	      alert('100자 이상 입력불가!');
	      $("#content").focus();
	      return false;
	    }


	    return true;  
	  
  };

</script>
<body>
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">답글</h1>
	</div>
	
	<form:form modelAttribute="abbsdto" action="/Aboard/replyOK" method="post">
		<form:hidden path="bgroup" value="${abbsdto.bgroup }" />
    <form:hidden path="step"  value="${abbsdto.step }" />
    <form:hidden path="indent" value="${abbsdto.indent }" />
    <form:hidden path="name" value="${user.name }" />
    <input type="hidden" name="reqPage" value="${rc.reqPage }" />
			<table class="table table-sm" summary="답글 작성">
				<colgroup>
					<col width="20%">
					<col width="">
				</colgroup>
				<tbody>			
					<tr>
						<th>제목</th>
						<td>
							<form:input class="form-control is-valid" path="title" type="text" placeholder="제목을 입력하세요" value="[답글]:${abbsdto.title }" autocomplete="off"/>
							<form:errors class="valid-feedback" path="title"></form:errors>
						</td>						
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<form:textarea class="form-control is-valid" path="content" rows="15" cols="30"  value="[원글]:${abbsdto.content }" ></form:textarea>
							<form:errors class="valid-feedback" path="content"></form:errors>	
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<button type="button" id="btn1"
								class="btn btn-sm btn-outline-dark">등록</button>
							<!-- <button type="button" id="btn2"
								class="btn btn-sm btn-outline-dark">취소</button> -->
							<button type="button" id="btn3"
								class="btn btn-sm btn-outline-dark">목록</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</main>
</body>
</html>