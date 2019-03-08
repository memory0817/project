<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../main_top.jsp" flush="false" />
<jsp:include page="../main_sidebar.jsp" flush="false" />

<script>
	$(function() {/* 
		$("#write").on("click", function(e) {
			e.preventdefault;

			if($("#sessionId").val().length == 0){			
				if(confirm("로그인이 필요합니다. 로그인 하시겠습니까?")){
					location.href = "${pageContext.request.contextPath }/login/loginForm";
				}else{
					
				}
			}else{	
				location.href = "${pageContext.request.contextPath }/bbs/write";
			}
			 
	
		});
		 */
		$("#searchbtn").on("click", function(){
			if($("#keyword").val().trim().length == 0){
				alert('검색어를 입력하세요.');
				return;
			}
			else{
				var searchType = $("#searchType").val();
				var keyword = $("#keyword").val().trim();
				console.log(searchType);
				console.log(keyword);
				$("form").submit();
			}
		})
		
	});
</script>


<body>
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">공지사항</h1>
		<div class="btn-toolbar mb-2 mb-md-0">
			
		        <form  action="/member/notice" method="post">
		       	<div class="row">
		          <label class="col-sm-1 col-form-label col-form-label-sm px-0 mx-1" for="searchType">검색</label>
		          <select class="col-sm-4 custon-select custom-select-sm px-1 mr-1" name="searchType" id="searchType">
		            <option value="TC"
		            	<c:out value="${pc.searchType == 'TC' ? 'selected' :'' }"/>>제목+내용</option>
		            <option value="T"
		            	<c:out value="${pc.searchType == 'T' ? 'selected' :'' }"/>>제목</option>
		            <option value="C"
		            	<c:out value="${pc.searchType == 'C' ? 'selected' :'' }"/>>내용</option>
		          </select>
		          <input class="col-sm form-control form-control-sm px-1 mr-1" type="search" name="keyword" id="keyword" value = "${pc.keyword }" >
		          <input class="btn btn-sm btn-outline-primary px-1 mx-0" class="button" type="button" value="검색" id="searchbtn">
		        </div>
		        </form>
     		 
		</div>
	</div>

	<div class="table-responsive">
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





	<ul class="pagination pagination-sm justify-content-center">

		<c:if test="${pc.prev }">
			<li class="page-item"><a class="page-link"
				href="/member/notice?${pc.makeSearchURL(1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">처음</a></li>
			<li class="page-item"><a class="page-link"
				href="/member/notice?${pc.makeSearchURL(pc.startPage - 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">이전</a></li>
		</c:if>



		<c:forEach begin="${pc.startPage }" end="${pc.endPage }" var="pageNum">
			<!-- 현재페이지와 요청페이지가 다르면 -->
			<c:if test="${pc.recordCriteria.reqPage != pageNum }">
				<li class="page-item"><a class="page-link"
					href="/member/notice?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
				</li>
			</c:if>

			<!-- 현재페이지와 요청'페이지가 같으면 강조표시 -->
			<c:if test="${pc.recordCriteria.reqPage == pageNum }">
				<li class="page-item active"><a class="page-link"
					href="/member/notice?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
				</li>
			</c:if>
			<!-- 요청페이지와 현재페이지가 같으면 강조표시 -->
		</c:forEach>


		<c:if test="${pc.next }">
			<li class="page-item"><a class="page-link"
				href="/member/notice?${pc.makeSearchURL(pc.endPage + 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">다음</a></li>
			<li class="page-item"><a class="page-link"
				href="/member/notice?${pc.makeSearchURL(pc.finalEndPage) }&searchType=${pc.searchType}&keyword=${pc.keyword}">마지막</a></li>
		</c:if>
	</ul>

	</main>

</body>
</html>