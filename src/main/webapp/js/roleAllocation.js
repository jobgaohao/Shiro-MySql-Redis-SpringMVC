so.init(function(){
		//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
	
	//全选
	so.id('deleteAll').on("click",function(){
		var checkeds = $('[check=box]:checked');
		if(!checkeds.length){
			return layer.msg('请选择要清除的用户。',so.default),!0;
		}
		var array = [];
		checkeds.each(function(){
			array.push(this.value);
		});
		return deleteById(array);
	});

});

/**
 * 删除用户角色
 */
function deleteById(ids){
	var index = layer.confirm("确定清除这"+ ids.length +"个用户的角色？",function(){
		var load = layer.load();
		$.post('../role/clearRoleByUserIds.shtml',{userIds:ids.join(',')},function(result){
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
 * 选择角色
 */
function selectRole(){
	var checked = $("#boxRoleForm  :checked");
	var ids=[],names=[];
	$.each(checked,function(){
		ids.push(this.id);
		names.push($.trim($(this).attr('name')));
	});
	var index = layer.confirm("确定操作？",function(){
		var load = layer.load();
		$.post('../role/addRole2User.shtml',{ids:ids.join(','),userId:$('#selectUserId').val()},function(result){
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

/*
*根据角色ID选择权限，分配权限操作。
*/
function selectRoleById(id){
	var load = layer.load();
	$.post("../role/selectRoleByUserId.shtml",{id:id},function(result){
		layer.close(load);
		if(result && result.length){
			var html =[];
			$.each(result,function(){
				html.push("<div class='checkbox'><label>");
				html.push("<input type='checkbox' id='");
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
			return so.id('boxRoleForm').html(html.join('')) & $('#selectRole').modal(),$('#selectUserId').val(id),!1;
		}else{
			return layer.msg('没有获取到用户数据，请先注册数据。',so.default);
		}
	},'json');
}