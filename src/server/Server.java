package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {
    public static final int BYTE_LENGTH = 1024;

    public static final String START_SIMULATION = "start_sid";
    public static final String STOP_SIMULATION = "stop_sim";
    public static final String GET_SIMULATION_DATA = "get_data";

    public static long SLEEP = 100;

    private DatagramSocket socket;

    public static final int PORT = 1488;

    private int port;
    private InetAddress address;
    private Building building;

    private Server() throws SocketException {
        socket = new DatagramSocket(PORT);
        building = new Building(3, 4, 0.5, 1);
    }

    @Override
    public void run() {
        while (true) {
            if (building.isWorked()) {
                building.inputCar();
                building.getMaster().work();
            }
            try {
                getPacket();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String data) throws IOException {
        byte[] output = data.getBytes();
        DatagramPacket outputPacket = new DatagramPacket(output, output.length, address, port);
        socket.send(outputPacket);
    }

    private void send(Building building) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(building);
        oos.flush();
        DatagramPacket outputPacket = new DatagramPacket(os.toByteArray(), os.toByteArray().length, address, port);
        socket.send(outputPacket);
    }

    private void switchCommand(String command) throws IOException {
        switch (command) {
            case Server.GET_SIMULATION_DATA:
                System.out.println("Данные отправлены");
                break;
            case Server.START_SIMULATION:
                building.setWorked(true);
                System.out.println("Симуляция запущена");
                break;
            case Server.STOP_SIMULATION:
                building.setWorked(false);
                System.out.println("Симуляция остановлена");
                break;
            default:
                System.out.println("Команда не распознана");
        }
        System.out.println("\n");
        send(building);
    }

    private void getPacket() throws IOException {
        byte[] input = new byte[BYTE_LENGTH];
        DatagramPacket inputPacket = new DatagramPacket(input, input.length);
        socket.receive(inputPacket);
        address = inputPacket.getAddress();
        port = inputPacket.getPort();
        String command = new String(input).trim();
        System.out.println("Получил " + command);
        switchCommand(command);
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server();
        server.start();
    }
}
