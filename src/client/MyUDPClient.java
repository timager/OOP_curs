package client;

import server.Server;

import java.io.IOException;
import java.net.*;

public class MyUDPClient {
private DatagramSocket socket;
    private byte[] input = new byte[256];
    private byte[] output = new byte[256];
    private InetAddress address;
    public static final int PORT = 1488;


    public MyUDPClient() {
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

    public void send(String command){
        System.out.println("Отправляю "+command);
        try {
            output = command.getBytes();
            DatagramPacket packet = new DatagramPacket(output, output.length, address, PORT);
            socket.send(packet);
            DatagramPacket inputPacket = new DatagramPacket(input, input.length);
            socket.receive(inputPacket);
            System.out.println(new String(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
