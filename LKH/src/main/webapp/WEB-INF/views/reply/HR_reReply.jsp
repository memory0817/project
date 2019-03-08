<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<head>
		<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
<title>spring framwork</title>
<!-- Bootstrap 시작 -->
<link rel="stylesheet" href="/resources/bootstrap-4.2.1/dist/css/bootstrap.css" />
<!-- google font -->
<link href="https://fonts.googleapis.com/css?family=Jua" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Patua+One" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&amp;subset=korean" rel="stylesheet">

<script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
<script src="/webjars/popper.js/1.14.6/dist/umd/popper.min.js"></script>
<script src="/resources/bootstrap-4.2.1/dist/js/bootstrap.js"></script>
<!-- Bootstrap 끝 -->
	
<!-- font awesome -->	
<link rel="stylesheet" href="/webjars/font-awesome/5.6.3/css/all.css" />
<style>
    textarea.autosize {
      /* min-height:1rem; */
      outline:none;      /* 클릭시 경계선 없애기 */
      width:100%;
      border:none;
      border-bottom:1px solid #000;
      overflow:hidden;   /* 스크롤바 앲애기 */
    }

    p, textarea{
      font-family: 'Noto Sans KR', sans-serif;
    }

    h6 ,.badge {
      font-family: 'Patua One','Jua';
    }

  </style>
