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

public class LoginPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    PasswordField pwdField;
    @FXML
    TextField usernameField;
    @FXML
    Button signupBTN;
    @FXML
    Button signinBTN;

    public void SignInBTNCLICK() {

        try {
            root = FXMLLoader.load(getClass().getResource("/view/SUPHomePage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)signinBTN.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
