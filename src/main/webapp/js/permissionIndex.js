
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
		return deleteById(array);
	});
});

/**
 * 根据ID 删除权限
 */
var deleteById=function(ids){
	var index = layer.confirm("确定这"+ ids.length +"个权限？",function(){
		var load = layer.load();
		$.post('../permission/deletePermissionById.shtml',{ids:ids.join(',')},function(result){
			layer.close(load);
			if(result && result.status != 200){
				return layer.msg(result.message,so.default),!0;
			}else{
				layer.msg(result.resultMsg);
				setTimeout(function(){
					$('#formId').submit();
				},1000);
			}
		},'json');
		layer.close(index);
	});
}

/**
 * 添加权限
 */
var addPermission=function(){
	var name = $('#name').val(),
		url  = $('#url').val();
	if($.trim(name) == ''){
		return layer.msg('权限名称不能为空。',so.default),!1;
	}
	if($.trim(url) == ''){
		return layer.msg('权限Url不能为空。',so.default),!1;
	}

	var load = layer.load();
	$.post('../permission/addPermission.shtml',{name:name,url:url},function(result){
		layer.close(load);
		if(result && result.status != 200){
			return layer.msg(result.message,so.default),!1;
		}
		layer.msg('添加成功。');
		setTimeout(function(){
			$('#formId').submit();
		},1000);
	},'json');
}