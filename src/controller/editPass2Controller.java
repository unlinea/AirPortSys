package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class editPass2Controller {
    editPass1Controller ep1;
    private String password;

    public void setEp1(editPass1Controller ep1) {
        this.ep1 = ep1;
    }

    public String id;

    public void setId(String id) {
        this.id = id;
    }
    @FXML
    private Button cancelBTN;

    @FXML
    private PasswordField newPassfield;

    @FXML
    private PasswordField repassfield;

    @FXML
    private Button submitBTN;
    @FXML
    private Label alarmLBL;

    @FXML
    void cancelBTN() {
        cancelBTN.getScene().getWindow().hide();
    }
    @FXML
    void submitBTN() {
        if (newPassfield.getText().isEmpty() || repassfield.getText().isEmpty()){
            takeAction("3");
        }else {
            if (newPassfield.getText().equals(DBPassword(id))){
                takeAction("1");
            }else {
                //if regex
                if (matchPassword(newPassfield.getText())){
                    if (newPassfield.getText().equals(repassfield.getText())){
                        // update password + close windows
                        updatePassword(newPassfield.getText(),id);
                        submitBTN.getScene().getWindow().hide();
                        loadPASSProfile();
                    } else {
                        takeAction("2");
                    }
                }else takeAction("4");

            }
        }
    }

    private void loadPASSProfile() {
        FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/PASSProfile.fxml"));
        try {
            ld.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        submitBTN.getScene().getWindow().hide();
        Scene scene = new Scene(ld.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void takeAction(String num) {
        if (num.equals("1")){
            alarmLBL.setText("new password can't be same as before");
        } else if (num.equals("2")){
            alarmLBL.setText("fields don't match!");
        } else if (num.equals("3")) {
            alarmLBL.setText("fill al the fields");
        } else if (num.equals("4")) {
            alarmLBL.setText("4 <> 16");
        }
    }
    private void updatePassword(String password,String id){
        DbConnection db = new DbConnection();
        Connection conn = db.getInstance();
        try {
            PreparedStatement pst = conn.prepareStatement("update userlist set password = ? where id = ?");
            pst.setString(1,password);
            pst.setString(2,id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public boolean matchPassword (String password){
        Pattern p = Pattern.compile("^[a-zA-z0-9]{5,15}$");
        Matcher m = p.matcher(password);
        boolean matchFound = m.find();
        return matchFound;
    }
}
