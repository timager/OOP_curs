package client;

import server.Server;

import javax.swing.*;

public class Client extends JFrame {
    private Client(){
        super("Автомастерская");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 130);
        setVisible(true);
    }
    public static void main(String[] args) {
        MyUDPClient client = new MyUDPClient();
        client.send(Server.GET_SIMULATION_DATA);
    }
}