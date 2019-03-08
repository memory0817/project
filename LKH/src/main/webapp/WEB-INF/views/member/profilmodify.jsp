<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 수정</title>
<!--  Bootstrap 시작-->
<link rel="stylesheet"
	href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->
</head>
<script>
//사진업로드버튼
$(function () {
$("#save").on("click", function(e) {
		 var form = $('#modify')[0];
		 var formData = new FormData(form);

		 console.log(form);
		 console.log(formData);
		        $.ajax({
	            type : 'post',
	            url : '/member/modify',
	            dataType : "text",
	            data : formData,
	            processData : false,
	            contentType : false,
	            success : function() {
	                alert("수정되었습니다.");
	                window.close();

	            },
	            error : function(error) {
	                alert("파일 업로드에 실패하였습니다.");
	                console.log(error);
	                console.log(error.status);
	            }
	        }); 
		        
		$("profil").attr("src",formData);
	});
});
</script>
<body onunload="javascript:opener.location.reload();">
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>
	<form id="modify" action="">
	<input type="file" name="file" />
	<button id="save">등록</button>
	<input type="hidden" name="id" value="${user.id }"/>
	</form>

</body>
</html>