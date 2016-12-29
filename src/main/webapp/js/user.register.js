$(function() {
	
	$("#vcode").on("click",'img',function(){
		vcodeChange(this);
	});
	
	$('.register').bind("click",function(){		
		doRegister();
	});
	
	
	$("form :text,form :password").keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
	
    //跳转
    $("#login").click(function(){
    	window.location.href="login.shtml";
    });
    
    $("#register").click(function(){
    	window.location.href="register.shtml";
    });
	
});


/**
 * 验证码切换
 */
var vcodeChange=function(imgCode){
	var i = new Image();
	i.src = '../open/getGifCode.shtml?'  + Math.random();
	$(i).replaceAll(imgCode);
}


var doRegister=function(){
	var form = $('#_form');
	var error= form.find(".error");
	var tops = ['27px','96px','165px','235px','304px','372px'];
	var inputs = $("form :text,form :password");
	for(var i=0;i<inputs.length;i++){
		var self = $(inputs[i]);
		if($.trim(self.val()) == ''){
			 error.fadeOut('fast', function(){
           		 $(this).css('top', tops[i]);
            });
            error.fadeIn('fast', function(){
               self.focus();
            });
            return !1;
		}
	}
	var re_password =$.trim($("#re_password").val());
	var password =$.trim($("#password").val());
	if(password != re_password){
		return layer.msg('2次密码输出不一样！',function(){}),!1;
	}
	
	if($('[name=vcode]').val().length !=4){
		return layer.msg('验证码的长度为4位！',function(){}),!1;
	}
	var load = layer.load();
	var formData=$("#_form").serialize();
		
	$.ajax({
	 	url:"../u/subRegister.shtml",
	 	type: 'POST',
	 	data: formData,
	 	dataType: 'JSON',	 
	 	success: function(result){
	 		layer.close(load);
			if(result && result.status!= 200){
				return layer.msg(result.message,function(){}),!1;
			}else{
				layer.msg('注册成功！' );
				window.location.href=result.back_url || "../";
			}
	 	}
	});
}