<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>资料修改 —个人中心</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link   rel="icon" href="${basePath}/img/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/img/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="${basePath}/js/common/jquery/jquery.form-2.82.js?${_v}"></script>
		<script  src="${basePath}/js/user.updateSelf.js"></script>
	</head>
	<body data-target="#one" data-spy="scroll">
		<@_top.top 1/>
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<div class="row">
				<@_left.user 1/>
				<div class="col-md-10">
					<h2>资料修改</h2>
					<hr>
					<form id="formId" enctype="multipart/form-data" action="${basePath}/user/updateSelf.shtml" method="post">
						  <input type="hidden" value="${token.id}" name="id"/>
						  <div class="form-group">
						    <label for="nickname">昵称</label>
						    <input type="text" class="form-control" autocomplete="off" id="nickname" maxlength="8" name="nickname" value="${token.nickname?default('未设置')}" placeholder="请输入昵称">
						  </div>
						  <div class="form-group">
						    <label for="email">Email（不准修改）</label>
						    <input type="text" class="form-control " disabled autocomplete="off" id="email" maxlength="64" name="email" value="${token.email?default('未设置')}" placeholder="请输入帐号">
						  </div>
						  <div class="form-group">
							  <button type="submit" class="btn btn-success">确定修改</button>
						  </div>
						</form>
				</div>
			</div><#--/row-->
		</div>
	
	</body>
</html>