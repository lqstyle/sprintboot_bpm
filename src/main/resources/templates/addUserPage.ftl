<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>kpmg人员管理系统</title>
    <!--引入外部css-->
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/addUserPage.css"/>
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
    <script src="/js/addUserPage.js"></script>

</head>

<body>
<!--头部-->
<header class="head">
    新增用户
</header>
<!--主体内容-->
<div class="main">
    <form id="loginForm" action="/user/addUser" method="post">
        <div class="search">
            <span>登录名：</span> <input type="text" id="loginname" name="loginName"/>
            <span>密码：</span> <input type="text" id="password" name="password"/>
            <span>邮箱：</span> <input type="text" id="email" name="email"/>
            <span>电话：</span> <input type="text" id="phone" name="phone"/>
        </div>
        <div class="search">
            <span>角色：</span>
            <select id="city" class="select" name="roleName" style="width:270px">
                <option value=""></option>
                <option value="CDD Assistant">CDD Assistant</option>
                <option value="CDD Executive">CDD Executive</option>
                <option value="Telephony Support">Telephony Support</option>
                <option value="Quality Control (QC)">Quality Control (QC)</option>
                <option value="Case Manager">Case Manager</option>
                <option value="Business Approver (BA)">Business Approver (BA)</option>
                <option value="Financial Crime Compliance (FCC) ">Financial Crime Compliance (FCC)</option>
                <option value="Quality Assurance (QA) ">Quality Assurance (QA)</option>
            </select>
            <span>姓名：</span> <input type="text" id="name" name="name"/>
            <span>手机：</span> <input type="text" id="mobile" name="mobile"/>
        </div>
        <div class="radio">
            <input id="syn" type="radio" name="synToActiviti" value="1" checked/>同步工作流表
            <input id="unsyn" type="radio" name="synToActiviti" value="0"/>不同步工作流表
        </div>
        <div class="submit">
            <input id="submitBtn" type="submit" value="确定" style="width: 100px;height: 30px;">
            &nbsp; &nbsp; &nbsp; &nbsp;
            <input id="cancelBtn" type="reset" value="取消"
                   style="width: 100px; height: 30px; ">
        </div>
        <input type="hidden" value="${userName!}" name="createUser">
    </form>
</div>

</body>

</html>