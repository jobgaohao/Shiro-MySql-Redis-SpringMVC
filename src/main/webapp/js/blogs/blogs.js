so.init(function(){
	//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
			
	
	/**
	 * 删除所有
	 */
	$("#deleteAll").on("click",function(){	
		var param=getCheckBoxSel();
		doDeleteAll(param);
	});
	
	
	/**
	 * 抓取博客园
	 */
	$("#addBlogs").on("click",function(){		
		extractBlogs();
	});
});


/**
 * 抓取博客园首页
 */
var extractBlogs=function(){
	var load=layer.load();
	$.ajax({
	 	url:"../blogs/addBlogs.shtml",
	 	type: 'get',	 	
	 	success: function(data){
	 		layer.close(load);
	 		layer.msg(data.message);	 		
	 	}
	 });  
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







