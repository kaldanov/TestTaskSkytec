<%@ page import="task.db.Clan" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="task.db.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<%
    ArrayList<Clan> clans = (ArrayList<Clan>) request.getAttribute("clans");
    if (clans != null) {
        for (Clan it : clans) {
%>
    <div class="container">
        <h2><%=it.getName()  %>    $<%=it.getGold()%>
        </h2>
        <a href="/history?id=<%=it.getId()%>">Отслеживание действий начисления золота</a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name of user</th>
                <th scope="col">Gold</th>
                <th scope="col">Reason</th>
                <th scope="col">Decrease/Increase</th>
                <th scope="col">Button</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<User> users = it.getUsers();
                if (users != null) {
                    for (User user : users) {
            %>
            <form action="/change" method="post">
                <tr>
                    <th scope="row"><%=user.getId()%>
                    </th>
                    <td><%=user.getName()%>
                    </td>
                    <td><input type="number" class="form-control" name="gold"></td>
                    <td><select class="form-select" name="reason">
                        <option value="1">Пополнить казну из своего кармана</option>
                        <option value="2">Сражался на арене</option>
                    </select></td>
                    <td><select class="form-select" name="is_increase">
                        <option value="1">Increase</option>
                        <option value="2">Decrease</option>
                    </select></td>
                    <td>
                        <input type="hidden" class="form-control" name="user_id" value="<%=user.getId()%>">
                        <input type="hidden" class="form-control" name="clan_id" value="<%=it.getId()%>">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </td>
                </tr>
            </form>
            </tbody>
            <%
                    }
                }
            %>
        </table>
    </div>
<%
        }
    }
%>
</body>
</html>