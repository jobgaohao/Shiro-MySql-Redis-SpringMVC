so.init(function(){
	//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
	
	//初始化富文本编辑器
	var um = UM.getEditor('myEditor');
	
	//星星控件初始化
	$("#inputStar").rating({'showClear':false,'showCaption':true});
	
	/**
	 * 删除所有
	 */
	$("#deleteAll").on("click",function(){	
		var param=getCheckBoxSel();
		doDeleteAll(param);
	});
	
	
	/**
	 * 添加弹层
	 */
	$("#addFitment").on("click",function(){
		showModal();
		$("input").attr("value","");
		//设置星星为初始化状态
		$("#inputStar").rating("reset");
		//清空富文本编辑器
		UM.getEditor('myEditor').setContent("", false);
	});

	
	/**
	 * 新增、修改 装修日记
	 */
	$("#btnAddFitment").on("click",function(){		
		doAddModifyFitment();
	});
});


/**
 * 删除所有
 */
var doDeleteAll=function(param){	
	layer.confirm("确定删除选择的日记？",function(){
		var load=layer.load();
		$.ajax({
		 	url:"../fitment/delFitment.shtml",
		 	type: 'POST',
		 	data:param,
		 	dataType: 'JSON',	 
		 	success: function(data){
		 		layer.close(load);
		 		if(data.status=="200"){
					layer.msg("删除成功!");
					setTimeout(function(){
						$("#formId").submit();
					},1000);
				}
		 		else{
		 			layer.msg("删除失败!");
		 		}
		 	}
		 });  
	});
	
	
}

/**
 * 删除单个
 */
var deleteOne=function(id){
	var param={ids:id};
	doDeleteAll(param);
}

/**
 * 添加,修改 装修日记
 */
var doAddModifyFitment=function(){
	var title=$.trim($("#textTitle").val());
	var url=$.trim($("#textUrl").val());
	var tag=$.trim($("#textTag").val());
	var style=$.trim($("#textStyle").val());
	var remark=$.trim($("#textRemark").val());
	var content=UM.getEditor('myEditor').getContent();
	var star=$.trim($("#inputStar").val());
    var pkid=$.trim($("#hiFitmentPKID").val());
	
	if(title==""){
		layer.msg("请输入标题!");
	}
	
	var param={title:title,
			   url:url,
			   tag:tag,
			   style:style,
			   remark:remark,
			   content:content,
			   star:star,
			   id:pkid};
	
	var postURl="";
	var mes="";
	if(pkid!=""&&pkid>0){
		postURl="../fitment/modifyFitment.shtml";
	    mes="修改成功";
	}
	else{
		postURl="../fitment/addFitment.shtml";
	    mes="添加成功";
	}
	
	var load=layer.load();
	$.ajax({
	 	url:postURl,
	 	type: 'POST',
	 	data:param,
	 	dataType: 'JSON',	 
	 	success: function(data){
	 		layer.close(load);
	 		if(data.status=="200"){
				layer.msg(mes);
				setTimeout(function(){
					$("#formId").submit();
				},1000);
			}
	 		else{
	 			layer.msg(data.message);
	 		}
	 	}
	 });  
}

/**
 * 获取选择项
 */
var getCheckBoxSel=function(){
	var checkeds=$('[check=box]:checked');
	if(!checkeds.length){
		return layer.msg("请选择删除的选项!");
	}
	var array=[];
	var param;
	checkeds.each(function(){
		array.push(this.value);
	});
	param={ids:array.join(',')};
	return param;
}

/**
 * 获取单个装修日记
 */
var getOneFitment=function(pkid){
	var load=layer.load();
	var param={pkid:pkid};
	$.ajax({
	 	url:"../fitment/getFitment.shtml",
	 	type: 'POST',
	 	data:param,
	 	dataType: 'JSON',	 
	 	success: function(data){
	 		if(data.status=="200"){
				bindFitment(data.entity);
			}
	 		else{
	 			layer.msg(data.message);
	 		}
	 		layer.close(load);
	 	}
	 });  
}


/**
 * 绑定装修日记
 */
var bindFitment=function(entity){
	$("#textTitle").val(entity.title);
	$("#textUrl").val(entity.url);
	$("#textTag").val(entity.tag);
	$("#textStyle").val(entity.style);
	$("#textRemark").val(entity.remark);
	$("#textContent").val(entity.content);
	UM.getEditor('myEditor').setContent(entity.content, false);
	$("#hiFitmentPKID").val(entity.id);
	showModal();
	//设置选中星星个数
	$("#inputStar").rating("update",entity.star);
	
	
}

/**
 * 显示弹框
 */
var showModal=function(){
$('#addFitmentDiv').modal();
}