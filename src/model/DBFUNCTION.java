package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFUNCTION {
    public String getPassword(String id){
        DbConnection db = new DbConnection();
        Connection conn = db.getInstance();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement("select * from userlist where id = ?");
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                return rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
