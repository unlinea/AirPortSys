package model;

import java.sql.*;

public class DbConnection {

    private static Connection instance = null;

    public static Connection getInstance() {

        if (instance != null)
            return instance;
        //Load the JDBC ODBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establish the connection to the database
            instance = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db","root","Bong#Claude!2004");
            return instance;
        }catch (Exception e){
            return null;
        }

    }
}
//        Statement stmt=conn.createStatement();
//        ResultSet rs=stmt.executeQuery("select * from employees");
//        while(rs.next())
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//            System.out.println(rs.);
//        conn.close();