<script>

		var num = ${hr_bbsDTO.num};		// 원글 번호
		var rereqPage = 1;	// 요청페이지
		
		var $l_id = "${user.name}/${user.division}/${user.position}"; 	//로그인 사용자 정보
 		
 		

    // 대댓글 작성 html코드 시작-----------------------------------------------
    var reply_str = '';
    reply_str += '<li data-rrnum="" data-form="write" class="list-group-item m-0 p-0">';
    reply_str += '  <div>';
    reply_str += '    <div class="row m-0 p-0">';
    reply_str += '      <div class="col-1"></div>';
    reply_str += '      <div class="col-11">';
    reply_str += '        <textarea class="autosize"></textarea></div>';
    reply_str += '      <div class="col-12 text-right">';
    reply_str += '        <a href="javascript:void(0)" class="rrclose  -light -sm">취소</a>';
    reply_str += '        <a href="javascript:void(0)" class="rrreply  -light -sm">댓글</a>';
    reply_str += '      </div>';
    reply_str += '    </div>';
    reply_str += '  </div>';
    reply_str += '</li>';
    //대댓글 작성 html코드 끝-----------------------------------------------

    // 대댓글 수정 html코드 시작-----------------------------------------------
    var modify_str = '';
    modify_str += '<li data-rrnum="" data-form="modify" class="list-group-item m-0 p-0">';
    modify_str += '  <div>';
    modify_str += '    <div class="row m-0 p-0">';
    modify_str += '      <div class="col-1"></div>';
    modify_str += '      <div class="col-11">';
    modify_str += '        <textarea class="autosize"></textarea></div>';
    modify_str += '      <div class="col-12 text-right">';
    modify_str += '        <a href="javascript:void(0)" class="rrclose  -light -sm">취소</a>';
    modify_str += '        <a href="javascript:void(0)" class="rrmodify  -light -sm">수정</a>';
    modify_str += '      </div>';
    modify_str += '    </div>';
    modify_str += '  </div>';
    modify_str += '</li>';
    //대댓글 수정 html코드 끝-----------------------------------------------
		
	$(function(){		
		
		//댓글목록 보내기
		replyList(rereqPage);
		
		//댓글 글자수 제한---------------------------------------------------
		var $rcontent = $("#rcontent");
		
		$rcontent.on("keyup",function(){
			var rcontent = $('#rcontent').val(); //댓글본문
			var cnt = rcontent.length;
			
			$('#count').html(cnt + '/100');
			
			if(cnt > 100){ 
				$rcontent.addClass("is-invalid");
				rcontent = rcontent.substring(rcontent.length-100);
				$("#rcontent").val(rcontent);
				
				$('#count').html(rcontent.length + '/100');
				return false;				
			}
			$rcontent.removeClass("is-invalid");				
		});
		$rcontent.keyup();
		//-----------------------------------------------------
		// 댓글 작성
		$('#replyBtn').on('click',function(){		// $('#replyBtn').click(function(){})
			var rid = $('#rid').text();								// 작성자 정보
			var loginid = $('#loginid').val();
			var rcontent = $('#rcontent').val();			// 댓글 본문
			var cnt = rcontent.trim().length;
			
/* 			console.log(rid);
			console.log(rcontent); */
			
			if(rcontent.trim().length == 0){
				$("#rcontent").attr("placeholder","댓글입력바랍니다.").addClass("is-invalid");
				$("#rcontent").focus();
				return false;
			}else if (cnt>100){
				alert('100자를 초과하였습니다.');
				return false;
			}
			
			// ajax 비동기 처리기술
			 $.ajax({
				type: "POST",		// post, get, put, delete
				url: "/hrrbbs/posts",
				headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
				dataType: "text",
				data: JSON.stringify({
					num: num,
					rid: rid,
					loginid: loginid,
					rcontent: rcontent
				}),
				success: function(result){
					// 댓글 목록 호출
					//console.log(result);
					replyList(rereqPage);
					
				},
				error: function(xhr,status,err){
					//console.log("실패"+e);
					console.log("xhr"+xhr);
					console.log("status"+status);
					console.log("err"+err);
				}
				
			});
			
			 //$#rcontent.attr("");
			 $("#rcontent").reset();
/* 			  $("form").each(function(){
				  this.reset();			  
			  }) */
			
/* 			 $('#rcontent').val(function(){
				 this.reset();
			 }); */
			
		});
			
		
	});

	//요청페이지에 대한 댓글 목록 가져오기
	function replyList(rereqPage){
		var str="";
		var rrid = "";				//부모댓글아이디
		var login_id = $('#loginid').val(); // 로그인 아이디dd
		$.ajax({
			type: "GET",
			url: "/hrrbbs/posts/map/"+num+"/"+rereqPage,
					dataType:"JSON",
					success: function(data,status,xhr){
 						/* console.log("data"+data);
						console.log("data.item"+data.item);
						console.log("data.pagecriteria"+data.pagecriteria); */
						str += "<ul class='list-gruop list-group-flush'>";
						
 						$.each(data.item,function(idx,rec){
 							/* console.log("idx"+idx); */
							console.log("rec: "+rec); 
							console.log("rec.grdto: "+rec.grdto); 
							console.log("rec.isdel: "+rec.isdel);
							
 							if(rec.hr_rdto != null){
								rrid = '@'+rec.hr_rdto.rid;						//보모댓글의 아이디
											//부모댓글의 아이디
							} 
 							if(rec.isdel == 'Y'){
 								//들여쓰기
								if( rec.rindent == 0){
								// 1step
									str += "<li data-rnum='"+rec.rnum+"' class='reList list-group-item m-0 p-0'>";
									str += "  <div>";
									str += "    <div class='row m-3 p-0'>";
									str += "      <div class='col-1'>";
									str += "      </div>";
									str += "      <div class='col-11'>";
									str += "        <p class='mb-0'><span class='mr-1 text-warning'>삭제된 댓글입니다.</span></p>";
									str += "      </div>";
									str += "    </div>";					
								}else{
									str += "<li data-rnum='"+rec.rnum+"' data-rrnum='"+rec.rrnum+"' class='reList list-group-item m-0 p-0'>";                   
									str += "  <div>";
									str += "    <div class='row m-3 p-0'>";
									str += "      <div class='col-1'></div>";
									str += "      <div class='col-1'>";
									str += "      </div>";
									str += "      <div class='col-10'>";
									str += "        <p class='mb-0'><span class='mr-1 text-warning'>삭제된 댓글입니다.</span></p>";
									str += "      </div>";
									str += "    </div>";		
								}
								str += "  </div>";
								str += "</li>";
 							}else{
 							// 들여쓰기
 								if(rec.rstep ==  0){
 									//1step
 								str += "<li data-rnum='"+rec.rnum+"' class='reList list-group-item m-0 p-0'>";
 								str += "<div><div class='row m-0 p-0'>";
 								str += "<div class='col-1'>";							
 								str += "<img src='/resources/images/basicicon.jpg' alt='로그인이미지' class='m-1 rounded-circle' style='width:45px;' />";
 								str += "</div>";
 								str += "<div class='col-11'>";
 								str += "<h6>"+rec.rid+" <small><i>"+rec.rcdate+"</i></small></h6>";
 								str += "<p><span class='mr-1 text-primary'></span>"+rec.rcontent+"</p>";
 								str += "</div>";
 								str += "</div>";
 								}else{
 									//2step
 								str += "<li data-rnum='"+rec.rnum+"'  data-rrnum='"+rec.rrnum+"' class='reList list-group-item m-0 p-0'>";
 								str += "<div><div class='row m-0 p-0'><div class='col-1'></div>";
 								str += "<div class='col-1'>";							
 								str += "<img src='/resources/images/basicicon.jpg' alt='로그인이미지' class='m-1 rounded-circle' style='width:45px;' />";
 								str += "</div>";
 								str += "<div class='col-10'>";
 								str += "<h6>"+rec.rid+" <small><i>"+rec.rcdate+"</i></small></h6>";					
 								str += "<p><span class='mr-1 text-primary'>"+rrid+"</span>"+rec.rcontent+"</p>";
 								str += "</div>";
 								str += "</div>";							
 								}
 								
 								
 								str += "<div class='row m-0 p-0'>";
 								str += "<div class='col-1'></div>";
 								str += "<div class='col-9 pl-4'>";
 								str += "<a href='javascript:void(0)' data-active='off' class='reReplyBtn badge badge-pill badge-dark px-2'>댓글</a>";
 								str += "</div>";
 							
 								
 								if(login_id == rec.loginid){ 
 	 								str += "<div class='col-2 m-0 p-0 text-right'>";
 	 								str += "<a href='javascript:void(0)' data-active='off' class='reModifyBtn badge badge-pill badge-dark mx-0 px-2'>수정</a>";
 	 								str += "<a href='javascript:void(0)' data-active='off' class='reDelBtn badge badge-pill badge-dark mx-0 px-2'>삭제</a>";
 	 								str += "</div>";
 	 	 						  } 
 								
 								str += "</div>";
 								str += "</div>";
 								str += "</li>";
 							}
							
						});
						str += "</ul>";
							// 댓글 목록 삽입
							$("#replyList").html(str);
						// 페이지 리스트 호출
						showPageList(data.pagecriteria);
						
						//댓글목록 이벤트처리
						doActionEvent();
						
					},
					error: function(xhr,status,err){
						console.log("xhr"+xhr);
						console.log("status"+status);
						console.log("err"+err);
					}
		});
		
		
		//페이지번호 클릭시 이벤트 처리
		$("#pageNumList").on("click","li a",function(e){
 			e.preventDefault(); 					//현재 이벤트의 기본 동작은 중단.
			e.stopImmediatePropagation(); //현재 이벤트가 상위 및 현재 레벨에 걸린 다른 이벤트도 동작하지않도록 중단.
//		e.stopPropagation(); 				//현재 이벤트가 상위로 전파되지 않도록 중단
			rereqPage = $(this).attr("href"); 
			replyList(rereqPage);	
			
		});	
		
	}
	
	// 페이징구현 
	function showPageList(pagecriteria){
		console.log(pagecriteria);
		
		var str="";
		
		//이전페이지여부
		if(pagecriteria.prev){		
		 //처음	
		 str += "<li class='page-item'>";
	   str += "<a class='page-link' href='1' tabindex='-1' aria-disabled='true'>◀</a></li>";
	   
	   //이전페이지
	   str += "<li class='page-item'><a class='page-link href='";
	   str +=  (pagecriteria.startPage-1);
	   str += "' tabindex='-1' aria-disabled='true'>◁</a></li>";
		}
		
		//페이지 1~10
		for( i=pagecriteria.startPage, end=pagecriteria.endPage; i<=end; i++){
		
			//현재페이지와 요청페이지가 다르면 
	   	if(pagecriteria.recordCriteria.reqPage != i) {           
	   		str += "<li class='page-item'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
	   	}else{	    	
				// 현재페이지와 요청페이지가 같으면 
	    	str += "<li class='page-item'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
	   	}
		}
		
		//다음페이지여부
		if(pagecriteria.next){
			//다음페이지
	    str += "<li class='page-item'><a class='page-link' href='";
	    str += (pagecriteria.endPage+1);
	    str += "'>▷</a></li>";		
	    
			//마지막
	    str += "<li class='page-item'><a class='page-link' href='";
	    str += (pagecriteria.finalEndPage);
	    str += "'>▷</a></li>";			
		}
	   
		//페이징 삽입
		$("#pageNumList").html(str);
		
	}
	
	function doActionEvent(){


	      //대댓글 작성 클릭시
	      $(".reReplyBtn").on("click",function(e){
	        console.log(e.target.classList.contains('badge-dark'));
	        var $liEle = $(this).parents('li');
	        var $data_rnum  =  $liEle.attr('data-rnum'); //댓글번호
	        var $data_rrnum =  $liEle.next().attr('data-rrnum'); //대댓글번호
	        var $data_form   = $liEle.next().attr('data-form'); //
	        

	        //대댓글 양식이 없을경우만 추가
	        if( $data_rnum != $data_rrnum) {
	          $liEle.after(reply_str);

	        //현재글의 대댓글은 있으나 댓글작성 중이 아닐경우
	        }else if($data_rnum == $data_rrnum && $data_form == null){
	        	$liEle.after(reply_str);
	        	
	        }else if($data_rnum == $data_rrnum && $data_form == 'write') {

	          var $textarea = $liEle.next().find('textarea');
	          var $tmp = $textarea.val();

	          $liEle.next().replaceWith(reply_str);
	          $liEle.next().find('textarea').val($tmp);

	        }else if($data_rnum == $data_rrnum && $data_form == 'modify'){

	          $liEle.next().replaceWith(reply_str);
	          $liEle.next().find('textarea').val($tmp);
	        }

	        $liEle.next().attr('data-rrnum',$liEle.attr('data-rnum'));

	        //대댓글 양식 높이 자동 조절
	        var $textareaEle = $("textarea.autosize");
	        $textareaEle.on('keyup focus', function(){
	          $(this)[0].style.height = 'auto';
	          $(this).css('height', $(this).prop('scrollHeight'));
	        //  console.log($(this).prop('scrollHeight'));
	        });
	        $textareaEle.trigger('focus');
	        //대댓글 양식 닫기
	        $(".rrclose").on("click",function(e){
	          e.stopImmediatePropagation();
	          $liEle.next().remove();
	        });
	        
		      //리댓글 등록 - num, rrnum, rid, rnickname, rcontent 
		      $(".rrreply").on('click', function(){
//		    	  console.log('리댓글등록');
	        var $li = $(this).parents('li');
	        var $rrnum = $li.attr('data-rrnum');
	        var $loginid = $("#loginid").val();
	        var $rcontent = $li.find('textarea').val();
	        var $l_id = $("#rrid").val(); 	//로그인 사용자 정보
	        console.log("$rcontent= "+$rcontent);
					$.ajax({
						type:"POST",
						url:"/hrrbbs/rposts",
						headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
						dataType:"text",
						data:JSON.stringify({
							num : num,
							rrnum : $rrnum ,
							rid : $l_id,
							loginid: $loginid,
							rcontent : $rcontent
						}),
						success:function(){
							replyList(rereqPage);
						},
						error:function(xhr,status,err){
							console.log("xhr"+xhr);
							console.log("status"+status);
							console.log("err"+err);				
						}
						
					});
		    	  
		      });
	        

	      });

	      //대댓글 수정 클릭시
	      $(".reModifyBtn").on("click",function(e){
	        //console.log(e.target.innerText);

	        var $liEle = $(this).parents('li');
	        var $data_rnum  =  $liEle.attr('data-rnum'); //댓글번호
	        var $data_rrnum =  $liEle.next().attr('data-rrnum'); //대댓글번호
	        var $data_form   = $liEle.next().attr('data-form'); //

	        //대댓글 수정 양식이 없을경우만 추가
	        if( $data_rnum != $data_rrnum){
	        	var $tmp = $liEle.find('p').text();
	        	$liEle.after(modify_str);
	        	 $liEle.next().find('textarea').val($tmp);
	        
        	//현재글의 대댓글은 있으나 댓글수정 중이 아닐경우
	        }else if( $data_rnum == $data_rrnum && $data_form == null) {
	        	var $tmp = $liEle.find('p').text();
	          
	          $liEle.after(modify_str);
	          $liEle.next().find('textarea').val($tmp);

	        }else if($data_rnum == $data_rrnum && $data_form == 'write') {
	          $tmp = $liEle.find('p').text();
	          $liEle.next().replaceWith(modify_str);
	          $liEle.next().find('textarea').val($tmp);

	        }else if($data_rnum == $data_rrnum && $data_form == 'modify') {

	          var $textarea = $liEle.next().find('textarea')
	          var $tmp = $textarea.val();
	          if($tmp.trim().length == 0 ){
	            $tmp = $liEle.find('p').text();
	          }
	          console.log($tmp);
	          $liEle.next().replaceWith(modify_str);
	          $liEle.next().find('textarea').val($tmp);
	        }
	        $liEle.next().attr('data-rrnum',$liEle.attr('data-rnum'));

	        //대댓글 수정 양식 높이 자동 조절
	        var $textareaEle = $("textarea.autosize");
	        $textareaEle.on('keyup focus', function(){
	          $(this)[0].style.height = 'auto';
	          $(this).css('height', $(this).prop('scrollHeight'));
	        });
	        $textareaEle.trigger('focus');

	        //대댓글 수정 양식 닫기
	        $(".rrclose").on("click",function(e){
	          e.stopImmediatePropagation();
	          $liEle.next().remove();
	        });
	        
	    		//수정 rnum, rcontent
		      $(".rrmodify").on('click', function(){
//		    	  console.log('수정');
						var $li = $(this).parents('li');
						var $rnum = $li.attr('data-rrnum');
				    var $rcontent = $li.find('textarea').val();
				    console.log($rcontent);
						
						$.ajax({
							type: "PUT",
							url:"/hrrbbs/posts",
							headers:{"Content-Type" : "application/json", "X-HTTP-Method-Override" : "POST"},
							dataType:"text",
							data:JSON.stringify({
								rnum : $rnum,
								rcontent : $rcontent
							}),
							success:function(){
								replyList(rereqPage);
							},
							error:function(xhr,status,err){
								console.log("xhr"+xhr);
								console.log("status"+status);
								console.log("err"+err);				
							}				
						
						});
						
						
		    	  
		      }); 
	    	
	      });


	      //댓글 삭제 클렉시
	      $('.reDelBtn').on('click', function(){
	    	  var $li = $(this).parents('li');
	    	  var rnum = $li.attr('data-rnum');	    		
//    	  console.log(rnum);
	    	  
	    	  
	    	  //자바스크립트에서 중괄호는 객체 대괄호는 배열
					$.ajax({
						type:"DELETE",
						url:"/hrrbbs/posts/"+rnum,
/* 						headers:{
							"Content-type":"application/json", //전송내용이 json포맷
							"X-HTTP-Method-Override":"POST",   //지원되지않는(낮은) 브라우저일때는 POST로 인식
							
						}, */
						dataType:"text",
						success:function(){
							replyList(rereqPage);
						},
						error:function(xhr,status,err){
							console.log("xhr"+xhr);
							console.log("status"+status);
							console.log("err"+err);							
						}
						
					});	    	  
	          //삭제toast 띄우기
	          $('#t-del-msg').toast('show');
	      });
	          

	    	
	    	//호감
	      $('.goodBtn').on('click', function(){
	   	  console.log('호감');
	    	  var $li = $(this).parents('li');
	    	  var rnum = $li.attr('data-rnum');	    		
//    	  console.log(rnum);
	    	  
	    	  
	    	  //자바스크립트에서 중괄호는 객체 대괄호는 배열
					$.ajax({
						type:"PUT",
						url:"/hrrbbs/posts/good/"+rnum,
						dataType:"text",
						success:function(){
							replyList(rereqPage);
						},
						error:function(xhr,status,err){
							console.log("xhr"+xhr);
							console.log("status"+status);
							console.log("err"+err);							
						}
						
					});	    	 
	    	  
	      });
	    	
	    	//비호감
	      $('.badBtn').on('click', function(){
	   	  console.log('비호감');
	    	  var $li = $(this).parents('li');
	    	  var rnum = $li.attr('data-rnum');	    		
//    	  console.log(rnum);
	    	  
	    	  
	    	  //자바스크립트에서 중괄호는 객체 대괄호는 배열
					$.ajax({
						type:"PUT",
						url:"/hrrbbs/posts/bad/"+rnum,
						dataType:"text",
						success:function(){
							replyList(rereqPage);
						},
						error:function(xhr,status,err){
							console.log("xhr"+xhr);
							console.log("status"+status);
							console.log("err"+err);							
						}
						
					});	   	    	  
	      });	    	

	      //tootip적용
	      $(function () {
	        $('[data-toggle="tooltip"]').tooltip()
	      });

		
		
	}
