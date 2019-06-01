package client;

import server.Building;
import server.Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.*;

class MyUDPClient {
    private DatagramSocket socket;
    private byte[] input = new byte[256];
    private InetAddress address;
    private static final int PORT = 1488;


    MyUDPClient() {
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

    void send(String command) throws IOException, ClassNotFoundException {
        System.out.println("Отправляю " + command);

        byte[] output = command.getBytes();
        DatagramPacket packet = new DatagramPacket(output, output.length, address, PORT);
        socket.send(packet);
        DatagramPacket inputPacket = new DatagramPacket(input, input.length);
        socket.receive(inputPacket);

        InputStream is = new ByteArrayInputStream(input);
        ObjectInputStream ois = new ObjectInputStream(is);
        Building building = (Building) ois.readObject();

        System.out.println(building);

    }
}
