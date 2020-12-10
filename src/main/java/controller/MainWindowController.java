package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import utils.Helpers;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        if (guestCheck(e)) { return; }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }

    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        if (guestCheck(e)) { return; }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }

    @FXML
    private void handleShowAllNurses(ActionEvent e) {
        if (guestCheck(e)) { return; }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllNurseView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllNurseController controller = loader.getController();
    }

    @FXML
    private void handleShowLogin(ActionEvent e) {
        if (!guestCheck()) { return; }
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LoginController controller = loader.getController();
    }

    private Boolean guestCheck(ActionEvent e) {
        if (Helpers.loggedIn)
            return false;

        handleShowLogin(e);
        return true;
    }

    private Boolean guestCheck() {
        if (Helpers.loggedIn)
            return false;

        return true;
    }
}
