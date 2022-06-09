<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="jqueryandcsrf.jsp"/>
</head>
<body>
	<form id="singup-form" action="singup" method="post">
		<div id="id">
			<label>ID</label>
			<input type="text" name="id" maxlength="20"/>
			<button type="button" class="id-duplicate-check">
			ID duplicate check</button>
			<div class="result">
			</div>
		</div>
		<div>
			<label>PASSWORD</label>
			<input type="password" name="password" maxlength="20"/>
		</div>
		<div>
			<label>PASSWORD Check</label>
			<input type="password" name="passwordCheck" maxlength="20"/>
		</div>
		<div id="email">
			<label>EMAIL</label>
			<input type="email" name="email" maxlength="20"/>
			<button type="button" class="email-duplicate-check"
			>EMAIL duplicate check</button>
			<div class="result">
			</div>	
		</div>
		<div>
			<label>NAME</label>
			<input type="text" name="name" maxlength="20">
		</div>
		<button class="singUp">submit</button>
	</form>
	
	<script>
		
		$(document).ready(function(){
			
			let idCheck = false;
			let emailCheck = false;
			let dataInputCheck = true;
			let regExp =
				/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			// Id Check
			$(".id-duplicate-check").on("click",function(e){
				let idValue = $('input[name=id]').val();
				let checkObj = { id : idValue };
				
				$.ajax({
				    url: '/duplicatecheck',
				    type: 'post',
				    data : JSON.stringify(checkObj),
					contentType : "application/json; charset=UTF-8",
					beforeSend : function(xmlHttpRequest){   
						xmlHttpRequest.setRequestHeader(csrfHeader,
								csrfToken);
		            },
				    success: function (data){
				    	data ?
					    		$("#id .result").html("사옹 가능한 ID 입니다") :
					    		$("#id .result").html("중복 ID 입니다");
				    	idCheck = data;	
				    },
				    error: function (error){
				        alert("에러");
				    }
				});
			})
			
			// Email Check
			$(".email-duplicate-check").on("click",function(e){
		
				let emailValue = $('input[name=email]').val();
				let checkObj = { email : emailValue };
				
				$.ajax({
				    url: '/duplicatecheck',
				    type: 'post',
				    data : JSON.stringify(checkObj),
				    beforeSend : function(xmlHttpRequest){   
						xmlHttpRequest.setRequestHeader(csrfHeader,
								csrfToken);
		            },
					contentType : "application/json; charset=UTF-8",
				    success: function (data){
				    	data ?
					    		$("#email .result").html("등록 가능한 Email 입니다") :
					    		$("#email .result").html("중복 Email 입니다");
				    	emailCheck = data;
				    },
				    error: function (error){
				        alert("에러");
				    }
				});
			})
			
			// 중복체크 후 변경 이벤트 감지
			$("input[name='id']")
				.on("propertychange change keyup paste input", function(){
				idCheck = false;
				$("#id .result").html("버튼을 눌러 중복체크를 해주세요");
			});
			$("input[name='email']")
				.on("propertychange change keyup paste input", function(){
				emailCheck = false; 
				$("#email .result").html("버튼을 눌러 중복체크를 해주세요");
			});
			
			// 제출
			$(".singUp").on("click",function(e){
				
				e.preventDefault();
				
				// 데이터 없음 검사
				$('#singup-form input').each(function(){
					if($(this).val().trim() == '') {
	                   dataInputCheck = false;
	                }
				})
				if(!dataInputCheck){
					alert("사용자 데이터 입력 필수");
					dataInputCheck = true;
					return;
				}
				// 아이디 중복체크
				else if(!idCheck){
					$("input[name='id']").focus();
					alert("아이디 중복체크 필요");
					return;
				}
				// 이메일 중복체크
				else if(!emailCheck){
					$("input[name='email']").focus();
					alert("이메일 중복체크 필요");
					return;
				}
				// 이메일 유효성 검사
				else if($("input[name='email']").val().match(regExp) == null){
					alert("올바른 이메일 형식이 아닙니다");
					return;
				}
				// 비밀번호 재확인 검사
				else if($("input[name='password']").val() !=
					$("input[name='passwordCheck']").val()){
					$("input[name='password']").focus();
					alert("비밀번호 체크 필요");
					return;
				}

				let newUser = {
						id : $(`input[name='id']`).val(),
						password : $(`input[name='password']`).val(),
						passwordCheck : $(`input[name='passwordCheck']`).val(),
						email : $(`input[name='email']`).val(),
						name : $(`input[name='name']`).val()
				}

				$.ajax({
				    url: '/singup',
				    type: 'post',
				    data : JSON.stringify(newUser),
					contentType : "application/json; charset=UTF-8",
					beforeSend : function(xmlHttpRequest){   
						xmlHttpRequest.setRequestHeader(csrfHeader,
								csrfToken);
		            },
				    success: function (data){
				    	alert(data + '님의 계정이 생성되었씁니다 등록하신 이메일로 인증링크를 발송했습니다.');
				    	window.location.href = "/main";
				    },
				    error: function (error){
				        alert("오류가 발생했습니다. 다시 시도해주세요");
				        window.location.href = "/singup";
				    }
				});
				
				
			})
			
		})
	</script>
</body>
</html>