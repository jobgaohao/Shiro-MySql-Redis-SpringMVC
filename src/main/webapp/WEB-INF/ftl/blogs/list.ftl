<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>博客园爬虫</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="${basePath}/img/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/img/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/js/common/bootstrap-star-rating-master/css/star-rating.css" media="all" rel="stylesheet" type="text/css" />
		<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>		
		<script  src="${basePath}/js/shiro.demo.js"></script>
		<script  src="${basePath}/js/blogs/blogs.js?${_v}"></script>					   	
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 6/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.blogs 1/>
				<div class="col-md-10">
					<h2>博客园列表</h2>
					<hr>
					<form method="post" action="" id="formId" class="form-inline">
						<div clss="well">
					      <div class="form-group">
					        <input type="text" class="form-control" style="width: 300px;"  name="blogtext" id="blogtext" placeholder="输入标题" value="${blogtext?default('')}">
					        <input type="text" class="form-control" style="width: 300px;"  name="blogcontent" id="blogcontent" placeholder="输入博客内容" value="${blogcontent?default('')}">
					      </div>
					     <span class=""> <#--pull-right -->
				         	<button type="submit" id="searchBlogs" class="btn btn-primary">查询</button>
				         	<button type="button" id="addBlogs" class="btn btn-success">爬取</button>
				         	<button type="button" id="deleteAll" class="btn  btn-danger">删除</button>				         	
				         </span>    
				        </div>
					<hr>
					<table class="table table-bordered">
						<tr>
							<th><input type="checkbox" id="checkAll"/></th>
							<th>标题</th>							
							<th>备注</th>
							<th>创建时间</th>							
							<th>操作</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td><input value="${it.pkid}" check='box' type="checkbox" /></td>
									<td><a href='${it.bloghref}' target="_blank">${it.blogtext?default('未设置')}</a></td>
									<td>${it.remark?default('')}</td>
									<td>${it.addedTime?string('yyyy-MM-dd HH:mm')}</td>
									<td>
										<a href="javascript:getOneBlog(${it.pkid});">编辑</a>
										<a href="javascript:deleteOne(${it.pkid});">删除</a>
									</td>
								</tr>
							</#list>
						<#else>
							<tr>
								<td class="text-center danger" colspan="7">没有找到数据</td>
							</tr>
						</#if>
					</table>
					<#if page?exists>
						<div class="pagination pull-right">
							${page.pageHtml}
						</div>
					</#if>
					</form>
				</div>
			</div>
		</div>
		
		   <#--弹框-->
			<div class="modal fade" id="addBlogsDiv" tabindex="-1" role="dialog" aria-labelledby="addPermissionLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="addPermissionLabel">博客园</h4>
			      </div>
			      <div class="modal-body">
			        <form id="boxRoleForm">
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">博客标题:</label>
			            <input type="text" class="form-control" name="textTitle" id="textTitle" placeholder="请输入博客标题"/>
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">博客链接:</label>
			            <input type="text" class="form-control" id="textbloghref" name="textbloghref"  placeholder="请输入URL">
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">博客内容:</label>
			            <input type="text" class="form-control" id="textBlogcontent" name="textBlogcontent"  placeholder="请输入博客内容">
			          </div>			          			         
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">博客摘要:</label>
			            <input type="text" class="form-control" id="textBlogsummary" name="textBlogsummary"  placeholder="请输入博客内容">
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">备注:</label>
			            <input type="text" class="form-control" id="textRemark" name="textRemark"  placeholder="请输入备注">
			          </div>
			          <input type="hidden" id="hiBlogsPKID" >
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			        <button type="button" id="btnAddBlogs" class="btn btn-primary">保存</button>
			      </div>
			    </div>
			  </div>
			</div>
			<#--/弹框-->
	</body>
</html>