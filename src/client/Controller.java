package client;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import server.Server;

import java.io.IOException;

public class Controller {
    private Client client = new Client();

    @FXML
    private Button startSimulationBtn;
    @FXML
    private Button stopSimulationBtn;

    @FXML
    private void startSimulation() {
        try {
            client.send(Server.START_SIMULATION);
            startSimulationBtn.setDisable(true);
            stopSimulationBtn.setDisable(false);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    @FXML
    private void stopSimulation() {
        try {
            client.send(Server.STOP_SIMULATION);
            stopSimulationBtn.setDisable(true);
            startSimulationBtn.setDisable(false);
        } catch (IOException | ClassNotFoundException e) {
            alert("Произошла ошибка", e.getMessage());
        }
    }

    private void alert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
