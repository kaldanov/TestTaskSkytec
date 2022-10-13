package task.servlets;

import task.db.UserGoldChangeHistory;
import task.threads.ThreadForChange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/change")
public class ChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * тут по параметрам получаю от фронта все данные
         * **/

        String reason = request.getParameter("reason");
        long clanId = Long.parseLong(request.getParameter("clan_id"));
        long userId = Long.parseLong(request.getParameter("user_id"));
        int gold = Integer.parseInt(request.getParameter("gold"));
        boolean isInc = !request.getParameter("is_increase").equals("2");

        //сервлет сам по себе как поток работает, но сделал чтобы изминении были в потоке
        ThreadForChange threadForChange = new ThreadForChange(new UserGoldChangeHistory(clanId, userId, new Date(), gold, reason, isInc));
        threadForChange.start();

        System.out.println(Thread.currentThread().getName());  // это для наглядности

        response.sendRedirect("/");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
