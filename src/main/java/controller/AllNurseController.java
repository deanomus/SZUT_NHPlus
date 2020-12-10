package controller;

import datastorage.NurseDAO;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Nurse;
import datastorage.DAOFactory;
import utils.Helpers;

import java.sql.SQLException;
import java.util.List;

public class AllNurseController {


    @FXML
    private TableView<Nurse> tableView;
    @FXML
    private TableColumn<Nurse, Integer> colId;
    @FXML
    private TableColumn<Nurse, String>
            colFirstName,
            colSurname,
            colTelephone;


    @FXML
    Button
            btnAdd,
            btnDel;

    @FXML
    TextField
            txfFirstname,
            txfSurname,
            txfTelephone,
            txfPassword;

    private ObservableList<Nurse> tableviewContent = FXCollections.observableArrayList();
    private NurseDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colId.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("id"));

        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Nurse, String>("firstName"));
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Nurse, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Nurse, String>("telephone"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tableView.setItems(this.tableviewContent);
    }


    /**
     *
     * Handles if firstname get edited
     *
     * @param event
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Nurse, String> event) {
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * Handles if surname get edited
     *
     * @param event
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Nurse, String> event) {
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * Handles if telephone get edited
     *
     * @param event
     */
    public void handleOnEditTelephone(TableColumn.CellEditEvent<Nurse, String> event){
        event.getRowValue().setTelephone(event.getNewValue());
        doUpdate(event);
    }

    /**
     * do write the updates/edits in database
     *
     * @param t
     */
    private void doUpdate(TableColumn.CellEditEvent<Nurse, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * read als nurses
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createNurseDAO();
        List<Nurse> allNurses;
        try {
            allNurses = dao.readAll();
            for (Nurse p : allNurses) {
                this.tableviewContent.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link NurseDAO}
     */
    @FXML
    public void handleDeleteRow() {
        Nurse selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(selectedItem);
        try {
            dao.deleteById((int) selectedItem.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link NurseDAO}
     */
    @FXML
    public void handleAdd() {
        String
                firstname = this.txfFirstname.getText(),
                surname = this.txfSurname.getText(),
                telephone = this.txfTelephone.getText(),
                password = this.txfPassword.getText(),
                hash = Helpers.md5hash(password);

        try {
            Nurse nurse = new Nurse(null, firstname, surname, telephone, hash);
            dao.create(nurse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txfFirstname.clear();
        this.txfSurname.clear();
        this.txfTelephone.clear();
        this.txfPassword.clear();
    }


}
