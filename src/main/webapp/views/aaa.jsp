<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2024-01-24
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>지나갑니다</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <style>
        ul, li { list-style-type: none; }
        ul { padding: 1px 10px; }
        li {
            display: inline-block;
            padding-right: 10px;
        }
    </style>
</head>
<body>

<div id="notice">
    <h2><span id="noticeTitle">공지사항</span>
    </h2>
    <ul id="noticeList" cols="all" ></ul>
    <ul id="noticeList2" cols="no,subject,writer,strDate" ></ul>
    <ul id="noticeList3" cols="-:content,-:writer" ></ul>
</div>



<div id="employee">
    <h2><span id="employeeTitle">공지사항</span>
    </h2>
    <ul id="employeeList" cols="empId, empCode, empName, empTitle, deptNo, empAuth" ></ul>
</div>






<script src="common/listing4.js" target="ul#noticeList" action="notice_list" eventid="1" table="notice"
        where="content like '%에%' and no < 2" ></script>
<script src="common/listing4.js" target="ul#employeeList" action="notice_list" eventid="2" table="employee"
        where="" ></script>
</body>
</html>
