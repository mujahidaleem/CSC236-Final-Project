package Gateways;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

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

    protected String turnHashMapIntoString(HashMap<String, LocalDateTime> arrayList){
        if(arrayList.size()>0){
            StringBuilder string = new StringBuilder();
            for (String s: arrayList.keySet()){
                string.append(s).append("=").append(arrayList.get(s).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("_");
            }
            string.delete(string.length()-1,string.length());
            return string.toString();
        }else {
            return "";
        }
    }

    protected HashMap<String, LocalDateTime> turnStringIntoHashMap(String string){
        HashMap<String, LocalDateTime> map = new HashMap<>();
        if(string != null && string.length()>0){
            String[] strings = string.split("_");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for(String s:strings){
                String[] s1 = s.split("=");
                String event = s1[0];
                LocalDateTime date = LocalDateTime.parse(s1[1], formatter);
                map.put(event, date);
            }
            return map;
        } else{
            return map;
        }
    }

    protected String turnArrayListIntoString(ArrayList<Integer> arrayList){
        StringBuilder stringBuilder = new StringBuilder();
        if(arrayList.size()>0){
            for(int i:arrayList){
                stringBuilder.append(i).append("_");
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    protected ArrayList<Integer> turnStringToArrayList(String string){
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(string != null && string.length() > 0){
            String[] strings = string.split("_");
            for(String s:strings){
                arrayList.add(Integer.parseInt(s));
            }
        } else {
            return new ArrayList<>();
        }
        return arrayList;
    }

}
