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

import java.io.IOException;

public class SignUpPageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField UserNameField;

    @FXML
    private PasswordField PassWordField;

    @FXML
    private PasswordField rePassWordField;

    @FXML
    private Label UserNamePromptLBL;

    @FXML
    private Label PasssWordPromptLBL;
    @FXML
    private Button SignUpBTN;

    @FXML
    private Button CancelBTN;

    public void SignUpBTN(){
        /* NEEDS : check if exists the do not allow (or run CancelBTN)
                   if doesn't exit add to database & grant permission




        */
    }


    public void CancelBTN(){
        // goes back to sign in page


            try {
                root = FXMLLoader.load(getClass().getResource("/view/SignInPage.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            stage = (Stage)CancelBTN.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }
}
