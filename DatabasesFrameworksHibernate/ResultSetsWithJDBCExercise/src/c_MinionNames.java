import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class c_MinionNames {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Connector.getConnection();

        String selectMinions = "SELECT m.name,m.age FROM minions AS m \n" +
                "                INNER JOIN minions_villains AS mv\n" +
                "                ON mv.minion_id = m.minion_id\n" +
                "                WHERE mv.villain_id = ?";


        String selectVillain = "SELECT name FROM villains WHERE villain_id = ?";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        PreparedStatement minionsStatement = connection.prepareStatement(selectMinions);
        PreparedStatement villainStatement = connection.prepareStatement(selectVillain);

        villainStatement.setInt(1, id);
        minionsStatement.setInt(1, id);

        ResultSet villainRS = villainStatement.executeQuery();
        ResultSet minionsRS = minionsStatement.executeQuery();
        if(villainRS.next()) {
            System.out.printf("Villain : %s%n",villainRS.getString("name"));
            if (!minionsRS.next()) {
                System.out.println("<no minions>");
            } else {
                minionsRS.previous();
                int count = 1;
                while (minionsRS.next()) {
                    System.out.println(String.format("%d. %s %d",
                            count++,
                            minionsRS.getString("name"),
                            minionsRS.getInt("age")));
                }
            }
        } else {
            System.out.println("No villain with ID " + id + " exists in the database.");
        }


    }
}
