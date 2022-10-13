package task.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/clanGame";
    static final String USER = "postgres";
    static final String PASS = "user";

    private static Connection connection = null;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Clan get(long clanId) {
        return null;
    }

/**
 * synchronized в данной случае необязательно,
 * так как я не изпользую один экземпляр в нескольких потоках
 * **/

    public static synchronized boolean changeGold(UserGoldChangeHistory userGoldChangeHistory) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select gold from clan where clan.id = ?");

            preparedStatement.setLong(1, userGoldChangeHistory.getClanId());

            ResultSet resultSet = preparedStatement.executeQuery();

            int gold = 0;

            while (resultSet.next()) {
                gold = resultSet.getInt("gold");
            }

            if (userGoldChangeHistory.isInc()) {
                gold += userGoldChangeHistory.getGold();
            } else {
                gold -= userGoldChangeHistory.getGold();
            }

            preparedStatement = connection.prepareStatement("UPDATE clan SET gold = ? WHERE id = ?");

            preparedStatement.setInt(1, gold);
            preparedStatement.setLong(2, userGoldChangeHistory.getClanId());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            saveHistory(userGoldChangeHistory);


            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean saveHistory(UserGoldChangeHistory userGoldChangeHistory) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "INSERT INTO gold_change_history(clan_id,user_id,reason,gold,is_inc) " +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setLong(1, userGoldChangeHistory.getClanId());
            preparedStatement.setLong(2, userGoldChangeHistory.getUserId());
            preparedStatement.setString(3, userGoldChangeHistory.getReason());
            preparedStatement.setInt(4, userGoldChangeHistory.getGold());
            preparedStatement.setBoolean(5, userGoldChangeHistory.isInc());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static List<UserGoldChangeHistory> getHistoryByClanId(long clanId) {

        List<UserGoldChangeHistory> userGoldChangeHistories = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select clan_id, user_id, reason, date, gold, is_inc from gold_change_history where clan_id =?");

            preparedStatement.setLong(1, clanId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userGoldChangeHistories.add(new UserGoldChangeHistory(
                        resultSet.getLong("clan_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getDate("date"),
                        resultSet.getInt("gold"),
                        resultSet.getString("reason"),
                        resultSet.getBoolean("is_inc")
                ));
            }

            return userGoldChangeHistories;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


    public static List<Clan> getClans() {

        try {

            // тут OneToMany. Сделал селект джойном. Получается, у одного клана есть несколько игроков
            PreparedStatement preparedStatement = connection.prepareStatement("" +
                    "select clan.id, clan.name, clan.gold, u.id as user_id, u.name as user_name from clan\n" +
                    "left join \"user\" u on clan.id = u.clan_id");

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Long, Clan> clanById = new HashMap<>(); // 1. тут создаю мап
            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int gold = resultSet.getInt("gold");

                String userName = resultSet.getString("user_name");
                long uId = resultSet.getLong("user_id");

                Clan clan = clanById.get(id);
                if (clan == null) {  // 2. тут проверка, если нет то добавляю
                    clan = new Clan(id, name, gold);
                    clanById.put(clan.getId(), clan);
                }
                clan.addUser(new User(uId, userName)); // 3. тут добавляю игроков клана
            }

            return new ArrayList<>(clanById.values());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
