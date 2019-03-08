<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../main_top.jsp" flush="false"/>
<jsp:include page="../main_sidebar.jsp" flush="false"/>

<link href='/resources/css/fullcalendar.min.css' rel='stylesheet' />
<link href='/resources/css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='/resources/js/moment.min.js'></script>
<script src='/resources/js/jquery.min.js'></script>
<script src='/resources/js/fullcalendar.min.js'></script>
<script src='/resources/js/locale-all.js'></script>

<script>
$(document).ready(function() {
	

	$("#calendar").fullCalendar({
		editable : false,
		navLinks : false,
		eventLimit : false,
		locale : 'ko',

		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,basicWeek,basicDay'
		},
		events : function(start, end, timezone, callback){
			// 일정 목록 출력
		 	$.ajax({
		 type: "POST",
		 url: "/admin/schudule/list",
		 dataType:"JSON",
		 success: function(data,status,xhr){
		
			var events = [];
		 	$.each(data.item,function(idx,rec){
				events.push({title:rec.sname, start:rec.startday, end:rec.endday, color:rec.grade});
				
				console.log(rec.sname);
				console.log(rec.startday);
				console.log(rec.endday);

		 });
		 callback(events);
		
		
		 },
			 error: function(xhr,status,err){
			 console.log("xhr"+xhr);
			 console.log("status"+status);
			 console.log("err"+err);
		 }	
		
		 });
		 	

		 
			
		}
		

	});
});

</script>
<style>
body {
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 500px;
	margin: 0 auto;
}
</style>
<body>
<frameset rows="100%" border=0>
	<frame name="frame" src="http://member/main">
</frameset>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	
	
	

	<div class="table-responsive">
		<table class="table table-sm table-bordered">
		<colgroup>
				<col width="50%">
				<col width="50%">
			</colgroup>
			<thead class="thead-light"">
				<tr>
					<th>공지</th>
					<th>일정</th>
				</tr>
				
			</thead>
			<tbody>
				
					<tr>
						<th><div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>

				<tr>
					<th></th>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="noticeDTO">
					<tr>
						<td></td>
						<td>${noticeDTO.num }</td>
						 <td>
							<c:forEach begin="1" end="${noticeDTO.indent }">&nbsp;&nbsp;</c:forEach>
	          				  <c:if test="${noticeDTO.indent > 0 }">
	            				<img src="${pageContext.request.contextPath }/resources/images/re.png" width="20" style="dispaly=inline" >
	           					 </c:if>				
							<a class="text-reset" href="/member/noticeread?num=${noticeDTO.num }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} " class="text-decoration-none text-reset text-danger" >${noticeDTO.title }</a>
						</td>
						<td>관리자</td>
						<td>${noticeDTO.cdate }</td>
						<td>${noticeDTO.hit }</td>				 		
					</tr> 
				</c:forEach>
			</tbody>
		</table>
	</div>
						</th>
						<th><div id="calendar"></div></th>
						
					</tr>
				
			</tbody>
		</table>
		<table class="table table-sm table-bordered">
		<colgroup>
				
			</colgroup>
			<thead class="thead-light"">
				<tr>
					<th>부서별 공지사항</th>
				</tr>
				
			</thead>
			<tbody>
				
					<tr>
						<th>공지</th>
						
						
					</tr>
					<tr>
						<th>추후 업데이트 예정</th>
						
						
					</tr>
				
			</tbody>
		</table>
	</div>
	
	</main>
</body>
</html>