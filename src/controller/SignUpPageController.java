package controller;

import com.mysql.cj.protocol.Resultset;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DbConnection;
import model.Person;
import model.TableInfo;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public class SignUpPageController {
    DbConnection db = new DbConnection();
    Connection conn = db.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField usernamefield;

    @FXML
    private PasswordField passfield;

    @FXML
    private PasswordField repassfield;

    @FXML
    private Button signupBTN;

    @FXML
    private Button cancelBTN;

    @FXML
    private Label userNamePromptLBL;

    @FXML
    private Label PassWordPromptLBL;

    @FXML
    private TextField firstnamefield;

    @FXML
    private TextField lastnamefield;

    @FXML
    private TextField emailfield;
    @FXML
    private TextField phonenumberfield;
    @FXML
    private Label emailPromptLBL;
    @FXML
    private Label rePassPromptLBL;
    @FXML
    private Label alarmLBL;
    public void SignUpBTN() {
        if (checkEmpty()){
            return;
        } else{
        String testMsg = authPassenger(usernamefield.getText(),emailfield.getText());
        if (takeAction(testMsg)){
            //if no err checks pass
            if(checkPass(passfield.getText(),repassfield.getText())){
                //if pass is ok --> adds Passenger and leads him to sign in page.
                addPassenger();
                loadSignInPage();
            }
        }else return;
        }
    }

    private void loadSignInPage() {
        loadSignInPage(signupBTN);
    }

    private void loadSignInPage(Button signupBTN) {
        try {
            root = FXMLLoader.load(getClass().getResource("/view/SignInPage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) signupBTN.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean checkPass(String pass,String repass) {
    if (pass.equals(repass)){
        return true;
    } else {
        rePassPromptLBL.setText("PASS DOESN'T MATCH!");
        return false;}
    }

    private boolean takeAction(String testMsg) {
        if (testMsg.equals("1"))
        {
            userNamePromptLBL.setText("NOT AVAILABLE!");
            emailPromptLBL.setText("");
            rePassPromptLBL.setText("");
            alarmLBL.setText("");
            return false;
        }
        else if (testMsg.equals("2")) {
            userNamePromptLBL.setText("");
            emailPromptLBL.setText("ALREADY USED!");
            rePassPromptLBL.setText("");
            alarmLBL.setText("");
            return false;
        }
        else if (testMsg.equals("0"))
        {
            userNamePromptLBL.setText("");
            emailPromptLBL.setText("");
            rePassPromptLBL.setText("");
            alarmLBL.setText("");
            return true;
        }
        return false;
    }


    public void CancelBTN() {
        // goes back to sign in page
        loadSignInPage(cancelBTN);
    }
    public String authPassenger(String userName,String email){
        try {
            PreparedStatement pst = conn.prepareStatement("select * from userlist");
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                String dbUserName = rs.getString("username");
                String dbEmail = rs.getString("email");
                if (dbUserName.equals(userName)){
                    return "1";
                }
                if (dbEmail.equals(email)){
                    return "2";
                }
            }
        }catch (Exception e){
            System.out.println("auth passenger :" + e.getMessage());
        }
        return "0";
        }
    public void addPassenger() {

         try {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO userlist (firstname,  lastname, username, password, email, role, phonenumber) values (?,?,?,?,?,?,?)");
        pst.setString(1,firstnamefield.getText());
        pst.setString(2,lastnamefield.getText());
        pst.setString(3,usernamefield.getText());
        pst.setString(4,passfield.getText());
        pst.setString(5,emailfield.getText());
        pst.setString(6,"Passenger");
        pst.setString(7,phonenumberfield.getText());
        pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error !!! at sign up");
        }
    }

    private boolean checkEmpty() {
        try {
            if (firstnamefield.getText().isEmpty() || lastnamefield.getText().isEmpty() || usernamefield.getText().isEmpty()|| passfield.getText().isEmpty() ||
                    emailfield.getText().isEmpty() || phonenumberfield.getText().isEmpty()) {
                alarmLBL.setText("Fill All The Fields!");
                return true;
            } else {
                alarmLBL.setText("");
                return false;
            }
        }catch(Exception e){
            System.out.println("this " + e.getMessage());
        }return false;
    }
}
