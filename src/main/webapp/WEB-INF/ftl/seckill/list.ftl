<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>秒杀-秒杀列表</title>
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
		<script  src="${basePath}/js/common/bootstrap-star-rating-master/js/star-rating.js"></script>	    
	
	</head>
	<body data-target="#one" data-spy="scroll">
	<@_top.top 5/>
	<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情页</th>
                </tr>
                </thead>
                <tbody>
                <#if page?exists && page?size gt 0 >
					<#list page as it>
                    <tr>
                        <td>${it.name}</td>
                        <td>${it.number}</td>
                        <td>${it.startTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${it.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${it.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>              
                        <td><a class="btn btn-info" href="${basePath}/seckill/${it.seckillId}/detail.shtml" target="_blank">详情</a></td>
                    </tr>
                </#list>
				<#else>
				    <tr>
						<td  colspan="6">没有找到数据</td>
					</tr>
				</#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
	
	</body>
</html