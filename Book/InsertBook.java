package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertBook {

    private  static  final String db_url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String user_Id = "root";
    private static final String db_password = "Xlim@152009";

    private static final String INSERT_SQL = "INSERT INTO Book VALUES(103, 'Python', 1196.95)";


    public static void main(String[] args) throws Exception {

        // step 1 Load Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // step 2 : Get DB Connection
        Connection con = DriverManager.getConnection(db_url,user_Id,db_password);


        // Step 3 Create statement
        Statement st = con.createStatement();
       int rowsEffected = st.executeUpdate(INSERT_SQL);

       // Step 5: Process Result
        System.out.println("Record Inserted count: "+rowsEffected);

        // step 6: close connection
        st.close();
        con.close();
    }
}
