package ConnectionPooling;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.Statement;

public class ExampleOfConnectionPooling  {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";

    public static void main(String[] args) throws Exception {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(db_userId);
        config.setPassword(db_passcode);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);

        HikariDataSource datasource = new HikariDataSource(config);

        Connection con = datasource.getConnection();

        String sql = "INSERT INTO BOOK VALUES (202, 'Django', 4500.00)";

        Statement stmt = con.createStatement();

        stmt.executeUpdate(sql);

        System.out.println("RECORD INSERTED.....");
        con.close();
    }
}


/*

  Q. Connection Pooling
 ────────────────────────

  Ans: 1. Connection pooling is the process of getting fixed no of Connections from database and store them into a pool for re-usability
       2. If we don't use connection pooling concept them our project will run into connections
          Exhausted problem (No connections available to communicate with database)
       3. If we use DriverManager.getConnection() it will give physical connection with database.
          It is not at all recommended to use physical Connections.
       4. Always we need to use Logical Connections to perform DB operations.
          To use Logical connections we need to set up Connection pool.

          Note: With the connection pooling we can improve performance of the application

          How to set up Connection pool
        ──────────────────────────────────
        We can stup Connection Pool in 2 ways
        1. Client side Connection pool
          Ex: DBCP, C3P0, Hikari etc.
        2. Server Managed Connection pool
          Ex: Tomcat, JBoss, WebLogic etc.


                 ┌───────────────────────────────┐
                 │       JAVA APPLICATION        │
                 └───────────────┬───────────────┘
                                 │
                                 │ Request Connection
                                 ▼
                 ┌───────────────────────────────┐
                 │    CONNECTION POOL MANAGER    │
                 │                               │
                 │   Manages reusable connections│
                 └───────────────┬───────────────┘
                                 │
                    ┌────────────┼────────────┐
                    │            │            │
                    ▼            ▼            ▼
              ┌──────────┐ ┌──────────┐ ┌──────────┐
              │ Conn 1   │ │ Conn 2   │ │ Conn 3   │
              │ Available│ │ In Use   │ │ Available│
              └────┬─────┘ └────┬─────┘ └────┬─────┘
                   │            │            │
                   └────────────┼────────────┘
                                │
                                ▼
                 ┌───────────────────────────────┐
                 │        MYSQL DATABASE         │
                 └───────────────────────────────┘

                                                         CONNECTION FLOW
                                                         ────────────────

                    ┌──────────────┐    ┌──────────────────┐    ┌──────────────────┐    ┌────────────────────┐
                    │ Java Program │ →  │ Request Connection│ → │ Connection Pool  │ →  │Available Connection│
                    └──────────────┘    └──────────────────┘    └──────────────────┘    └──────────┬─────────┘
                                                                                                   │
                                                                                                   ▼
                    ┌──────────────┐    ┌──────────────┐    ┌───────────────┐    ┌────────────────────┐
                    │ Reuse Again  │ ←  │ Connection   │ ←  │ Return to Pool│ ←  │ Finish Work        │
                    └──────┬───────┘    │    Pool      │    └───────────────┘    └──────────┬─────────┘
                           │            └──────────────┘                                    │
                           │                                                                ▼
                           │                                                        ┌────────────────┐
                           └─────────────────────────────────────────────────────── │ Execute SQL    │
                                                                                    └───────┬────────┘
                                                                                            │
                                                                                            ▼
                                                                                   ┌────────────────┐
                                                                                   │    Database    │
                                                                                   └────────────────┘


                              Without Pooling
                           ─────────────────────
                    Create Connection  →  Execute SQL  →  Close Connection  →  Repeat Again & Again


                                With Pooling
                              ────────────────
                    Create Connections  →  Keep in Pool  →  Reuse Connections  →  Better Performance
 */