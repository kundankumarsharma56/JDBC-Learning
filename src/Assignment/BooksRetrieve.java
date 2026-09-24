package Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BooksRetreive {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String SELECT = "SELECT Book_name, Book_Price FROM Book Where Book_Price < ?";
    private static final String WithoutUserInputSQL = "SELECT Book_name, Book_Price FROM Book";

    public static void main(String[] args) throws Exception{

        // User Input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Book Price: ");
        String userInputPrice = sc.nextLine();

        // Database Connectivity
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connectionMysql = DriverManager.getConnection(url,db_userId,db_passcode);
        PreparedStatement preparedStatement;

//        Checking Condition  if user not provide any input then retrieve all data otherwise condition where true
        if (userInputPrice.isEmpty()){
            preparedStatement = connectionMysql.prepareStatement(WithoutUserInputSQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1)+"---"+rs.getDouble(2) );
            }
            // Else block for with user input
        }else {
           preparedStatement = connectionMysql.prepareStatement(SELECT);
            preparedStatement.setDouble(1, Double.parseDouble(userInputPrice));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+"---"+resultSet.getDouble(2) );
            }
        }
    }
}
