package client;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import server.Building;
import server.Server;

import java.io.IOException;

public class Controller extends Thread {
    private Client client = new Client();
    private Building building;
    private boolean exit = false;
    private Draw draw;

    @FXML
    private Canvas repairShopCanvas, carWashCanvas, vehicleInspectionCanvas;
    @FXML
    private Button startSimulationBtn, stopSimulationBtn;
    @FXML
    private TextField fieldR1, fieldR2, fieldP, fieldT;

    @FXML
    public void initialize() {
        try {
            draw = new Draw(repairShopCanvas, carWashCanvas, vehicleInspectionCanvas);
            getSimulationData();
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
        fieldR1.setText("3");
        fieldR2.setText("4");
        fieldP.setText("0.5");
        fieldT.setText("1");
        onlyInt(fieldR1);
        onlyInt(fieldR2);
        onlyFloat(fieldP);
        onlyInt(fieldT);
        start();
    }

    private void onlyInt(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void onlyFloat(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                field.setText(newValue.replaceAll("[^\\d{0,7}([\\.]\\d{0,4})?]", ""));
            }
        });
    }

    @FXML
    private void startSimulation() {
        try {

            Packet packet = new Packet();
            packet.setCommand(Server.START_SIMULATION);
            packet.setR1(Long.parseLong(fieldR1.getText()));
            packet.setR2(Long.parseLong(fieldR2.getText()));
            packet.setP(Double.parseDouble(fieldP.getText()));
            packet.setT(Long.parseLong(fieldT.getText()));

            building = client.send(packet);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    @FXML
    private void stopSimulation() {
        try {
            Packet packet = new Packet();
            packet.setCommand(Server.STOP_SIMULATION);
            building = client.send(packet);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    @FXML
    private void clearSimulation() {
        try {
            Packet packet = new Packet();
            packet.setCommand(Server.CLEAR_DATA);
            building = client.send(packet);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    private void alert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                getSimulationData();
            } catch (IOException | ClassNotFoundException e) {
                exit = true;
                alert("Произошла ошибка, перезапустите программу", e.getMessage());
            }
        }
    }

    private void getSimulationData() throws IOException, ClassNotFoundException {
        Packet packet = new Packet();
        packet.setCommand(Server.GET_SIMULATION_DATA);
        building = client.send(packet);
        startSimulationBtn.setDisable(building.isWorked());
        stopSimulationBtn.setDisable(!building.isWorked());
        fieldR1.setDisable(building.isWorked());
        fieldR2.setDisable(building.isWorked());
        fieldP.setDisable(building.isWorked());
        fieldT.setDisable(building.isWorked());

        if (building.isWorked()) {
            fieldR1.setText(String.valueOf(building.getCarWash().getR1()));
            fieldR2.setText(String.valueOf(building.getVehicleInspection().getR2()));
            fieldP.setText(String.valueOf(building.P));
            fieldT.setText(String.valueOf(building.T));
        }

        System.out.println(building);
        draw.drawBuilding(building);
    }
}
