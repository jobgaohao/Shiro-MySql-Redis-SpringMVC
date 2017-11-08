<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>秒杀-秒杀详情</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link rel="icon" href="${basePath}/img/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${basePath}/img/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
		<script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script src="${basePath}/js/common/layer/layer.js"></script>
		<script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>		
		<script src="${basePath}/js/shiro.demo.js"></script>
		<script src="${basePath}/js/seckill/seckill.js?${_v}"></script>		
		<!--使用CDN 获取公共js http://www.bootcdn.cn/-->
		<!--jQuery Cookie操作插件-->
		<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
		<!--jQuery countDown倒计时插件-->
		<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>	    
	
	</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="pannel-heading">
            <h1>${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <!--显示time图标-->
                <span class="glyphicon glyphicon-time"></span>
                <!--展示倒计时-->
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>
<!--登录弹出层 输入电话-->
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"> </span>秒杀电话:
                </h3>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey"
                               placeholder="填写手机号^o^" class="form-control">
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <!--验证信息-->
                <span id="killPhoneMessage" class="glyphicon"> </span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                                                            提交
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function () {    	
        //使用EL表达式传入参数
        seckill.detail.init({
        	seckillId:${seckill.seckillId},
            startTime:${seckill.startTimeLong},//毫秒
            endTime:${seckill.endTimeLong}//毫秒
        });
    })
</script>