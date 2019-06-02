package client;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import server.Building;
import server.Server;

import java.io.IOException;

public class Controller extends Thread {
    private Client client = new Client();
    private Building building;
    private boolean exit = false;
    private Draw draw;

    @FXML
    private Canvas repairShopCanvas,carWashCanvas,vehicleInspectionCanvas;
    @FXML
    private Button startSimulationBtn;
    @FXML
    private Button stopSimulationBtn;

    @FXML
    public void initialize() {
        try {
            draw = new Draw(repairShopCanvas,carWashCanvas,vehicleInspectionCanvas);
            getSimulationData();
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
        start();
    }

    @FXML
    private void startSimulation() {
        try {
            building = client.send(Server.START_SIMULATION);
            startSimulationBtn.setDisable(true);
            stopSimulationBtn.setDisable(false);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    @FXML
    private void stopSimulation() {
        try {
            building = client.send(Server.STOP_SIMULATION);
            stopSimulationBtn.setDisable(true);
            startSimulationBtn.setDisable(false);
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
        building = client.send(Server.GET_SIMULATION_DATA);
        startSimulationBtn.setDisable(building.isWorked());
        stopSimulationBtn.setDisable(!building.isWorked());
        System.out.println(building);
        draw.drawBuilding(building);
    }
}
