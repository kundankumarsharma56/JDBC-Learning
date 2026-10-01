package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectOnlyForOne {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String Select_data = "select * from Book where Book_id = 103";

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url,db_userId,db_passcode);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Select_data);

        if (resultSet.next()){
            int book_id = resultSet.getInt("Book_id");
            String bookName = resultSet.getString("Book_Name");
            double bookPrice = resultSet.getDouble("Book_Price");

            System.out.println("Book Id: "+book_id+" - Book Name: "+bookName+" - Book Price: "+bookPrice);
        }else {
            System.out.println("Not found record");
        }

        connection.close();
    }
}
