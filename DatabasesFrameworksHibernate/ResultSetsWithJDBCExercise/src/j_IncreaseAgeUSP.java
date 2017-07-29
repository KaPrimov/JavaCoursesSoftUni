import connector.Connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class j_IncreaseAgeUSP {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Connector.getConnection();

        String procedureCall = "CALL usp_get_older(?)";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try(CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            int id = Integer.parseInt(reader.readLine());
            callableStatement.setInt(1, id);
            callableStatement.execute();
        }

    }
}
