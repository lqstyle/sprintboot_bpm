<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>kpmg人员管理系统</title>
    <!--引入外部css-->
    <link rel="stylesheet" href="/css/vacationApply.css"/>
    <link rel="stylesheet" type="text/css" href="/css/theme/icon.css">
    <link rel="stylesheet" type="text/css" media="screen" href="/css/theme/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="/css/theme/ui.jqgrid.css">
    <!--引入外部js-->
    <script src="/js/jquery/jquery-1.8.3.js"></script>
    <script src="/js/jquery/jquery.form-3.46.0.js"></script>
    <script src="/js/jquery/jquery.BlockUI.js"></script>
    <script src="/js/jquery/grid.locale-cn.js"></script>
    <script src="/js/jquery/jquery.jqGrid.min.js"></script>
    <script src="/js/layer/layer.js"></script>
    <script src="/js/jquery/jquery-ui.js"></script>
    <script src="/js/vacationApply.js"></script>

</head>

<body>
<!--头部-->
<header class="head">
    请假申请信息填写
</header>
<!--主体内容-->
<div class="main">
    <div class="search">
        <span>请假时间起：</span> <input type="text" id="beginTime"/>
        <span>请假时间止：</span> <input type="text" id="endTime"/>
    </div>
    <div class="submit">
        <input id="submitBtn" type="button" value="提交" class="btn">
        &nbsp; &nbsp; &nbsp; &nbsp;
        <input id="cancelBtn" type="button" value="取消" class="btn">
    </div>
</div>

</body>

</html>