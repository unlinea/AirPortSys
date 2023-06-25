package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PASSHomeController {
    private SignInPageController sic;
    public void setSic(SignInPageController sic) {
        this.sic = sic;
    }

    @FXML
    private Button balanceBTN;

    @FXML
    private Button profileBTN;

    @FXML
    private Button suggestionsBTN;

    @FXML
    private Button ticketShopBTN;

    public String id;

    public void setId(String id) {
        this.id = id;
    }

    @FXML
    void balanceBTN() {

    }

    @FXML
    void profileBTN() {

        FXMLLoader ld = new FXMLLoader(getClass().getResource("/view/PASSProfile.fxml"));
        try {
            ld.load();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        PASSProfilecontroller ppc = ld.getController();
        ppc.setPhc(this);
        ppc.setLabels(id);
        ppc.setId(id);
        profileBTN.getScene().getWindow().hide();
        Scene scene = new Scene(ld.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
//        PASSProfilecontroller ppc = new PASSProfilecontroller();
//        ppc.setLabels(sic.ID);
//        loadPage("/view/PASSProfile.fxml");
    }

    @FXML
    void suggestionsBTN() {
        System.out.println(sic.ID);
    }

    @FXML
    void ticketShopBTN() {

    }
    private void loadPage(String pageAddress) {
        FXMLLoader ld = new FXMLLoader(getClass().getResource(pageAddress));
        PASSProfilecontroller ppc = ld.getController();
        ppc.setPhc(this);
        try {
            ld.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(ld.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
//        Parent root;
//        try {
//            ((Stage) profileBTN.getScene().getWindow()).close();
//            root = FXMLLoader.load(getClass().getResource(pageAddress));
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }

    }

//    public void setId(String ID) {
//        id = ID;
//    }
}
