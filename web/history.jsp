<%@ page import="java.util.List" %>
<%@ page import="task.db.UserGoldChangeHistory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Игрок</th>
                <th scope="col">Золото</th>
                <th scope="col">Причина</th>
                <th scope="col">+/-</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<UserGoldChangeHistory> userGoldChangeHistories = (List<UserGoldChangeHistory>) request.getAttribute("users");
                if (userGoldChangeHistories != null) {
                    for (UserGoldChangeHistory it : userGoldChangeHistories) {
            %>
                <tr>
                    <th scope="row"><%=it.getDate()%></th>
                    <td><%=it.getUserId()%></td>
                    <td><%=it.getGold()%></td>
                    <td><%=it.getReason()%></td>
                    <% if(it.isInc()){%>
                    <td>Увеличен</td>
                    <%}else{%>
                    <td>Уменшен</td><%}%>
                </tr>
            </tbody>
            <%
                    }
                }
            %>
        </table>
        <a class="btn btn-danger" href="/">Назад</a>
    </div>
</body>
</html>