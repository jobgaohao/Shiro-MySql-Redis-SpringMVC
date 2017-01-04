
so.init(function(){
	//角色分配初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
	
	
	//清空角色权限
	so.id('deleteAll').on("CLICK",function(){
		var checkeds = $('[check=box]:checked');
		if(!checkeds.length){
			return layer.msg('请选择要清除的角色。',so.default),!0;
		}
		var array = [];
		checkeds.each(function(){
			array.push(this.value);
		});
		return deleteById(array);
	});

});

/**
 * 根据角色ID删除权限
 */
var deleteById=function(ids){
	var index = layer.confirm("确定清除这"+ ids.length +"个角色的权限？",function(){
		var load = layer.load();
		$.post('../permission/clearPermissionByRoleIds.shtml',{roleIds:ids.join(',')},function(result){
			layer.close(load);
			if(result && result.status != 200){
				return layer.msg(result.message,so.default),!0;
			}else{
				layer.msg(result.message);
				setTimeout(function(){
					$('#formId').submit();
				},1000);
			}
		},'json');
		layer.close(index);
	});
}

/**
 * 保存选中权限
 */
var selectPermission=function(){
	var checked = $("#boxRoleForm  :checked");
	var ids=[],names=[];
	$.each(checked,function(){
		ids.push(this.id);
		names.push($.trim($(this).attr('name')));
	});
	var index = layer.confirm("确定操作？",function(){
		var load = layer.load();
		$.post('../permission/addPermission2Role.shtml',{ids:ids.join(','),roleId:$('#selectRoleId').val()},function(result){
			layer.close(load);
			if(result && result.status != 200){
				return layer.msg(result.message,so.default),!1;
			}
			layer.msg('添加成功。');
			setTimeout(function(){
				$('#formId').submit();
			},1000);
		},'json');
	});
}

/**
*根据角色ID选择权限，分配权限操作。
*/
var selectPermissionById=function(id){
	var load = layer.load();
	$.post("../permission/selectPermissionById.shtml",{id:id},function(result){
		layer.close(load);
		if(result && result.length){
			var html =[];
			html.push('<div class="checkbox"><label><input type="checkbox" selectAllBox="">全选</label></div>');
			$.each(result,function(){
				html.push("<div class='checkbox'><label>");
				html.push("<input type='checkbox' selectBox=''   id='");
				html.push(this.id);
				html.push("'");
				if(this.check){
					html.push(" checked='checked'");
				}
				html.push("name='");
				html.push(this.name);
				html.push("'/>");
				html.push(this.name);
				html.push('</label></div>');
			});
			
			//初始化选择权限==》全选。
			return so.id('boxRoleForm').html(html.join('')),
			so.checkBoxInit('[selectAllBox]','[selectBox]'),
			$('#selectPermission').modal(),
			$('#selectRoleId').val(id),!1;
		}else{
			return layer.msg('没有获取到权限数据，请先添加权限数据。',so.default);
		}
	},'json');
}
