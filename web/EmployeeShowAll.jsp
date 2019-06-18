<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.restaurant.dao.Impl.EmployeeDaoImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.restaurant.entity.Employee" %>
<%@ page import="java.util.List" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/6/17
  Time: 7:59
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>My EmployeeShow JSP</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<style>
    th, tr, td, table {
        border: 1px solid blue;
    }
</style>
<%
    EmployeeDaoImpl employeeDao=new EmployeeDaoImpl();
    List list= employeeDao.getList();
    employeeDao.getList();
    System.out.println(employeeDao.getList());
%>


<body>
<h1>所有员工信息展示</h1>
<table border="1px">
    <tr>
        <th>员工序号</th>
        <th>用户名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>身份证号</th>
        <th>家庭住址</th>
        <th>电话</th>
        <th>职位</th>
        <th>是否在职</th>
    </tr>
    <%--<%--%>
        <%--if(list!=null&&list.size()>0)--%>
        <%--{--%>
            <%--for(int i=0;i<list.size();i++)--%>
            <%--{--%>
                <%--Employee employee=list.get(i);--%>
    <%--%>--%>
    <%--<tr>--%>
        <%--<th><%=employee.getId()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getName()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getSex()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getBirthday()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getIdentityID()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getAddress()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getTel()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getPosition()%>--%>
        <%--</th>--%>
        <%--<th><%=employee.getFreeze()%>--%>
        <%--</th>--%>
    <%--</tr>--%>
    <%--<%--%>
            <%--}--%>
        <%--}--%>
    <%--%>--%>
</table>


</body>
</html>
