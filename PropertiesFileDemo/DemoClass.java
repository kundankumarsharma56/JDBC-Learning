package PropertiesFileDemo;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class DemoClass {
    public static void main(String[] args) throws Exception{

        File f = new File("src/PropertiesFileDemo/db.properties");
        FileInputStream fis = new FileInputStream(f);

        Properties p = new Properties();
        p.load(fis);

       String username =  p.getProperty("db.username");
       String password = p.getProperty("db.Password");

        System.out.println("User name is: "+username);
        System.out.println("Password is: "+password);

        fis.close();

    }
}

/*

    Working with Properties files in Java
    ---------------------------------------
    Ans: 1. As of now in JDBC programs we have declared Database
        Properties which is not at all recommended because if database properties are
        modified then we need to modify our java programs also.

     Note: In realtime project we will have multiple databases like below
          a.) Dev DB (Developers will use this db)
          b.) SIT DB (Testers will use this db)
          c.) UAT DB (Client side testing will happen with this db)
          d.) Prod DB (Live application will use this db)

     Note: Every database will have different credentials so when we want to change
           the database then we have to change our java programs which is not a good practise.

           -> We need to separate our java programs with Database properties using properties file
           -> Properties file is used to configure properties in the form of key-value pair

     Note: File name can be anything's but extension should be .properties only

     -> To work with properties file we have "java.util.properties" class. Using this class we
         can store the data in properties, and we can get data from Properties file.



                     ┌──────────────────────┐
                     │   Java Application   │
                     └──────────┬───────────┘
                                │
                                │ Configuration
                                ▼
                    ┌────────────────────────┐
                    │    Properties File     │
                    │    db.properties       │
                    └────────────┬───────────┘
                                 │
                 ┌───────────────┼───────────────┐
                 │               │               │
                 ▼               ▼               ▼
          ┌────────────┐  ┌────────────┐  ┌────────────┐
          │   DEV DB   │  │   SIT DB   │  │   UAT DB   │
          │ Developers │  │  Testers   │  │   Client   │
          └────────────┘  └────────────┘  └────────────┘
                                 │
                                 │
                                 ▼
                         ┌────────────┐
                         │  PROD DB   │
                         │ Live App   │
                         └────────────┘

        ───────────────────────────────────
                WITHOUT Properties File
        ───────────────────────────────────
                Java Code
                   │
                   ├── URL
                   ├── Username
                   └── Password
                        │
                        ▼
                  Change DB
                        │
                        ▼
                Modify Java Code ❌

             ────────────────────────────
                WITH Properties File
             ────────────────────────────
        Java Code ───────► db.properties ───────► Database
                               │
                               ├── URL
                               ├── Username
                               └── Password

        Change DB
           │
           ▼
        Change Properties File Only ✅
           │
           ▼
        Java Code remains unchanged

 */