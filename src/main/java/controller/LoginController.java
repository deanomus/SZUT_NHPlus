package controller;



import datastorage.DAOFactory;
import datastorage.NurseDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Nurse;
import utils.Helpers;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class LoginController {

    private NurseDAO nurseDAO;

    @FXML
    private TextField
                txfTelephone,
                txfPassword;

    @FXML
    private Button
                btnLogin;

    public void initialize() {
        this.nurseDAO = DAOFactory.getDAOFactory().createNurseDAO();
        LoginWindow();
    }

    public void LoginWindow() {

    }


    /**
     * handle actions on login button
     */
    @FXML
    public void handleLogin() {
        String
                number = this.txfTelephone.getText(),
                password = this.txfPassword.getText(),
                pwHash = Helpers.md5hash(password);

        if (check(number, pwHash)) {
            Helpers.loggedIn = true;
        } else {
            this.txfPassword.clear();
        }

    }


    /**
     * check if provided login credentials valid
     *
     * @param number
     * @param pwHash
     * @return
     */
    private Boolean check(String number, String pwHash) {
        try {
            for (Nurse nurse: nurseDAO.readAll()) {
                if (nurse.getTelephone() == null) {
                    continue;
                }
                if (nurse.getPasswordHash() == null) {
                    continue;
                }

                if (nurse.getTelephone().equalsIgnoreCase(number)) {
                    if (nurse.getPasswordHash().equalsIgnoreCase(pwHash)) {
                        return true;
                    }
                    // return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;

    }

}
