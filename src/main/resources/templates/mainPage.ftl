<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>kpmg人员管理系统</title>
    <!--引入外部css-->
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
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
    <script src="/js/mainPage.js"></script>
</head>

<body>
<!--头部-->
<header class="publicHeader">
    <h1>kpmg人员管理系统</h1>
    <div class="publicHeaderR">
        <p>
            <span>下午好！</span><span style="color: #fff21b"> ${userName!}</span> , 欢迎你！
        </p>
        <a href="/">退出</a>
    </div>
</header>
<!--主体内容-->
<div class="left">
    <h2 class="leftH2">
        <span class="span1"></span>功能列表 <span></span>
    </h2>
    <nav>
        <ul class="list">
        <#list menuUrlList as menuUrl>
            <li>
                <a id="projectBut" href="${menuUrl!}?roleId=${roleId!}">用户管理</a>
            </li>
        </#list>
        </ul>
    </nav>
</div>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong> <span>假期管理页面</span>
    </div>
    <div class="search">
        <span>任务名称：</span> <input type="text" id="taskName" placeholder="任务名称"/>
        <input id="searchBut" type="button" value="查询" style="width: 100px;padding: 0 20px;height: 28px;">
        <input id="createVacation" type="button" value="创建请假申请" style="width: 120px;padding: 0 20px;height: 28px;">

    </div>

    <!--文件上传-->
    <div class="search">

        <form name="frm" id="frm" enctype="multipart/form-data">

        </form>

    </div>
    <!--账单表格 样式和供应商公用-->
    <table id="projectTable">

    </table>
    <!--分页-->
    <div id="projectPager"></div>
</div>

</body>

</html>