import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    private static final String URL = "jdbc:sqlserver://your_server_address:1433;databaseName=your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
