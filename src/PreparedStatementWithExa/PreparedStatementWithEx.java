package PreparedStatement;

import java.sql.*;

public class PreparedStatementWithEx {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String Insert_Sql = "insert into Book values(?,?,?)";


    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection co = DriverManager.getConnection(url,db_userId,db_passcode);

        PreparedStatement pr = co.preparedStatement(Insert_Sql);
        pr.executeUpdate()
        co.close();
    }
}

/*
   Prepared Statement

   Prepared statement is used to execute both select & non-select queries
   prepared statement will support for positional parameters(?) in the query
   Positional parameters are used to supply dynamic values to query in the run time.

   When we want to execute same query multiple times with different values then
    it is highly recommended to use Prepared Statement.

    --> Query without positional parameters: Insert into books values(101,"Java",5000.50);
    --> Query with positional parameters: Insert into books values(?,?,?);

    Note : Positional parameters index will start from 1.

 */
