
$(function(){
	
	$("a[v=onlineDetails]").on('click',function(){
		var self = $(this);
		var text = $.trim(self.text());
		var index = layer.confirm("确定"+ text +"？",function(){
			changeSessionStatus(self.attr('sessionId'),self.attr('status'),self);
			layer.close(index);
		});
	});
});

/**
 * 改变状态
 */
var changeSessionStatus=function(sessionIds,status,self){
	status = !parseInt(status);
	//loading
	var load = layer.load();
	$.post("../member/changeSessionStatus.shtml",{status:status,sessionIds:sessionIds},function(result){
		layer.close(load);
		if(result && result.status == 200){
			return self.text(result.sessionStatusText),
						self.attr('status',result.sessionStatus),
							self.parent().prev().text(result.sessionStatusTextTd);
							layer.msg('操作成功'),!1;
		}else{
			return layer.msg(result.message,function(){}),!1;
		}		
	},'json');
}
