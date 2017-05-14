<%--
  Created by IntelliJ IDEA.
  User: DYN
  Date: 2017/5/13
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>查询结果</title>
</head>
<body>
<table class="table table-striped table-hover ">
    <thead>
    <tr>
        <th>Title</th>
        <th>Year</th>
        <th>imdbid</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
    </tr>
    <s:property value="lalala"/>
    <s:iterator value="searchresult_movies" id='s'>


        <tr>
            <td><s:property value="#s.title"/></td>
            <td><s:property value="#s.year"/></td>
            <td><s:property value="#s.imdbid"/> </td>
        </tr>

    </s:iterator>

    </tbody>
</table>
</body>
</html>
