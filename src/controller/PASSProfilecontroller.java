package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PASSProfilecontroller {
    public PASSHomeController phc;
    public void setPhc(PASSHomeController phc) {
        this.phc = phc;
    }
    public String id;

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    private Label emailLBL;

    @FXML
    private Label fnameLBL;

    @FXML
    private Label idLBL;

    @FXML
    private Label lnameLBL;

    @FXML
    private Label passeditLBL;

    @FXML
    private Label phonenumLBL;

    @FXML
    private Label usernameLBL;
    @FXML
    private Button returnBTN;
    private String password;


    @FXML
    void passeditLBL() {

        //load edit pass window
        FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/editPass1.fxml"));
        try {
            ld.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        editPass1Controller ep1 = ld.getController();
        ep1.setPpc(this);
        ep1.setId(id);
        passeditLBL.getScene().getWindow().hide();
        Scene scene = new Scene(ld.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void returnBTN() {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/PASSHome.fxml"));
        try {
            ld.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PASSHomeController phc = ld.getController();
        phc.setId(id);
        returnBTN.getScene().getWindow().hide();
        Scene scene = new Scene(ld.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public String getPassword() {
        return password;
    }

    public void setLabels(String id){
        DbConnection db = new DbConnection();
        Connection conn = db.getInstance();
        try {
            PreparedStatement pst = conn.prepareStatement("select * from userlist where id = ?");
            pst.setString(1,id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                idLBL.setText(id);
                usernameLBL.setText(rs.getString("username"));
                fnameLBL.setText(rs.getString("firstname"));
                lnameLBL.setText(rs.getString("lastname"));
                phonenumLBL.setText(rs.getString("phonenumber"));
                emailLBL.setText(rs.getString("email"));
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
