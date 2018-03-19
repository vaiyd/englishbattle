<%--
  Created by IntelliJ IDEA.
  User: Baptiste Mégevand
  Date: 05/03/2018
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>English Battle</title>
</head>
<body>
   <h4>English Battle : Utilisateur : ${sessionScope.partie.joueur.nom} ${sessionScope.partie.joueur.prenom} / Meilleur score : ${count} <a style="margin-left:50%" href="Deconnexion">Deconnexion</a></h4>
</body>
</html>
