<%--
  Created by IntelliJ IDEA.
  User: Baptiste Mégevand
  Date: 05/03/2018
  Time: 09:07
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
    <h1 align="center">English Battle Inscription</h1>
    <a href="/EnglishBattle" align="center">Connexion</a>
    <form align="center" style="margin-left:35%; margin-right:35%" method="post" action="Inscription">
        <label>Nom</label>
        <input type="text" name="nom" required>
        <br>
        <br>
        <label>Prénom</label>
        <input type="text" name="prenom" required>
        <br>
        <br>
        <label>Email</label>
        <input type="email" name="email" required>
        <br>
        <br>
        <label>Mot de passe</label>
        <input type="password" name="password" required>
        <br>
        <br>
        <label>Niveau</label>
        <select id="level" name="level">
            <option value="beginner" selected>Débutant</option>
            <option value="medium" >Intermédiaire</option>
            <option value="expert">Expert</option>
        </select>
        <br>
        <br>
        <label>Villes</label>
        <select id="city" name="city">
        <c:forEach  items="${villes}" var="e" >
            <option value=${e.cp} >${e.nom}</option>
        </c:forEach>
        </select>
        <br>
        <br>
        <button align="center">Inscription</button>
        <br>
        <br>
        <br>
    </form>

</body>
</html>
