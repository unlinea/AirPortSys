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
    private TextField UserNameField;

    @FXML
    private PasswordField PassWordFIeld;

    @FXML
    private Button SignUpBTN;

    @FXML
    private Button SignInBTN;

    public void SignInBTNCLICK() {

        try {
            root = FXMLLoader.load(getClass().getResource("/view/SUPHomePage.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage)SignInBTN.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
