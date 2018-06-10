<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - kpmg人员管理系统</title>
    <link rel="stylesheet" href="css/style.css"/>
    <script src="/js/jquery/jquery-1.8.3.js"></script>
    <script src="/js/jquery/jquery.form-3.46.0.js"></script>
    <script src="/js/layer/layer.js"></script>
</head>
<body class="login_bg">
<section class="loginBox">
    <header class="loginHeader">
        <h1>kpmg人员管理系统</h1>
    </header>
    <section class="loginCont">
        <form class="loginForm" id="loginForm" action="/user/validateUser">
            <div class="inputbox">
                <label for="userName">用户名：</label>
                <input id="userName" type="text" name="userName" placeholder="请输入用户" required/>
            </div>
            <div class="inputbox">
                <label for="userPassword">密码：</label>
                <input id="userPassword" type="userPassword" name="userPassword" placeholder="请输入密码" required/>
            </div>
            <div class="subBtn">
                <input type="submit" value="登录"/>
                <input type="reset" value="重置"/>
            </div>
        </form>
    </section>
</section>

</body>
</html>