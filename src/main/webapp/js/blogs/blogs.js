so.init(function(){
	//初始化全选。
	so.checkBoxInit('#checkAll','[check=box]');
			
	
	/**
	 * 抓取博客园
	 */
	$("#addBlogs").on("click",function(){		
		extractBlogs();
	});
	
	/**
	 * 修改博客
	 */
	$("#btnAddBlogs").on("click",function(){
		doAddModifyBlogs();
	})
	
	/**
	 * 删除所有
	 */
	$("#deleteAll").on("click",function(){	
		var param=getCheckBoxSel();
		doDeleteAll(param);
	});
		
});


/**
 * 删除单个
 */
var deleteOne=function(pkid){
	var params={ids:pkid};
	doDeleteAll(params);
}


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
	 		location.reload(); 
	 	}
	 });  
}


/**
 * 显示弹框
 */
var showModal=function(){
$('#addBlogsDiv').modal();
}

/**
 * 获取单个博客
 */
var getOneBlog=function(pkid){
	var load=layer.load();
	var param={pkid:pkid};
	$.ajax({
	 	url:"../blogs/getBlogs.shtml",
	 	type: 'POST',
	 	data:param,
	 	dataType: 'JSON',	 
	 	success: function(data){
	 		if(data.status=="200"){
				bindBlogs(data.entity);
			}
	 		else{
	 			layer.msg(data.message);
	 		}
	 		layer.close(load);
	 	}
	 });  
}

/**
 * 绑定博客
 */
var bindBlogs=function(blogs){
	$("#textTitle").val(blogs.blogtext);
	$("#textbloghref").val(blogs.bloghref);
	$("#textBlogcontent").val(blogs.blogcontent);
	$("#textBlogsummary").val(blogs.blogsummary);
	$("#textRemark").val(blogs.remark);	
	$("#hiBlogsPKID").val(blogs.pkid);
	showModal();
}

/**
 * 删除所有
 */
var doDeleteAll=function(param){	
	layer.confirm("确定删除选择的博客吗?",function(){
		var load=layer.load();
		$.ajax({
		 	url:"../blogs/delBlogs.shtml",
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
 * 添加,修改 装修日记
 */
var doAddModifyBlogs=function(){
	var blogtext=$.trim($("#textTitle").val());
	var bloghref=$.trim($("#textbloghref").val());
	var blogcontent=$.trim($("#textBlogcontent").val());
	var blogsummary=$.trim($("#textBlogsummary").val());
	var remark=$.trim($("#textRemark").val());	
    var pkid=$.trim($("#hiBlogsPKID").val());
	
	if(blogtext==""){
		layer.msg("请输入标题!");
	}
	
	var param={blogtext:blogtext,
			   bloghref:bloghref,
			   blogcontent:blogcontent,
			   blogsummary:blogsummary,
			   remark:remark,			  
			   pkid:pkid};
	
	var postURl="";
	var mes="";
	if(pkid!=""&&pkid>0){
		postURl="../blogs/modifyBlogs.shtml";
	    mes="修改成功";
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





