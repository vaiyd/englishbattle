package fr.englishbattle.servlets;

import fr.englishbattle.beans.Joueur;
import fr.englishbattle.beans.Ville;
import fr.englishbattle.services.JoueurService;
import fr.englishbattle.services.VilleService;
import fr.englishbattle.utils.DataConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/Inscription"})
public class Inscription extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (java.sql.Connection connection = DataConnect.getConnection()) {
            JoueurService joueurService = new JoueurService(connection);
            //VilleService villeService = new VilleService(connection);

            String name = request.getParameter("name");
            String firstname = request.getParameter("firstname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String level = request.getParameter("level");
            Long cityId = Long.valueOf(request.getParameter("city"));

            //Ville city = villeService.getVilleFromId(cityId);

            joueurService.createJoueur(new Joueur(email,name,firstname,password,cityId,level));
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/EnglishBattle");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Ville> villes = new ArrayList<>();
        try (Connection connection = DataConnect.getConnection()) {
            VilleService villeService = new VilleService(connection);
            villes = villeService.getAll();
            request.setAttribute("villes", villes);

            this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
            // TODO : on peu utiliser les méthodes de JoueurService
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
