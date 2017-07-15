import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class h_ChangeTownNames {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Connector.getConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String countryName = reader.readLine();

        String checkCountryQuery = "SELECT name FROM towns WHERE country = ?";
        String updateTownQuery = "UPDATE towns SET name = ? WHERE name = ?";

        PreparedStatement statement = connection.prepareStatement(checkCountryQuery);
        statement.setString(1, countryName);
        ResultSet country = statement.executeQuery();

        List<String> townsAffected = new ArrayList<>();

        if (country.isBeforeFirst()) {
            while (country.next()) {
                String townName = country.getString("name");
                PreparedStatement updateStatement = connection.prepareStatement(updateTownQuery);
                updateStatement.setString(1, townName.toUpperCase());
                updateStatement.setString(2, townName);
                updateStatement.executeUpdate();
                townsAffected.add(townName.toUpperCase());
            }

            System.out.printf("%d town names were affected.%n", townsAffected.size());
            System.out.println(townsAffected.toString());

        } else {
            System.out.println("No town names were affected.");
        }

        country.close();

    }
}
