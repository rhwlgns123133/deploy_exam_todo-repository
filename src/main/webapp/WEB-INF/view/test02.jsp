<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-10-12
  Time: 오후 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
      Title: ${todoDTO.title}
    </div>
    <div>
      DueDate: ${todoDTO.dueDate}
    </div>
    <div>
      Writer: ${todoDTO.writer}
    </div>
    <div>
      Writer: ${todoDTO.finished}
    </div>
</body>
</html>
