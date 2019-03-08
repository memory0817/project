<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../admin_top.jsp" flush="false" />
<jsp:include page="../admin_sidebar.jsp" flush="false" />


<script>
		var delid;
	$(function() {
		$("#searchbtn").on("click", function() {
			if ($("#keyword").val().trim().length == 0) {
				alert('검색어를 입력하세요.');
				return;
			} else {
				var searchType = $("#searchType").val();
				var keyword = $("#keyword").val().trim();
				console.log(searchType);
				console.log(keyword);
				$("form").submit();
			}
		});
			$("#clearbtn,#modifybtn").on("click",function(){
				console.log("1")
				$(location).attr("href",$(this).attr("data-memurl"));
			});
			
			  $(".deletebtn").on("click",function(){
				
				 var myBookId = $(this).data('id');
				 delid = $(this).data('id');
				 console.log(myBookId);
				 $(".modal-body #bookId").val( myBookId );
				 
				 $(".modal").modal('show');

			});  
			  $("#delbtn").on("click",function(){
				 
				console.log(delid);
				location.href = "/admin/memberDelete/"+delid;
 
			});  

	});
</script>

<body>

	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"> 
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">사원관리</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			<div class="btn-group mr-2">
				<form action="/admin/memberlist" method="post">
					<input type="text" id="keyword" name="keyword" />
					<button id="searchbtn">검색</button>
				</form>

			</div> 
			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">부서</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href="/admin/memberlist?keyword=경영지원">경영지원</a>
					<a class="dropdown-item" href="/admin/memberlist?keyword=영업">영업</a>
					<a class="dropdown-item" href="/admin/memberlist?keyword=개발">개발</a>
					<a class="dropdown-item" href="/admin/memberlist?keyword=테스트">테스트</a>
				</div>
			</div>
		</div>
	</div>

	<div class="table-responsive">
		<table class="table table-striped table-sm">
			<thead>

				<tr>
					
					<th>이름</th>
					<th>부서</th>
					<th>직급</th>
					<th>사번</th>
					<th>입사일</th>
					<th>로그인카운트</th>
					<th>사원정보수정</th>
					<th>퇴사처리</th>
					<th>초기화</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="memberDTO">
					 <input type="hidden" class="delid" value="${memberDTO.id }"/>
	
					<tr>
						
						<td>${memberDTO.name }</td>
						<td>${memberDTO.division }</td>
						<td>${memberDTO.position }</td>
						<td>${memberDTO.tel }</td>
						<td>${memberDTO.cdate }</td>
						<td>${memberDTO.logincount }</td>
						<td>						
						<button id="modifybtn" class="btn btn-sm btn-outline-secondary" data-memurl="/admin/adminModifyForm/${memberDTO.id }">수정</button>
						</td>
						<td>
						<button class="deletebtn btn-sm btn-outline-secondary" data-toggle="modal" data-id="${memberDTO.id }">퇴사</button>
						</td>
						<td>
						<button id="clearbtn" class="btn btn-sm btn-outline-secondary" data-memurl="/admin/clear/${memberDTO.id }">잠금해제</button>
						</td>
					</tr>
					
				</c:forEach> 
			</tbody>
		</table>
	</div>
	
	
	


	<ul class="pagination pagination-sm justify-content-center">

		<c:if test="${pc.prev }">
			<li class="page-item"><a class="page-link"
				href="/admin/memberlist?${pc.makeSearchURL(1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">처음</a></li>
			<li class="page-item"><a class="page-link"
				href="/admin/memberlist?${pc.makeSearchURL(pc.startPage - 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">이전</a></li>
		</c:if>



		<c:forEach begin="${pc.startPage }" end="${pc.endPage }" var="pageNum">
			<!-- 현재페이지와 요청페이지가 다르면 -->
			<c:if test="${pc.recordCriteria.reqPage != pageNum }">
				<li class="page-item"><a class="page-link"
					href="/admin/memberlist?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
				</li>
			</c:if>

			<!-- 현재페이지와 요청'페이지가 같으면 강조표시 -->
			<c:if test="${pc.recordCriteria.reqPage == pageNum }">
				<li class="page-item active"><a class="page-link"
					href="/admin/memberlist?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
				</li>
			</c:if>
			<!— 요청페이지와 현재페이지가 같으면 강조표시 —>
		</c:forEach>


		<c:if test="${pc.next }">
			<li class="page-item"><a class="page-link"
				href="/admin/memberlist?${pc.makeSearchURL(pc.endPage + 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">다음</a></li>
			<li class="page-item"><a class="page-link"
				href="/admin/memberlist?${pc.makeSearchURL(pc.finalEndPage) }&searchType=${pc.searchType}&keyword=${pc.keyword}">마지막</a></li>
		</c:if>
	</ul>
 
	</main>
	<!-- delete Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">퇴사처리</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					       	퇴사처리시 복구가 불가능합니다. 계속 진행 하시겠습니까?
					       	
					 

					      </div>
					      <div class="modal-footer">
					      
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					        <button type="button" class="btn btn-primary" id="delbtn">예</button>
					      </div>
					    </div>
					  </div>
					</div>

</body>
</html>