</script>


<div class="container">
<c:set var='user' value="${SPRING_SECURITY_CONTEXT.authentication.principal }"/>

<table class="table table-bordeless table-sm ">
   <colgroup>
    <col width="80%"/>
    <col width="10%"/>
   </colgroup>
    <tr>   
       <td><span id="rid" style="font-weight:bold;">작성자: ${user.name }/${user.division }/${user.position }</span></td>
       <td><input type="hidden" id="loginid" value="${user.id }" ></input></td>
    </tr> 
    </table>

    <table class="table table-bordeless table-sm ">
   <colgroup>
    <col width="100%"/> 
   </colgroup>    
    <tr>       
       <td>
          <textarea id="rcontent" class="form-control" rows="2" style= "height:150px;"
          placeholder="건전한 댓글 문화를 위해 운영원칙에 위배된 댓글은 삭제됩니다."></textarea>
          <p class="text-right" id="count" style="margin-bottom:0;" >(1/100)</p>
       </td>
       </table>
       
       <table class="table table-bordeless table-sm ">
   <colgroup>
    <col width="80%"/>
    <col width="10%"/>
   </colgroup>
       
 
       <tr><td></td>
    <td><button class=" btn btn-block btn-lg mt-2 btn-dark" id="replyBtn">등록</button></td>
       </tr> 

</table>

	<!-- 댓글 목록   -->
	<div id="replyList"></div>

	<!-- 댓글 페이징   -->
<div>
	<nav aria-label="Page navigation example">
	  <ul id="pageNumList" class="pagination pagination-sm justify-content-center"></ul>
	</nav>
 </div>


  <!-- toast -->
  <div role='alert' aria-live='assertive' aria-atomic='true' id='t-del-msg' class='toast' data-autohide='true' data-delay='2000'
  style='position: absolute; right: 50px; top: 50px;'>
  <div class='toast-header'>
    <img src='/resources/images/pinkheart.png' style='width:20px;' class='rounded mr-2' alt='알림메세지'>
    <!-- <i class='far fa-trash-alt text-danger fa-lg'></i> -->
    <strong class='mr-auto'>[삭제]</strong>
    <small class='invisible'>11 mins ago</small>
    <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>
      <span aria-hidden='true'>&times;</span>
    </button>
  </div>
  <div class='toast-body'>
    댓글이 삭제되었습니다.
  </div>
</div>



</div>