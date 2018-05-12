package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseConnection {

private Connection conn;
public int maxuid=1;

    public java.sql.Connection getConnection () {           //gets connection to database when needed
        if(conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                return DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Inventory?useSSL=false", "root", "Slopez625102"
                );
            } catch (Exception any) {
                any.printStackTrace();
            }
                checkDatabase();                            //when called on goes to checkdatabase
                System.out.println("i go through here");    //checks that it goes to checkdatabase
        }
        return conn;
    }
    public void checkDatabase(){                            //checks max uid from database
        int maxnum=1;
        try {
            ResultSet results = getConnection().createStatement().executeQuery(
                    "SELECT max(uid) as UID FROM Inventory;"
            );
            while (results.next()) {
                System.out.println("i go through here");
                maxuid++;
                InventorySystem.setUid(results.getInt(1));
            }
        } catch (Exception any) {
            any.printStackTrace();
        }
        System.out.println(maxuid);                         //prints maxuid
    }
    public int checkmax(){
        return maxuid;
    }
}
