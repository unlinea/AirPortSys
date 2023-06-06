package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
public class SuperAdminPageController {
    @FXML
    Button profileBTN;
    public void superOnClick(){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/profilePage.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("theres problem to load passenger view \n" + e);
        }
        Stage stage = new Stage();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.show();
    }

}
