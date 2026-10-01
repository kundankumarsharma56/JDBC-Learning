package PreparedStatementWithExa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertingData {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String insertBook = "insert into Book values(?,?,?)";

    public static void main(String[] args) throws Exception{
        // Taking input as values
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book id: ");
        int book_id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Book name: ");
        String book_name = sc.nextLine();

        System.out.print("Enter Book price: ");
        double book_Price = Double.parseDouble(sc.nextLine());


        // database Connection
        Connection connection1 = DriverManager.getConnection(url,db_userId,db_passcode);
        // using Prepared Statement
        PreparedStatement preparedStatement1 = connection1.prepareStatement(insertBook);

        preparedStatement1.setInt(1,book_id);
        preparedStatement1.setString(2,book_name);
        preparedStatement1.setDouble(3,book_Price);

        int count = preparedStatement1.executeUpdate();
        System.out.println("Row Effected: "+count);
    }
}
