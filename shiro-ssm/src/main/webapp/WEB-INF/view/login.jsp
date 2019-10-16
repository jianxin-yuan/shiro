<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<title>login</title>
<body>

<h2 align="center">登录页面</h2>
<div align="center">
    <p>正常登陆链接</p>
    <form method="post" action="/login">
        用户名： <input align="center" type="text" name="userName"/>
        密 码：<input align="center" type="password" name="password"/>
        <input type="submit" value="登录"/>
    </form>
</div>
</body>
</html>
