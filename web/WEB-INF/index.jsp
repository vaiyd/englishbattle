<%--
  Created by IntelliJ IDEA.
  User: Baptiste Mégevand
  Date: 05/03/2018
  Time: 08:27
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
    <h1 align="center">Bienvenue sur Englisheuh Battle !</h1>

    <form align="center" method="post" style="margin-left:35%; margin-right:35%" action="EnglishBattle">
        <c:if test="${isConnect == false}">
            <label style="color:red">Nom de compte ou mot de passe incorrect</label>
            <br>
            <br>
        </c:if>

        <c:if test="${isWin == false}">
            <label style="color:red">Test échoué</label>
            <br>
            <br>
        </c:if>

        <c:if test="${timeStamp != null}">
            <label style="color:red">Temps maximal de 60s écoulé : ${timeStamp/1000}s</label>
            <br>
            <br>
        </c:if>


        <label>E-mail</label>
        <input type="email" name="email" required>
        <br>
        <br>
        <label>Mot de passe</label>
        <input type="password" name="password" required>
        <br>
        <br>
        <button type="submit" align="center">Connexion</button>
        <br>
        <br>
        <a href="/Inscription" align="center">Inscription</a>
        <br>
    </form>

    <c:import url="halloffame.jsp"></c:import>

</body>
</html>
