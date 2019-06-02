package client;

import server.Building;
import server.Server;

import java.io.*;
import java.net.*;

class Client{
    private DatagramSocket socket;
    private byte[] input = new byte[Server.BYTE_LENGTH];
    private InetAddress address;

    Client() {
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

    Building send(Packet packet) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(packet);
        oos.flush();
        DatagramPacket outputPacket = new DatagramPacket(os.toByteArray(), os.toByteArray().length, address, Server.PORT);
        socket.send(outputPacket);

        DatagramPacket inputPacket = new DatagramPacket(input, input.length);
        socket.receive(inputPacket);

        InputStream is = new ByteArrayInputStream(input);
        ObjectInputStream ois = new ObjectInputStream(is);
        return (Building) ois.readObject();
    }
}
