import connector.Connector;

import java.sql.*;

public class g_PrintAllMinions {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.getConnection();

        String selectQueryAsc = "SELECT m.minion_id FROM minions AS m\n" +
                "ORDER BY m.minion_id";

        String selectQueryDesc = "SELECT m.minion_id FROM minions AS m\n" +
                "ORDER BY m.minion_id DESC";

        String selectSpecificMinion = "SELECT name FROM minions WHERE minion_id=?";

        Statement ascStatement = connection.createStatement();
        ResultSet ascending = ascStatement.executeQuery(selectQueryAsc);

        Statement descStatement = connection.createStatement();
        ResultSet descending = descStatement.executeQuery(selectQueryDesc);

        ascending.next();
        descending.next();

        int forwardId = ascending.getInt("minion_id");
        int backwardId = descending.getInt("minion_id");

        while (forwardId <= backwardId) {

            PreparedStatement findMinion = connection.prepareStatement(selectSpecificMinion);
            findMinion.setInt(1, forwardId);
            ResultSet resultSetForward = findMinion.executeQuery();
            resultSetForward.next();
            System.out.println(resultSetForward.getString("name"));

            if (forwardId == backwardId) {
                break;
            }
            findMinion.setInt(1, backwardId);
            ResultSet resultSetBack = findMinion.executeQuery();
            resultSetBack.next();
            System.out.println(resultSetBack.getString("name"));

            ascending.next();
            descending.next();

            forwardId = ascending.getInt("minion_id");
            backwardId = descending.getInt("minion_id");

        }
    }
}
