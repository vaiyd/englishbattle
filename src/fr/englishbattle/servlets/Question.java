package fr.englishbattle.servlets;

import fr.englishbattle.beans.Partie;
import fr.englishbattle.services.QuestionService;
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
import java.sql.Timestamp;
import java.sql.Date;

@WebServlet(urlPatterns = {"/Question"})
public class Question extends HttpServlet {

    private int count;
    private Timestamp timeStart;
    private Partie partie;
    private HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = DataConnect.getConnection()) {
            QuestionService questionService = new QuestionService(connection);

            this.setSessionParty(request);
            Timestamp timeEnd = new Timestamp(System.currentTimeMillis());

            Long finalStamp = timeEnd.getTime() - this.timeStart.getTime();

            if (finalStamp >= 60000) {
                this.session.invalidate();
                request.setAttribute("timeStamp", finalStamp);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }

            fr.englishbattle.beans.Verbe currentVerbe = partie.getQuestions().get(count).getVerbe();
            String preterit = request.getParameter("preterit");
            String pp = request.getParameter("pp");


            if (currentVerbe.getPreterit().equals(preterit) && currentVerbe.getParticipePasse().equals(pp)) {
                fr.englishbattle.beans.Question currentQuestion = partie.getQuestions().get(count);
                currentQuestion.setReponsePreterit(preterit);
                currentQuestion.setReponseParticipePasse(pp);
                currentQuestion.setDateEnvoi(new Date(timeStart.getTime()));
                currentQuestion.setDateReponse(new Date (timeEnd.getTime()));

                questionService.createQuestion(currentQuestion);

                this.count = this.count + 1;
                if (partie.getQuestions().size() - 1 == this.count) {

                    this.session.setAttribute("win", true);
                    response.sendRedirect("/Deconnexion");

                } else {
                    this.session.setAttribute("count", this.count);
                    doGet(request, response);
                }

            } else {
                this.session.invalidate();
                request.setAttribute("isWin", false);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.setSessionParty(request);

        fr.englishbattle.beans.Verbe currentVerbe = partie.getQuestions().get(this.count).getVerbe();
        request.setAttribute("verbe", currentVerbe);
        this.timeStart = new Timestamp(System.currentTimeMillis());

        this.getServletContext().getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
    }

    private void setSessionParty(HttpServletRequest request){

        this.session = request.getSession();
        this.partie = (Partie) this.session.getAttribute("partie");
        this.count = (int) this.session.getAttribute("count");
    }
}
