so.init(function(){
	//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
	
	/**
	 * 删除所有
	 */
	$("#deleteAll").on("click",function(){		
		deleteAll();
	});
	
	
	/**
	 * 添加权限
	 */
	$("#addFitment").on("click",function(){
		$('#addFitmentDiv').modal();
	});

});


/**
 * 删除所有
 */
var deleteAll=function(){
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
	
	
	layer.confirm("确定这"+array.length+"个日记？",function(){
		var load=layer.load();
		$.ajax({
		 	url:"../fitment/delFitment.action",
		 	type: 'POST',
		 	data:param,
		 	dataType: 'JSON',	 
		 	success: function(data){
		 		if(data.result=="1"){
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