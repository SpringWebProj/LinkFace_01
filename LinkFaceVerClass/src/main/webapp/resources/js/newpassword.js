$(document).ready(function(){
	
	let dataInputCheck = true;
		
	$(".btnSubmit").on("click",function(e){
		
		e.preventDefault();
		
		$('.newPasswordForm input').each(function(){
			if($(this).val().trim() == '') {
			   dataInputCheck = false;
			}
		})
		if(!dataInputCheck){
			alert("데이터 입력 필수");
			dataInputCheck = true;
			return;
		}
		else if($("input[name='password']").val() !=
			$("input[name='passwordCheck']").val()){
			$("input[name='password']").focus();
			alert("비밀번호 체크 필요");
			return;
		}

		let passwordData = {
			
			key : $('.newPasswordForm').data("key"),
			password : $(`input[name='password']`).val(),
			passwordCheck : $(`input[name='passwordCheck']`).val(),
			
		}
		
		
		$.ajax({
		    url: '/newpassword',
		    type: 'post',
		    data : JSON.stringify(passwordData),
			contentType : "application/json; charset=UTF-8",
			beforeSend : function(xmlHttpRequest){   
				xmlHttpRequest.setRequestHeader(csrfHeader,
						csrfToken);
            },
		    success: function (data){
		    	alert(data);
		 		window.location.href = "/login";

		    },
		    error: function (error){
		     	alert(error);
		     	window.location.href = "/main";
		    }
		});
		
		
	})
	
		
	
	
})