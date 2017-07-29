import connector.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class b_GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = Connector.getConnection();

        String query = "SELECT v.name, count(mv.minion_id) AS 'count' FROM villains AS v " +
                "INNER JOIN minions_villains AS mv " +
                "ON mv.villain_id = v.villain_id " +
                "INNER JOIN minions AS m " +
                "ON m.minion_id = mv.minion_id " +
                "GROUP BY v.name " +
                "HAVING count(mv.minion_id) > 3 " +
                "ORDER BY count(mv.minion_id) DESC";

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("count"));
        }

    }
}
