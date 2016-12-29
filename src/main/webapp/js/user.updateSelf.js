$(function() {
	
	submitForm();
});
	
/**
 * 提交表单
 */
var submitForm=function(){
	var load;
	$("#formId").ajaxForm({
    	success:function (result){
    		layer.close(load);
    		if(result && result.status == 300){
    			layer.msg(result.message,function(){});
    			return !1;
    		}
    		if(result && result.status == 500){
    			layer.msg("操作失败！",function(){});
    			return !1;
    		}
    		layer.msg('操作成功！');
    	},
    	beforeSubmit:function(){
    		//判断参数
    		if($.trim($("#nickname").val()) == ''){
	    		layer.msg('昵称不能为空！',function(){});
	    		$("#nickname").parent().removeClass('has-success').addClass('has-error');
	    		return !1;
    		}else{
    			$("#nickname").parent().removeClass('has-error').addClass('has-success');
    		}
    		load = layer.load();
    	},
    	dataType:"json",
    	clearForm:false
	});

}
