package BatchInJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchOpEx {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";


    public static void main(String[] args) throws Exception{
        Connection con1 = DriverManager.getConnection(url,db_userId,db_passcode);
        Statement st1 = con1.createStatement();

        st1.addBatch("insert into Book values(3001,'Postman',850.36)");
        st1.addBatch("insert into Book values(3002,'RestAPI',650.36)");
        st1.addBatch("insert into Book values(3003,'Github',3600)");
        st1.addBatch("insert into Book values(3004,'Git',350.36)");

        int[] recode = st1.executeBatch();
        int sum = 0;
        for (int i: recode){
            sum+=i;
        }

        System.out.println("Executing completed......"+sum);
    }
}


/*

   JBDC Batch Operations

   Ans: JDBC Batch Processing allows you to group related SQL statements
   into a single unit of work and submit them to the database in one single call.
 */