package fr.englishbattle.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/Deconnexion"})
public class Deconnexion extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("win") != null) {
            boolean isWin = (boolean) session.getAttribute("win");
            if (isWin) {
                session.invalidate();
                this.getServletContext().getRequestDispatcher("/WEB-INF/bravo.jsp").forward(request, response);
            } else{
                request.setAttribute("isWin", false);
                this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            }
        } else{
            session.invalidate();
            response.sendRedirect("/EnglishBattle");
        }
    }
}
