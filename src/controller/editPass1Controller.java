package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editPass1Controller {
    private String password;
    PASSProfilecontroller ppc;
    public void setPpc(PASSProfilecontroller ppc) {
        this.ppc = ppc;
    }



    public String id;

    public void setId(String id) {
        this.id = id;
    }
    @FXML
    private Button cancelBTN;

    @FXML
    private Button okBTN;

    @FXML
    private PasswordField passfield;

    @FXML
    void cancelBTN() {
        cancelBTN.getScene().getWindow().hide();
    }
//    String id = ppc.phc.id;
    @FXML
    void okBTN() {
            if (DBPassword(id).equals(passfield.getText())){
                okBTN.getScene().getWindow().hide();
            FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/editPass2.fxml"));
            try {
                ld.load();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }

            editPass2Controller ep2 = ld.getController();
            ep2.setEp1(this);
            ep2.setId(id);
            okBTN.getScene().getWindow().hide();
            Scene scene = new Scene(ld.getRoot());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
    }
    public String DBPassword(String id){
        DbConnection db = new DbConnection();
        Connection conn = db.getInstance();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from userlist where id = ?");
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return password;
    }
}
