package ResultSet;

import java.sql.*;

public class SelectBook {
    private  static final String url = "jdbc:mysql://localhost:3306/adv_java_db";
    private static final String db_userId = "root";
    private static final String db_passcode = "Xlim@152009";
    private static final String SELECTBook = "SELECT Book_id, Book_name, Book_Price FROM Book";


    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,db_userId,db_passcode);

        Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = st.executeQuery(SELECTBook);

        ResultSetMetaData metaData = rs.getMetaData();

        System.out.println("Column count: "+metaData.getColumnCount());

        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName);
        }
        rs.close();
        st.close();
        connection.close();
    }
}
