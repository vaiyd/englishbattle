package fr.englishbattle.servlets;

import fr.englishbattle.beans.Joueur;
import fr.englishbattle.beans.Partie;
import fr.englishbattle.beans.Verbe;
import fr.englishbattle.beans.Question;
import fr.englishbattle.services.JoueurService;
import fr.englishbattle.services.PartieService;
import fr.englishbattle.services.VerbeService;
import fr.englishbattle.utils.DataConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = {"/EnglishBattle"})
public class EnglishBattle extends HttpServlet {

    JoueurService joueurService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Boolean successAuth = false;

        try (Connection connection = DataConnect.getConnection()) {

            joueurService = new JoueurService(connection);
            VerbeService verbeService = new VerbeService(connection);
            PartieService partieService = new PartieService(connection);
            Random randomGenerator = new Random();

            List<Verbe> verbes = verbeService.getAll();
            List<Question> questions = new ArrayList<>();
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Joueur joueur = joueurService.getJoueurFromEmailAndPassword(email, password);

            if (joueur != null) {
                HttpSession session = request.getSession();
                successAuth = true;
                Partie partie = new Partie(joueur);
                partieService.createPartie(partie);

                for (int x = 0; x <= 5; x++) {
                    int index = randomGenerator.nextInt(verbes.size());
                    boolean found = false;
                    for (Question question : questions) {
                        if (question.getVerbe().equals(verbes.get(index))) {
                            found = true;
                        }
                    }
                    if (!found) {
                        questions.add(new Question(partie, verbes.get(index)));
                    }
                }

                partie.setQuestions(questions);

                session.setAttribute("partie", partie);
                session.setAttribute("count", 0);
                response.sendRedirect("/Question");

            }

            // TODO : on peu utiliser les méthodes de JoueurService
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (!successAuth) {
            request.setAttribute("isConnect", false);
            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = DataConnect.getConnection()) {
            joueurService = new JoueurService(connection);
            List<Joueur> listScore = joueurService.getScores();

            request.setAttribute("listScore", listScore);
            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);


            // TODO : on peu utiliser les méthodes de JoueurService
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
