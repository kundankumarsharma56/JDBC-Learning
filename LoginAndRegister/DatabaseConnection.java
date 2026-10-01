package LoginAndRegister;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    private  static final String url = "jdbc:mysql://localhost:3306/user_management";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url,db_userId,db_passcode);
    }
}
