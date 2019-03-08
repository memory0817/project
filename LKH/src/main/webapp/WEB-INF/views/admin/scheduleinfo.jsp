<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	
	<!-- Modal -->
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