package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {

    @FXML
    PasswordField pwdField;
    @FXML
    TextField usernameField;
    @FXML
    Button signupBTN;
    @FXML
    Button signinBTN;

    public void bia() {
        System.out.println("loged in");
    }

}
