so.init(function(){
	//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
	//全选
	so.id('deleteAll').on('click',function(){
		var checkeds = $('[check=box]:checked');
		if(!checkeds.length){
			return layer.msg('请选择要删除的选项。',so.default),!0;
		}
		var array = [];
		checkeds.each(function(){
			array.push(this.value);
		});
		return _delete(array);
	});
});
//根据ID数组，删除
var _delete=function(ids){
	var index = layer.confirm("确定这"+ ids.length +"个用户？",function(){
		var load = layer.load();
		$.post('../member/deleteUserById.shtml',{ids:ids.join(',')},function(result){
			layer.close(load);
			if(result && result.status != 200){
				return layer.msg(result.message,so.default),!0;
			}else{
				layer.msg('删除成功');
				setTimeout(function(){
					$('#formId').submit();
				},1000);
			}
		},'json');
		layer.close(index);
	});
}

/*
*激活 | 禁止用户登录
*/
var forbidUserById=function(status,id){
	var text = status?'激活':'禁止';
	var index = layer.confirm("确定"+text+"这个用户？",function(){
		var load = layer.load();
		$.post('../member/forbidUserById.shtml',{status:status,id:id},function(result){
			layer.close(load);
			if(result && result.status != 200){
				return layer.msg(result.message,so.default),!0;
			}else{
				layer.msg(text +'成功');
				setTimeout(function(){
					$('#formId').submit();
				},1000);
			}
		},'json');
		layer.close(index);
	});
}
