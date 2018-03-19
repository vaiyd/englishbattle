package fr.englishbattle.servlets;

import fr.englishbattle.beans.Joueur;
import fr.englishbattle.services.JoueurService;
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

@WebServlet(urlPatterns = {"/Connexion"})
public class Connexion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private JoueurService joueurService = null;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request,
            HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        try (Connection connection = DataConnect.getConnection()) {
            joueurService = new JoueurService(connection);

        // TODO : on peu utiliser les méthodes de JoueurService
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request,
            HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
    }
}