<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="../index_top.jsp" flush="false" />

<style>
h1 {
	font-size:3em; 
	font-family:'Roboto Condensed';
}
</style>
<script>
	$(function() {

		$("#write").on("click", function(e) {
			e.preventDefault();
			/* 		 if ${sessionScope.user} == null {
			 location.href="/login/loginForm";
			 return; 		 }*/

			/*  		 <c:if test="${sessionScope.user eq null}">
				location.href="/login/loginForm";
				return;
			</c:if>  */

			location.href = "${pageContext.request.contextPath }/gboard/write";

			/* 		 $(location).attr('/bbs/write_form'); */
		});

		//검색버튼 클릭
		$("#btn1").on("click", function() {

			if ($("#keyword").val().trim().length == 0) {
				alert("검색어를 입력하세요!");
				return;

			}
			$("form").submit();

		});

	});
</script>

<div class="block-container">
	<div class="container">

		<div class="text-center mb-4">
			<h1>Q & A</h1>

		</div>

		<div class="table-responsive">
			<table class="table table-sm">
				<thead class="thead-light">
					<tr>
						<th scope="col">글번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="gbbsdto">
						<tr>
							<td>${gbbsdto.num }</td>
							<td><a
								href="/gboard/view?num=${gbbsdto.num }&${pc.makeSearchURL(pc.recordCriteria.reqPage)} "
								class="text-decoration-none text-reset text-danger">${gbbsdto.title }</a>
							</td>
							<td>${gbbsdto.id }</td>
							<td>${gbbsdto.cdate }</td>
							<td>${gbbsdto.hit }</td>
						</tr>
					</c:forEach>
					<tr style="background-color: white">
						<td colspan="10" align="right">
							<button id="write" type="button"
								class="btn btn-sm btn-outline-dark">글쓰기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<ul class="pagination pagination-sm justify-content-center">

			<c:if test="${pc.prev }">
				<li class="page-item"><a class="page-link"
					href="/gboard/list?${pc.makeSearchURL(1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">처음</a></li>
				<li class="page-item"><a class="page-link"
					href="/gboard/list?${pc.makeSearchURL(pc.startPage - 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">이전</a></li>
			</c:if>



			<c:forEach begin="${pc.startPage }" end="${pc.endPage }"
				var="pageNum">
				<!-- 현재페이지와 요청페이지가 다르면 -->
				<c:if test="${pc.recordCriteria.reqPage != pageNum }">
					<li class="page-item"><a class="page-link"
						href="/gboard/list?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
					</li>
				</c:if>

				<!-- 현재페이지와 요청'페이지가 같으면 강조표시 -->
				<c:if test="${pc.recordCriteria.reqPage == pageNum }">
					<li class="page-item active"><a class="page-link"
						href="/gboard/list?${pc.makeSearchURL(pageNum) }&searchType=${pc.searchType}&keyword=${pc.keyword}">${pageNum }</a>
					</li>
				</c:if>
				<!-- 요청페이지와 현재페이지가 같으면 강조표시 -->
			</c:forEach>


			<c:if test="${pc.next }">
				<li class="page-item"><a class="page-link"
					href="/gboard/list?${pc.makeSearchURL(pc.endPage + 1) }&searchType=${pc.searchType}&keyword=${pc.keyword}">다음</a></li>
				<li class="page-item"><a class="page-link"
					href="/gboard/list?${pc.makeSearchURL(pc.finalEndPage) }&searchType=${pc.searchType}&keyword=${pc.keyword}">마지막</a></li>
			</c:if>
		</ul>

		<div class="btn-toolbar pt-4 mb-2 mb-md-0 justify-content-center">
			<div class="btn-group mr-2">
				<form action="/gboard/list" method="post">
					<div class="row">
						<label class="col-sm-1 col-form-label col-form-label-sm px-0 mx-1"
							for="searchType"><i class="fas fa-search fa-lg pt-1 pl-1"></i></label>
						<select class="col-sm-4 custon-select custom-select-sm px-1 mr-1"
							name="searchType" id="searchType">
							<option value="TC"
								<c:out value="${pc.searchType == 'TC' ? 'selected' :'' }"/>>제목+내용</option>
							<option value="T"
								<c:out value="${pc.searchType == 'T' ? 'selected' :'' }"/>>제목</option>
							<option value="C"
								<c:out value="${pc.searchType == 'C' ? 'selected' :'' }"/>>내용</option>
							<option value="N"
								<c:out value="${pc.searchType == 'N' ? 'selected' :'' }"/>>이름</option>
						</select> <input class="col-sm form-control form-control-sm px-1 mr-1"
							type="search" name="keyword" id="keyword" value="${pc.keyword }">
						<input class="btn btn-sm btn-outline-primary px-1 mx-0"
							class="button" type="button" value="검색" id="searchbtn">
					</div>
				</form>
			</div>

		</div>
	</div>
</div>
</body>
</html>