$(document).ready(function(){
	
	  let dataInputCheck = true;
	  let regExp = 
	  		/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	  // 모달 띄우기
	  $(".btn_find").click(function(){
		$(".menu").show();
	    $(".modal").fadeIn();
	    $(".modal input").val("");
		
	  });
	  // 닫기 버튼을 누를 경우
	  $(".btn_close").click(function(){
	    $(".modal").hide();
		$(".modal_modal ").hide(); 
	  });
	  // esc 를 누를 경우
	  window.addEventListener("keyup", function(e){
		 if($(".modal").css("display") === "block" && e.key === "Escape"){
			 $(".modal").hide();
			 $(".modal_modal ").hide();
		 }
      });
      // 뒤로가기
	  $(".btn_back").click(function(){
		  $(".modal_modal ").hide();
		  $(".menu").show();
		  $(this).hide();
		  $(".modal_modal result").hide();
		  $(".modal input").val("");
		
	  });
	  // 아이디 찾기
	  $(".btn_findid").click(function(){
		  $(".modal_modal ").hide();
		  $(".findid").show();
		  $(".btn_back").show();
	  });
	  // 비밀번호 찾기
	  $(".btn_findpassword").click(function(){
		  $(".modal_modal ").hide();
		  $(".findpassword").show();
		  $(".btn_back").show();
	  });
	  
	  
	  $(".submit").on("click",function(e){
		
		e.preventDefault();
		
		
		$(this).siblings("div").children("input").each(function(){
			if($(this).val().trim() == ''){
				alert("사용자 데이터 입력 필수");
       			dataInputCheck = false;
       		}
		})
	  	if(!dataInputCheck){
			alert("사용자 데이터 입력 필수");
			dataInputCheck = true;
			return;
		}
		else if($(this).siblings("div").children("input[name='email']").val().match(regExp) == null){
			alert("올바른 이메일 형식이 아닙니다");
			return;
		}
		// 동적으로 객체 생성
		let type =  $(this).parent("form").data("type");
		let checkUser = {};
		$(this).siblings("div").children("input").each(function(){
			checkUser[$(this).attr("name")] = $(this).val();
		})
		
		$.ajax({
		    url: '/find',
		    type: 'post',
		    data : JSON.stringify({[type] :checkUser}),
			contentType : "application/json; charset=UTF-8",
			beforeSend : function(xmlHttpRequest){   
				xmlHttpRequest.setRequestHeader(csrfHeader,
						csrfToken);
            },
		    success: function (data){
				$(".modal_modal ").hide();
				$(".result").html(data);
				$(".result").show();
		    },
		    error: function (error){
				$(".modal_modal ").hide();
				$(".result").html(error);
				$(".result").show();
		    }
		});
		
		
	})
			
});