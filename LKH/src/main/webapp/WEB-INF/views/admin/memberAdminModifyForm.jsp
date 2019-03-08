<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!--  Bootstrap 시작-->
<link rel="stylesheet"
   href="/resources/bootstrap-4.2.1/css/bootstrap.css" />
<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/js/bootstrap.js"></script>
<!-- Bootstrap -->
  

<style>
   .errmsg, pwErr {color: #f00;}
</style>            
      
<script>
   $(function(){
      
      //유효성체크 오류시 에러표시 css적용 
      $(".errmsg").each(function(idx){
         if($(this).text().length > 0){
             $(this).prev().removeClass("is-valid").addClass("is-invalid");
         }
      });   
      
      //비밀번호 확인
      $("#pw,#pwchk").on("keyup",function(){
         if($("#pw").val() != $("#pwchk").val()) {
            console.log($(this).val());
            $(".pwErr").text('비밀번호가 틀립니다!');
            $(this).focus();
         }else{
            $(".pwErr").text('');
         }
      })
      
      // 수정버튼
      $("#modBtn").on("click",function(e){
         e.preventDefault();   
         $("form").submit();
      });
      
      // 수정초기화버튼
      $("#modClearBtn").on("click",function(e){
         e.preventDefault();   
         $("form").each(function(){
            this.reset();
         });
      });
      
      // 수정취소버튼
      $("#modCancelBtn").on("click",function(e){
         e.preventDefault();   
         location.href="/login/loginForm";
      });
      
      // 회원목록버튼
      $("#listBtn").on("click",function(e){
         e.preventDefault();   
         location.href="/member/memberList";
      });      
      
   });
</script>
   <div class="container">
   <div class="row">
   <div class="col offset-3">
   <form:form modelAttribute="mdto" action="${pageContext.request.contextPath }/member/adminModify">
  
  <h2>[회원정보수정:관리자]</h2></br>
  
  <div class="form-group row">
    <form:label path="id" class="col-sm-2 col-form-label col-form-label-sm">아이디</form:label>
    <form:input type="text" path="id" class="col-sm-3 form-control form-control-sm is-valid" placeholder="아이디를 입력하세요!" required="required"   />
       <form:errors path="id" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    
    <form:label path="division" class="col-sm-2 col-form-label col-form-label-sm">부서</form:label>
         <form:select path="division" class="col-sm-3 form-control form-control-sm is-valid">
       <option value="" >--부서--</option>          
       <form:options path="division" items="${dCodes }" itemLabel="label" itemValue="code" />
       </form:select>   
 
  </div>
  
  <div class="form-group row">
    <form:label path="position" class="col-sm-2 col-form-label col-form-label-sm">직급</form:label>
     <form:select path="position" class="col-sm-3 form-control form-control-sm is-valid">
       <option value="" >--직급--</option>          
       <form:options path="position" items="${pCodes }" itemLabel="label" itemValue="code" />
       </form:select>   
  </div>
  
  <div class="form-group row ">
    <form:label path="pw" class="col-sm-2 col-form-label col-form-label-sm">비밀번호</form:label>
    <form:input type="password" path="pw" class="col-sm-3 form-control form-control-sm is-valid" placeholder="비밀번호를 입력하세요!" required="required"/>
      <form:errors path="pw" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    <label for="pwchk" class="col-sm-2 col-form-label col-form-label-sm">비밀번호확인</label>
    <input type="password" id="pwchk" class="col-sm-3 form-control form-control-sm is-valid" placeholder="비밀번호를 입력하세요!" required/>
      <span class="col-sm-5 pwErr"></span>
  </div> 
    
    <div class="form-group row">
    <form:label path="snumber" class="col-sm-2 col-form-label col-form-label-sm">주민등록번호</form:label>
    <form:input type="text" path="snumber" class="col-sm-3 form-control form-control-sm is-valid"  required="required"   />
       <form:errors path="snumber" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
    <div class="form-group row">
    <form:label path="memberno" class="col-sm-2 col-form-label col-form-label-sm">사번</form:label>
    <form:input type="text" path="memberno" class="col-sm-3 form-control form-control-sm is-valid"  required="required"   />
       <form:errors path="memberno" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    <form:label path="tel" class="col-sm-2 col-form-label col-form-label-sm">전화번호</form:label>
     <form:input type="text" path="tel" class="col-sm-3 form-control form-control-sm is-valid" placeholder="전화번호를 입력하세요 ex)010-1234-5678" required="true"/>
      <form:errors path="tel" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    <form:label path="address" class="col-sm-2 col-form-label col-form-label-sm">주소</form:label>
     <form:input type="text" path="address" class="col-sm-3 form-control form-control-sm is-valid" placeholder="전화번호를 입력하세요 ex)010-1234-5678" required="true"/>
      <form:errors path="address" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    <form:label path="cdate" class="col-sm-2 col-form-label col-form-label-sm">생성일</form:label>    
    <form:input type="text" path="cdate" class="col-sm-3 form-control form-control-sm is-valid" placeholder="별칭을 입력하세요!" required="true"/>
      <form:errors path="cdate" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
  
  <div class="form-group row">
    <form:label path="logincount" class="col-sm-2 col-form-label col-form-label-sm">로그인카운트</form:label>    
    <form:input type="text" path="logincount" class="col-sm-3 form-control form-control-sm is-valid" placeholder="별칭을 입력하세요!" required="true"/>
      <form:errors path="logincount" cssClass="col-sm-5 errmsg"></form:errors>
  </div>
   
  <div class="form-group row">
    <form:label path="name" class="col-sm-2 col-form-label col-form-label-sm">이름</form:label>    
    <form:input type="text" path="name" class="col-sm-3 form-control form-control-sm is-valid" placeholder="별칭을 입력하세요!" required="true"/>
      <form:errors path="name" cssClass="col-sm-5 errmsg"></form:errors>
  </div>

  
       
  <div class="form-group row" >
    <div class="col-sm-2"></div>
      <div class="col-sm-4">
      <button class="btn btn-primary" type="submit" id="modBtn">수정</button>
      <button class="btn btn-primary" type="submit" id="modClearBtn">초기화</button>
      <button class="btn btn-primary" type="submit" id="modCancelBtn">취소</button>

      </div>
      <div class="col-sm-2" ></div>
   </div>
</form:form>
</div></div></div>

     