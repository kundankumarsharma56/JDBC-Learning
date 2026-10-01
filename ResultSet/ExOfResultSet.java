package ResultSet;

import java.sql.*;


public class ExOfResultSet {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String SELECT = "SELECT Book_id, Book_name, Book_Price FROM Book";

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url,db_userId,db_passcode);
        Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stm.executeQuery(SELECT);

        while (rs.next()){
            System.in.read();
            System.in.read();
            rs.refreshRow();
            System.out.println(rs.getInt(1) + "---" +rs.getString(2)+"---"+rs.getDouble(3) );
        }
        rs.close();
        stm.close();
        conn.close();
    }
}


/*

Types of ResultSet..?
    ResultSet will represent data given by our select query
    ResultSet will maintain cursor to point the rows
    Initially Resultset cursor will be available before first record
    we need to move RS cursor to next position by calling next() method

    Note : By Default ResultSet is FORWARD_DIRECTIONAL

    1.TYPE_FORWARD_ONLY (BY DEFAULT)
    2.TYPE_SCROLL_INSENSITIVE
    3.TYPE_SCROLL_SENSITIVE

       SENSITIVE & INSENSITIVE Resultsets are scrollable and Bi-Directional

    SENSITIVE RESULT SET : Bi-Directional Resultset & it will react for every change
    INSENSITIVE RESULT SET : Bi-Directional Resultset & it won't react for changes


----------> ResuletSet concurrency will represent changes of ResultSet data
             1. CONCUR_READ_ONLY
             2. CONCUR_UPDATABLE

      --> CONCUR_READ_ONLY Will allow only read operation on the ResultSet
      --> CONCUR_UPDATABLE will allow update operations also on the ResultSet
 */
