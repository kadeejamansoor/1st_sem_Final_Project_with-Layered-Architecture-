package lk.ijse.projectseaw.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.BookingInformationBO;
import lk.ijse.projectseaw.bo.custom.impl.GuestManagementBOImpl;
import lk.ijse.projectseaw.dto.GuestDTO;
import lk.ijse.projectseaw.dto.tm.GuestTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GuestManagementFormController implements Initializable {

    GuestManagementBOImpl guestManagementBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.GUESTMANAGEMENT_BO);

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colCountry;

    @FXML
    private TableColumn<?, ?> colGuestCity;

    @FXML
    private TableColumn<?, ?> colGuestFullName;

    @FXML
    private TableColumn<?, ?> colGuestId;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableView<GuestTm> tblGuest;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtGuestFullName;

    @FXML
    private TextField txtGuestId;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllGuest();
    }

    private void setCellValueFactory() {
        colGuestId.setCellValueFactory(new PropertyValueFactory<>("GuestId"));
        colGuestFullName.setCellValueFactory(new PropertyValueFactory<>("GuestFullname"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("GuestAddress"));
        colGuestCity.setCellValueFactory(new PropertyValueFactory<>("GuestCity"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("GuestPostalcode"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("GuestCountry"));
        colState.setCellValueFactory(new PropertyValueFactory<>("GuestState"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("GuestAge"));
    }

    void loadAllGuest(){
        tblGuest.getItems().clear();

        try {
            ArrayList<GuestDTO> allSalaries = guestManagementBO.getAllGuests();
            for (GuestDTO g : allSalaries) {
                tblGuest.getItems().add(new GuestTm(g.getGuestId(), g.getFullname(), g.getAddress(), g.getCity(), g.getPostalcode(), g.getCountry(), g.getEmail(), g.getAge()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtGuestId.clear();
        txtGuestFullName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtPostalCode.clear();
        txtCountry.clear();
        txtState.clear();
        txtAge.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String salaryId = txtGuestId.getText();

        boolean b = guestManagementBO.deleteGuest(salaryId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            btnClearOnAction(event);
            loadAllGuest();
            new Alert(Alert.AlertType.CONFIRMATION, "guest has been deleted successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String guestId = txtGuestId.getText();
        String fullname = txtGuestFullName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String postalcode = txtPostalCode.getText();
        String country = txtCountry.getText();
        String customerEmail = txtState.getText();
        int ageText = Integer.parseInt(txtAge.getText());

        boolean b = guestManagementBO.updateGuest(new GuestDTO(guestId, fullname, address, city, postalcode, country, customerEmail, ageText));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            loadAllGuest();
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "guest has been updated successfully").show();
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String guestId = txtGuestId.getText();

        GuestDTO g = guestManagementBO.searchGuest(guestId);



        if (existGuest(guestId)) {
            txtGuestId.setText(g.getGuestId());
            txtGuestFullName.setText(g.getFullname());
            txtAddress.setText(g.getAddress());
            txtCity.setText(g.getCity());
            txtPostalCode.setText(g.getPostalcode());
            txtCountry.setText(g.getCountry());
            txtState.setText(g.getEmail());
            txtAge.setText(String.valueOf(g.getAge()));
        }else {
            new Alert(Alert.AlertType.ERROR, "There is no such guest associated with the id").show();
        }
    }

    boolean existGuest(String id) throws SQLException, ClassNotFoundException {
        return guestManagementBO.existGuestByCode(id);
    }


}
