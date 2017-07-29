import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class d_AddMinion {
    private static final String URL = "jdbc:mysql://localhost:3306/minions_db";

    private static final String USERNAME = "root";

    private static final String PASS = "1234";

    public static void main(String[] args) throws SQLException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String minionInput = reader.readLine();
        String villainInput = reader.readLine();

        String[] minionInfo = minionInput.split("\\s+");
        String[] villainInfo = villainInput.split("\\s+");

        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String town = minionInfo[3];
        int townId = 0;
        int minionId = 0;
        int villainId = 0;

        String villainName = villainInfo[1];

        String checkTownQuery = "SELECT town_id FROM towns WHERE name = '" + town + "'";
        String checkVillainQuery = "SELECT villain_id FROM villains\n" +
                "WHERE name = '" + villainName + "'";

        String insertTown = "INSERT INTO towns(name) VALUES ('" + town + "')";
        String insertVillain = "INSERT INTO villains(name, evilness_factor) VALUES ('" + villainName + " ', 'evil')";

        String insertMinion = "INSERT INTO minions(name,age,town_id) VALUES (?,?,?)";
        String insertMap = "INSERT INTO minions_villains VALUE (?,?)";
        String getMinionId = "SELECT minion_id FROM minions WHERE name = ?";
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASS);

        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet townResult = statement.executeQuery(checkTownQuery);

            if (!townResult.next()) {
                statement.executeUpdate(insertTown);
                System.out.printf("Town %s was added to the database.%n", town);
            }

            townResult = statement.executeQuery(checkTownQuery);

            if (townResult.next()) {
                townId = townResult.getInt("town_id");
            }

            ResultSet villainResult = statement.executeQuery(checkVillainQuery);

            if (!villainResult.next()) {
                statement.executeUpdate(insertVillain);
                System.out.printf("Villain %s was added to the database.%n", villainName);
            }
            villainResult = statement.executeQuery(checkVillainQuery);

            if (villainResult.next()) {
                villainId = villainResult.getInt("villain_id");
            }

            PreparedStatement minionsStatement = connection.prepareStatement(insertMinion);
            minionsStatement.setString(1, minionName);
            minionsStatement.setInt(2, minionAge);
            minionsStatement.setInt(3, townId);

            minionsStatement.executeUpdate();

            PreparedStatement minionIdStatement = connection.prepareStatement(getMinionId);
            minionIdStatement.setString(1, minionName);
            ResultSet minionIdResult = minionIdStatement.executeQuery();

            if (minionIdResult.next()) {
                minionId = minionIdResult.getInt("minion_id");
            }

            PreparedStatement mapStatement = connection.prepareStatement(insertMap);
            mapStatement.setInt(1, minionId);
            mapStatement.setInt(2, villainId);
            mapStatement.executeUpdate();

            System.out.printf("Successfully added %s to be minion of %s%n", minionName, villainName);

            minionIdResult.close();
            villainResult.close();
            townResult.close();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
