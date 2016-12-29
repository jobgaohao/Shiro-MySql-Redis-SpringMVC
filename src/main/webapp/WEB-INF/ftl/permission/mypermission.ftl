<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>我的权限  —我的权限</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="${basePath}/img/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/img/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>		
		<script  src="${basePath}/js/common/bootstrap/bootstrap-treeview.js"></script>
		<script  src="${basePath}/js/mypermission.js"></script>
	</head>
	<body data-target="#one" data-spy="scroll">
		
		<@_top.top 1/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<#--row-->
			<div class="row">
				<@_left.user 2/>
				<div class="col-md-10">
					<h2>我的权限</h2>
					<hr>
					<div id="getPermissionTree" >loding... ...</div>
				</div>
			</div>
			<#--/row-->
		</div>				
	</body>
</html>