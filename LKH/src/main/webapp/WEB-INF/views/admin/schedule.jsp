<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../admin_top.jsp" flush="false" />
<jsp:include page="../admin_sidebar.jsp" flush="false" />

<link href='/resources/css/fullcalendar.min.css' rel='stylesheet' />
<link href='/resources/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='/resources/js/moment.min.js'></script>
<script src='/resources/js/jquery.min.js'></script>
<script src='/resources/js/jquery-ui.min.js'></script>
<script src='/resources/js/fullcalendar.min.js'></script>
<script src='/resources/js/locale-all.js'></script>

<script type="text/javascript">
 
	$(document).ready(
			function calender() {

				$("#calendar").fullCalendar(
						{
							editable : true,
							navLinks : true,
							eventLimit : true,
							eventDurationEditable : true,
							refetchResourcesOnNavigate : true,
							droppable: true,
							locale : 'ko',
							eventResize : function(event, delta, revertFunc, jsEvent, ui, view){
								var defaultDuration = moment.duration($('#calendar').fullCalendar('option', 'defaultTimedEventDuration'));
								var end = event.end || event.start.clone().add(defaultDuration);
								console.log("eventResizeStop");
								console.log(event.title);	
								console.log(event.start.format());	
								console.log(event.end.format());	
								console.log(event.color);	
								$.ajax({
									type: "PUT",
									url:"/admin/updateschedule",
									headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
									dataType:"text",
									data:JSON.stringify({
										sname : event.title,
										startday : event.start.format(),
										endday : event.end.format(),
										grade : event.color
									}),
									success:function(){
										location.reload();
									},
									error:function(xhr,status,err){
										console.log("xhr"+xhr);
										console.log("status"+status);
										console.log("err"+err);				
									}				
								
								});
							}, 
						
							eventDrop : function (event, delta, revertFunc, jsEvent, ui, view) { 
								var defaultDuration = moment.duration($('#calendar').fullCalendar('option', 'defaultTimedEventDuration'));
								var end = event.end || event.start.clone().add(defaultDuration);
								console.log("eventdrop");	
								console.log(event.title);	
								console.log(event.start.format());	
								console.log(end.format());	
								console.log(event.color);	
								$.ajax({
									type: "PUT",
									url:"/admin/updateschedule",
									headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
									dataType:"text",
									data:JSON.stringify({
										sname : event.title,
										startday : event.start.format(),
										endday : end.format(),
										grade : event.color
									}),
									success:function(){
										location.reload();
									},
									error:function(xhr,status,err){
										console.log("xhr"+xhr);
										console.log("status"+status);
										console.log("err"+err);				
									}				
								
								});
						
								 
							},
							
							eventClick : function (calEvent, event, delta,revertFunc) { 
								console.log(calEvent.title);
								var del = confirm("일정 : "+calEvent.title+"\n"+"삭제하시겠습니까?");
								if(del==true){
									console.log(calEvent.title);
									var sname = calEvent.title; 
									$.ajax({
										type:"DELETE",
										url:"/admin/delschedule/"+sname,
										contentType: "application/x-www-form-urlencoded; charset=UTF-8",
										dataType:"text",
										success:function(result){
											location.reload();
										},
										error:function(xhr,status,err){
											console.log("xhr"+xhr);
											console.log("status"+status);
											console.log("err"+err);							
										},
									});	    	  
								}
								
							},

							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,basicWeek,basicDay'
							},
							selectable : true,
							selectHelper : true,
							navLinks : true, // can click day/week names to navigate views
							editable : true,
							eventLimit : true, // allow "more" link when too many events

							

							events : function(start, end, timezone, callback) {
								// 일정 목록 출력
								$.ajax({
									type : "POST",
									url : "/admin/schudule/list",
									dataType : "JSON",
									success : function(data, status, xhr) {

										var events = [];
										$.each(data.item, function(idx, rec) {
											events.push({
												title : rec.sname,
												start : rec.startday,
												end : rec.endday,
												color : rec.grade
											});

										});
										callback(events);

									},
									error : function(xhr, status, err) {
										console.log("xhr" + xhr);
										console.log("status" + status);
										console.log("err" + err);
									}

								});

							},
							
						

						});

			});


	$(function() {

		$("#add").on("click", function() {
			$("form").submit();

		});
		
		$("#del").on("click", function() {
			console.log(titlename);

		});
		$("#modify").on("click", function() {
			$('#myModal').modal('show')

		});
		
	}); 

</script>


<style>
 body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
} 

 #calendar {
	max-width: 900px;
	margin: 0 auto;
} 
</style>
<body>
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	
	<div id="calendar"></div>

	<div class="text-right pt-3">
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModal">일정등록</button>
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#exampleModal2">수정</button>
		
	</div> 
	<!-- Modal 일정 추가하기 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">일정등록</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form:form class="form-schedule" modelAttribute="asdto"
					action="${pageContext.request.contextPath }/admin/addschedule">
					<div class="modal-body">
						<div class="form-group row">
							<form:label path="sname">스케줄명</form:label>
							<form:input path="sname" required="true" />
							<form:select path="grade"
								class="col-sm-10 form-control form-control-sm is-valid"
								required="true">
								<option value="">구분</option>
								<option value="red">국가공휴일</option>
								<option value="green">사내휴일</option>
								<option value="yellow">사내행사</option>
								<option value="gray">기타</option>
							</form:select>
							<form:errors path="grade" cssClass="col-sm-5 errmsg"></form:errors>
						</div>
						<form:label path="startday">시작일</form:label>
						<form:input type="date" path="startday"
							class="col-sm-10 form-control form-control-sm is-valid"
							required="true" />
						<form:label path="endday">종료</form:label>
						<form:input type="date" path="endday"
							class="col-sm-10 form-control form-control-sm is-valid"
							required="true" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">취소</button>
						<button type="button" class="btn btn-primary" id="add">등록</button>
					</div>
				</form:form>
			</div>
		</div>
	</div> 
	
	<!-- 모달 리스트 일정 수정하기 -->
<div id="myModal" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div> 
<!-- 모달 리스트 일정 삭제하기 -->
<div id="myModal2" class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal adfsdftitle</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>Modal body text goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="del">Save changes</button>
      </div>
    </div>
  </div>
</div>


<div id="calendarModal" class="modal fade">
<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span> <span class="sr-only">close</span></button>
            <h4 id="modalTitle" class="modal-title"></h4>
        </div>
        <div id="modalBody" class="modal-body"> </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
</div>

	</main>
</body>
</html>