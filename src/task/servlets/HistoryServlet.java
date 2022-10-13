package task.servlets;

import task.db.ClanService;
import task.db.ClanServiceImpl;
import task.db.UserGoldChangeHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/history")
public class HistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));

        ClanService clanService = new ClanServiceImpl();

        List<UserGoldChangeHistory> userGoldChangeHistories = clanService.getHistoryByClanId(id);

        request.setAttribute("users", userGoldChangeHistories);
        request.getRequestDispatcher("/history.jsp").forward(request, response);

    }
}
