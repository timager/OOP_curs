package client;

import server.Building;
import server.Server;

import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;

public class Client extends JFrame {
    private DatagramSocket socket;
    private byte[] input = new byte[Server.BYTE_LENGTH];
    private InetAddress address;

    private Client() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void send(String command) throws IOException, ClassNotFoundException {
        System.out.println("Отправляю " + command);

        byte[] output = command.getBytes();
        DatagramPacket packet = new DatagramPacket(output, output.length, address, Server.PORT);
        socket.send(packet);
        DatagramPacket inputPacket = new DatagramPacket(input, input.length);
        socket.receive(inputPacket);

        InputStream is = new ByteArrayInputStream(input);
        ObjectInputStream ois = new ObjectInputStream(is);
        Building building = (Building) ois.readObject();

        System.out.println(building);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Client client = new Client();
        client.send(Server.START_SIMULATION);
        Thread.sleep(Server.SLEEP);
        for(int i=0;i<=20;i++){
            client.send(Server.GET_SIMULATION_DATA);
            Thread.sleep(Server.SLEEP);
        }
        client.send(Server.STOP_SIMULATION);
    }


}
