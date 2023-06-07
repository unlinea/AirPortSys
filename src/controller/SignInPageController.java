package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField UserNameField;

    @FXML
    private PasswordField PassWordFIeld;

    @FXML
    private Button SignUpBTN;

    @FXML
    private Button SignInBTN;

    public void SignInBTNClick() {

        //1-if authorized for Sup loads SupHome.
        SUPHomeLoader();

        //2-if authorized for manager loads managerhome.
        //MGRHomrloader();

        //3-if authorized for employee loads employee.

        //4-if authorized for passenger loads passenger.
    }

    private void SUPHomeLoader() {
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
}
