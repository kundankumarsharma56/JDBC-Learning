package NoHardCodingDataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class WithoutDirectDB {

    static {

        try {
            File f = new File("src/NoHardCodingDataBase/Connection.properties");
            FileInputStream fileInputStream = new FileInputStream(f);

            Properties properties = new Properties();
            properties.load(fileInputStream);


            String url = properties.getProperty("db.url");
            String username =  properties.getProperty("db.userId");
            String password = properties.getProperty("db.PassCode");
            String poolSize = properties.getProperty("db.poolSize");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(username);
            config.setPassword(password);
            config.setMaximumPoolSize(Integer.parseInt(poolSize));

            HikariDataSource dataSource = new HikariDataSource(config);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

public static connection getDbConnection() throws Exception{
    return datasource.getConnection();
}
