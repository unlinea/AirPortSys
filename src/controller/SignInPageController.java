package controller;

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
import java.io.IOException;
import java.sql.*;
import static model.Person.Role.*;

public class SignInPageController {
    String ID;
    DbConnection dbconnection = new DbConnection();
    Connection conn = DbConnection.getInstance();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label UserNameLBL;
    @FXML
    private Label signUpLBL;
    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField PassWordFIeld;

    @FXML
    private Button SignUpBTN;
    @FXML
    private Label alarmLBL;

    @FXML
    private Button SignInBTN;

    public void SignInBTNClick() {

         ID = checkExist(UserNameField.getText(),PassWordFIeld.getText());
        if (ID==null){
            //he doesn't exist ...
            alarmLBL.setText("something went wrong!");
        }else {
            //show related scene
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM userlist where ID = ?");
                pst.setString(1,ID);
                ResultSet rs = pst.executeQuery();
                String role = null;

                while (rs.next()){
                role = rs.getString("role");}

                //if authorized for employee loads employee.
                if (role.equals("employee")){loadPage("/view/EmpHome.fxml");}
                //if authorized for passenger loads passenger.
                else if (role.equals("Passenger")){

//                    PASSHomeController phc = new PASSHomeController();
//                    phc.setId(ID);
                    SignInBTN.getScene().getWindow().hide();
                    FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/PASSHome.fxml"));
                    try {
                        ld.load();

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }

                    PASSHomeController phc = ld.getController();
                    phc.setSic(this);
                    phc.setId(ID);
                    SignInBTN.getScene().getWindow().hide();
                    Scene scene = new Scene(ld.getRoot());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
//                    loadPage("/view/PASSHome.fxml");
                }
                //if authorized for manager loads managerhome.
                else if (role.equals("manager")){loadPage("/view/MGRHome.fxml");}
                //if authorized for Sup loads SupHome.
                else if (role.equals("SuperAdmin")){loadPage("/view/SUPHomePage.fxml");}
            } catch (SQLException e) {
                alarmLBL.setText("something went wrong!");
                throw new RuntimeException(e);
            }
        }
    }
    public void SignUpLBL(){
        try {
            root = FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) SignInBTN.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void loadPage(String pageAddress) {
        Parent root = null;
        try {
            ((Stage) SignInBTN.getScene().getWindow()).close();
            root = FXMLLoader.load(getClass().getResource(pageAddress));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public String checkExist(String userName, String password){
        DbConnection dbConnection = new DbConnection();
        Connection conn = DbConnection.getInstance();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM userlist WHERE username = ? AND password = ?");
            pst.setString(1,userName);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            if (rs.getString("password").equals(null)){}
            else {
                return rs.getString("id");
                }
            }
        }catch (Exception e){
            System.out.println("sign in :" + e.getMessage());
        }
        return null;
    }

    private Person.Role roleConverter(String role) {
        if (role.equals("Employee"))return Employee;
        else if (role.equals("Passenger"))return Passenger;
        else if (role.equals("Manager"))return Manager;
        else if (role.equals("SuperAdmin"))return SuperAdmin;
        else return null;
    }

    public String findByUserName(String username){
        DbConnection dbConnection = new DbConnection();
        Connection conn = DbConnection.getInstance();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM userlist WHERE username = ?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("password"));
                return rs.getString("password");
            }
//            while (rs.next()){
//                System.out.println("PASS : " + rs.getString("password"));
//            }

        } catch (SQLException e) {
            System.out.println("err");
            throw new RuntimeException(e);
        }

        return null;
    }

    private void SUPHomeLoader() {
        ((Stage)SignInBTN.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SupHomePage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    private void MGRHomeLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MGRHomePage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }
    private void EMPHomeLoader() {
                                                                        //add address !!!
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SupHomePage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    private void PASHomeLoader() {                                       //add address!!!
        FXMLLoader loader = new FXMLLoader(getClass().getResource("e/View/SupHomPage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }
    public void findPasswordByName(){
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.getResultSet();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void findPasswordByEmail(){

    }
}
