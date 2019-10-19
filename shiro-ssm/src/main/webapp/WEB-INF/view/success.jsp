<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<title>login success</title>
<body>
<h2>login success,${userName}</h2>
<shiro:hasPermission name="user:query">
    <h2><a>查询</a></h2>
</shiro:hasPermission>
<shiro:hasPermission name="user:add">
    <h2><a>添加</a></h2>
</shiro:hasPermission>
<shiro:hasPermission name="user:update">
    <h2><a>修改</a></h2>
</shiro:hasPermission>
<shiro:hasPermission name="user:delete">
    <h2><a>删除</a></h2>
</shiro:hasPermission>
<shiro:hasPermission name="user:export">
    <h2><a>导出</a></h2>
</shiro:hasPermission>


</body>
</html>
