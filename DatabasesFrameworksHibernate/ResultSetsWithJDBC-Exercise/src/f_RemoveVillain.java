import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class f_RemoveVillain {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Connector.getConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int villainId = Integer.parseInt(reader.readLine());

        String deleteFromMapTable = "DELETE FROM minions_villains WHERE villain_id = ?";
        String deleteFromVillainTable = "DELETE FROM villains WHERE villain_id = ?";
        String findVillainName = "SELECT name FROM villains WHERE villain_id = ?";

        PreparedStatement selectVillainStatement = connection.prepareStatement(findVillainName);
        selectVillainStatement.setInt(1, villainId);

        ResultSet villainNameResult = selectVillainStatement.executeQuery();

        if(villainNameResult.next()) {
            String villainName = villainNameResult.getString("name");
            villainNameResult.close();

            PreparedStatement statementDeleteFromMapTable = connection.prepareStatement(deleteFromMapTable);

            statementDeleteFromMapTable.setInt(1, villainId);
            int minionsAffected = statementDeleteFromMapTable.executeUpdate();
            statementDeleteFromMapTable.close();

            PreparedStatement deleteVillainStatement = connection.prepareStatement(deleteFromVillainTable);
            deleteVillainStatement.setInt(1, villainId);
            deleteVillainStatement.executeUpdate();
            deleteVillainStatement.close();

            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released", minionsAffected);
        } else {
            System.out.println("No such villain was found");
        }

    }
}
