<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>装修列表—装修日记</title>
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
		<script  src="${basePath}/js/fitmentList.js?${_v}"></script>		
		<script  src="${basePath}/js/common/bootstrap-star-rating-master/js/star-rating.js"></script>
	    <!--uEditor  -->
		<link href="${basePath}/js/common/uEditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
	    <script type="text/javascript" charset="utf-8" src="${basePath}/js/common/uEditor/umeditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="${basePath}/js/common/uEditor/umeditor.min.js"></script>
	    <script type="text/javascript" src="${basePath}/js/common/uEditor/lang/zh-cn/zh-cn.js"></script>
	
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 4/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.fitment 1/>
				<div class="col-md-10">
					<h2>装修日记列表</h2>
					<hr>
					<form method="post" action="" id="formId" class="form-inline">
						<div clss="well">
					      <div class="form-group">
					        <input type="text" class="form-control" style="width: 300px;" value="${findContent?default('')}" 
					        			name="findContent" id="findContent" placeholder="输入标题/ 标签">
					      </div>
					     <span class=""> <#--pull-right -->
				         	<button type="submit" class="btn btn-primary">查询</button>
				         	<button type="button" id="addFitment" class="btn btn-success">新增</button>
				         	<button type="button" id="deleteAll" class="btn  btn-danger">删除</button>
				         </span>    
				        </div>
					<hr>
					<table class="table table-bordered">
						<tr>
							<th><input type="checkbox" id="checkAll"/></th>
							<th>标题</th>
							<th>标签</th>
							<th>链接</th>
							<th>星级</th>
							<th>创建时间</th>							
							<th>操作</th>
						</tr>
						<#if page?exists && page.list?size gt 0 >
							<#list page.list as it>
								<tr>
									<td><input value="${it.id}" check='box' type="checkbox" /></td>
									<td>${it.title?default('未设置')}</td>
									<td>${it.tag?default('未设置')}</td>
									<td><a href='${it.url?default('未设置')}' target="_blank">点我</a></td>
									<td>${it.star}</td>
									<td>${it.addedTime?string('yyyy-MM-dd HH:mm')}</td>
									<td>
										<a href="javascript:getOneFitment(${it.id});">编辑</a>
										<a href="javascript:deleteOne(${it.id});">删除</a>
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
		
		<@shiro.hasPermission name="/fitment/index.shtml">
			<#--弹框-->
			<div class="modal fade" id="addFitmentDiv" tabindex="-1" role="dialog" aria-labelledby="addPermissionLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="addPermissionLabel">装修日记</h4>
			      </div>
			      <div class="modal-body">
			        <form id="boxRoleForm">
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">标题名称:</label>
			            <input type="text" class="form-control" name="textTitle" id="textTitle" placeholder="请输入标题"/>
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">URL地址:</label>
			            <input type="text" class="form-control" id="textUrl" name="textUrl"  placeholder="请输入URL">
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">标签:</label>
			            <input type="text" class="form-control" id="textTag" name="textTag"  placeholder="请输入标签">
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">风格:</label>
			            <select class="form-control" id="textStyle" name="textStyle"  placeholder="请输入风格">
			              <option value ="1">现代简约</option>
						  <option value ="2">地中海</option>
						  <option value ="3">乡村田园</option>
						  <option value ="4">北欧宜家</option>
						  <option value ="5">美式风格</option>
						  <option value ="6">欧式风格</option>
						  <option value ="7">混搭风格</option>
			            </select>
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">星星:</label>
			            <input id="inputStar" value="0.5" type="number" class="rating" min=0 max=5 step=0.5 data-size="xs" >
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">备注:</label>
			            <input type="text" class="form-control" id="textRemark" name="textRemark"  placeholder="请输入备注">
			          </div>
			          <div class="form-group">
			            <label for="recipient-name" class="control-label">内容:</label>
			            <script type="text/plain" id="myEditor" style="width:568px;height:240px;">
	                    </script>
			          </div>
			          <input type="hidden" id="hiFitmentPKID" >
			        </form>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			        <button type="button" id="btnAddFitment" class="btn btn-primary">保存</button>
			      </div>
			    </div>
			  </div>
			</div>
			<#--/弹框-->
			</@shiro.hasPermission>
		</div>
		
		
	</body>
</html>