<%--
  Created by IntelliJ IDEA.
  User: Baptiste Mégevand
  Date: 05/03/2018
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>English Battle</title>
</head>
<body>
    <c:import url="entete.jsp"></c:import>

    <h1 align="center">Question ${count + 1} : le verbe ${verbe.baseVerbale}</h1>

    <form align="center" method="post" style="margin-left:35%; margin-right:35%" action="Question">
        <label>${verbe.baseVerbale}</label>
        <input type="text" name="preterit" placeholder="prétérit" required>
        <input type="text" name="pp" placeholder="participe passé" required>
        <br>
        <br>
        <button type="submit" align="center">Envoyer</button>
        <br>
        <br>
    </form>


</body>
</html>
