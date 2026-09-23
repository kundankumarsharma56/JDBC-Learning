package Book;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SecondClass {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String Delete_Sql = "DELETE FROM Book WHERE Book_id = 102";

    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,db_userId,db_passcode);
        Statement statement = connection.createStatement();

        int roweffected = statement.executeUpdate(Delete_Sql);

        System.out.println("Recode Effected: "+roweffected);
    }

}
