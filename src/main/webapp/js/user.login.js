$(function() {
	
	/**
	 * 登出校验
	 */
	doKickout();	
	
	/**
	 * 注册
	 */
	$("#register").bind("click",function(){		
		doRegister();
	});
	
	/**
	 * 错误提示
	 */
	$(".page-container form .username, .page-container form .password").bind("keyup",function(){
		$(this).parent().find('.error').fadeOut('fast');
	});
	
	/**
	 * 回车登录
	 */
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){ 
			$('#login').click();
		}
	}; 
	
	/**
	 * 登录
	 */
	 $('#login').bind("click",function(){
		 doLogin();
	 });
	
});

/**
 * 登录
 */
var doLogin=function(){
	 var username =$.trim($('.username').val());
     var password =$.trim($('.password').val());
     if(username == '') {
         $('.error').fadeOut('fast', function(){
             $('.error').css('top', '27px').show();
         });
         $('.error').fadeIn('fast', function(){
             $('.username').focus();
         });
         return false;
     }
     if(password == '') {
         $('.error').fadeOut('fast', function(){
             $('.error').css('top', '96px').show();
         });
         $(this).find('.error').fadeIn('fast', function(){
             $('.password').focus();
         });
         return false;
     }
     var pswd = MD5(username +"#" + password),
     	data = {pswd:pswd,email:username,rememberMe:$("#rememberMe").is(':checked')};
     var load = layer.load();
     $.ajax({
		 	url:"../u/submitLogin.shtml",
		 	type: 'POST',
		 	data: data,
		 	dataType: 'JSON',	 
		 	success: function(result){
		 		layer.close(load);
		 		if(result && result.status != 200){
		 			layer.msg(result.message,function(){});
		 			$('.password').val('');
		 			return;
		 		}else{
		 			layer.msg('登录成功！');
		 			setTimeout(function(){
		 				//登录返回
			    			window.location.href= result.back_url || "${basePath}/";
		 			},1000)
		 		}
		 	}
		 });   
};

/**
 * 注册
 */
var doRegister=function(){
	window.location.href="register.shtml";
}

/**
 * 提出验证
 */
var doKickout=function(){
	try{
		var _href = window.location.href+"";
		if(_href && _href.indexOf('?kickout')!=-1){
			layer.msg('您已经被踢出，请重新登录！');
		}
	}catch(e){
		
	}
}

//换种方式获取，之前的方式在不同的环境下，有问题
var baseUrl = $("script[baseUrl]").attr('baseUrl');
/**退出*/
var logout=function(){
	var load = layer.load();
	$.getJSON(baseUrl + '/u/logout.shtml',{},function(result){
		layer.close(load);
		if(result && result.status == 200){
			$(".qqlogin").html('').next('ul').remove();
			layer.msg('退出成功');
			window.location.reload(true);
			return !1;
		}else{
			layer.msg('退出失败，重试！');
		}
	});
}
