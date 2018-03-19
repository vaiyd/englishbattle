<%--
  Created by IntelliJ IDEA.
  User: Baptiste Mégevand
  Date: 19/03/2018
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>EnglishBattle</title>
</head>
<body>
<section style="margin-left:47%;">
<h4>Hall Of Fame</h4>
<table>
    <thead>
    <tr>
        <th>Joueur</th>
        <th>Score</th>
    </tr>
    </thead>
    <c:forEach  items="${listScore}" var="e" >
        <tr>
            <td>${e.nom}</td>
            <td>${e.score}</td>
        </tr>

    </c:forEach>
</table>
</section>
</body>
</html>
