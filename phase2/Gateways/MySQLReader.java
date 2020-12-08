package Gateways;

import java.sql.*;

public class MySQLReader {
    static String jdbcDriver = "com.mysql.jdbc.Driver";
    static String url ="jdbc:mysql://localhost:3306/phase2";
    static String userName ="root";
    static String password = "csc207group0700";

    void cleanTable(String tableName){
        try {
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM " + tableName);
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

}
