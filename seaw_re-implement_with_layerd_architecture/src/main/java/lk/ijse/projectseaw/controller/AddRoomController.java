package lk.ijse.projectseaw.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.projectseaw.bo.BoFactory;
import lk.ijse.projectseaw.bo.custom.AddRoomBO;
import lk.ijse.projectseaw.dto.RoomDTO;
import lk.ijse.projectseaw.dto.RoomStatusDTO;
import lk.ijse.projectseaw.entity.RoomStatus;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRoomController implements Initializable {

    AddRoomBO addRoomBO =  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ADDROOM_BO);

    @FXML
    private ComboBox<String> cmbRoomtype;

    @FXML
    private TextField txtCapacity;

    @FXML
    private TextField txtFloorNumber;

    @FXML
    private TextField txtRate;

    @FXML
    private TextField txtRoomid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> RoomTypeList = FXCollections.observableArrayList();
        RoomTypeList.add("Standard");
        RoomTypeList.add("Deluxe");
        RoomTypeList.add("Superior");

        cmbRoomtype.setItems(RoomTypeList);
        txtRoomid.setText(generateNewId());
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtRoomid.clear();
        cmbRoomtype.getSelectionModel().clearSelection();
        txtCapacity.clear();
        txtFloorNumber.clear();
        txtRate.clear();
    }

    @FXML
    void btnCodeSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String roomId = txtRoomid.getText();

        RoomStatusDTO r = addRoomBO.searchRoom(roomId);

        if (existRoom(roomId )) {
            txtRoomid.setText(r.getRoom_id());
            cmbRoomtype.setValue(r.getRoom_type());
            txtCapacity.setText(String.valueOf(r.getCapacity()));
            txtFloorNumber.setText(String.valueOf(r.getFloor_Number()));
            txtRate.setText(String.valueOf(r.getRate()));
        }else {
            new Alert(Alert.AlertType.ERROR, "There is no such room associated with the id").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String roomId = txtRoomid.getText();

        boolean b = addRoomBO.deleteRoom(roomId);
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "room has been deleted successfully").show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String roomId = txtRoomid.getText();
        String roomType = cmbRoomtype.getValue();
        int capacity = Integer.parseInt(txtCapacity.getText());
        String capacityText = txtCapacity.getText();
        String floorNumberText = txtFloorNumber.getText();
        int floorNumber = Integer.parseInt(txtFloorNumber.getText());
        double rate = Double.parseDouble(txtRate.getText());
        String rateText = txtRate.getText();

        if (roomId.isEmpty() || roomType.isEmpty() || capacityText.isEmpty() || floorNumberText.isEmpty() || rateText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (capacity <= 0) {
            new Alert(Alert.AlertType.WARNING, "Invalid capacity. Please enter a valid number!").show();
            txtCapacity.requestFocus();
            return;
        }else if (floorNumber <= 0) {
            new Alert(Alert.AlertType.WARNING, "Invalid floor number. Please enter valid number!").show();
            txtCapacity.requestFocus();
            return;
        }else if (!Double.toString(rate).matches("^\\d+(\\.\\d+)?$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid rate. Please enter a valid number!").show();
            txtCapacity.requestFocus();
            return;
        }


        boolean b = addRoomBO.saveRoom(new RoomStatusDTO(roomId, roomType, capacity, floorNumber, rate, "available", null));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "room added successfully").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String roomId = txtRoomid.getText();
        String roomType = cmbRoomtype.getValue();
        int capacity = Integer.parseInt(txtCapacity.getText());
        String capacityText = txtCapacity.getText();
        int floorNumber = Integer.parseInt(txtFloorNumber.getText());
        String floorNumberText = txtFloorNumber.getText();
        double rate = Double.parseDouble(txtRate.getText());
        String rateText = txtRate.getText();

        if (roomId.isEmpty() || roomType.isEmpty() || capacityText.isEmpty() || floorNumberText.isEmpty() || rateText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required.").show();
            return;
        }else if (capacity <= 0) {
            new Alert(Alert.AlertType.WARNING, "Invalid capacity. Please enter a valid number!").show();
            txtCapacity.requestFocus();
            return;
        }else if (floorNumber <= 0) {
            new Alert(Alert.AlertType.WARNING, "Invalid floor number. Please enter valid number!").show();
            txtCapacity.requestFocus();
            return;
        }else if (!Double.toString(rate).matches("^\\d+(\\.\\d+)?$")) {
            new Alert(Alert.AlertType.WARNING, "Invalid rate. Please enter a valid number!").show();
            txtCapacity.requestFocus();
            return;
        }

        boolean b = addRoomBO.updateRoom(new RoomStatusDTO(roomId, roomType, capacity, floorNumber, rate, "available", null));
        if (!b) {
            new Alert(Alert.AlertType.ERROR, "something wrong").show();
        } else {
            btnClearOnAction(event);
            new Alert(Alert.AlertType.CONFIRMATION, "room has been updated successfully").show();
        }
    }

    boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return addRoomBO.existRoomByCode(id);
    }

    private String generateNewId() {
        try {
            return addRoomBO.generateNewRoomID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


}
