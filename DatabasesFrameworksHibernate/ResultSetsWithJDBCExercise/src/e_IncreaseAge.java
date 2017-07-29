import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class e_IncreaseAge {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Connector.getConnection();

        String query = "UPDATE minions SET age = age + 1, name = concat(ucase(left(name, 1)), substring(name,2))\n" +
                "WHERE minion_id IN (";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] ids = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();

        for (int i = 0; i < ids.length; i++) {
            query +="?";
            if (i != ids.length-1) {
                query += ",";
            }

        }
        query += ")";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            preparedStatement.setInt(i+1, id);
        }

        preparedStatement.execute();
    }
}
