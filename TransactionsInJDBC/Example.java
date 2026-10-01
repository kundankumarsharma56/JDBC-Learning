package TransactionsInJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example {
    private static final String url = "jdbc:mysql://localhost:3006/bank";
    private static final String user = "root";
    private static final String password = "Xlim@152009";

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String debitSQL = "UPDATE accounts SET balance = balance - 500 WHERE id = 1";
            String creditSQL = "UPDATE accounts SET balance = balance + 500 WHERE id = 2";

            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
                 PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

                debitStmt.executeUpdate();
                creditStmt.executeUpdate();

                conn.commit();
                System.out.println("Transaction committed successfully!");

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


/*

   Q.   Transactions in JDBC
   ---------------------------
  Ans:  1.Single unit amount of work is called as Transaction
        2. We can execute multiple Queries in single transaction

      Note: Every transaction should follow ACID Properties

      A - Atomicity
      C - Consistency
      I - Isolation
      D - Durability


      Note :1. When we are performing Non-select operations (insert / update/ delete) with database then transactions is mandatory
            2. For select operations transaction is optional
            3. When we are performing multiple operations is single transaction then either all operations should be success or none of the operation should be success
                         -> Transaction Commit - to save then operation permanently
                         -> Transaction rollback - to undo the operation
            -> In JDBC, transaction will be committed by default for every non-select query execution because by default Transaction Auto commit is true

            con.setAutoCommit(true); // this is default behavior of Connection object

          -> If we want to manage Transaction in jdbc we need to set AutoCommit as false
 */
