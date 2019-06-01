package client;

import server.Server;

import javax.swing.*;
import java.io.IOException;

public class Client extends JFrame {
    private Client(){
        super("Автомастерская");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 130);
        setVisible(true);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        MyUDPClient client = new MyUDPClient();
        client.send(Server.START_SIMULATION);
        Thread.sleep(Server.SLEEP);
        for(int i=0;i<=20;i++){
            client.send(Server.GET_SIMULATION_DATA);
            Thread.sleep(Server.SLEEP);
        }
        client.send(Server.STOP_SIMULATION);
    }
}
