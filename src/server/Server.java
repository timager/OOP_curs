package server;

import client.Packet;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server extends Thread {
    public static final int BYTE_LENGTH = 1024;

    public static final String START_SIMULATION = "start_sid";
    public static final String STOP_SIMULATION = "stop_sim";
    public static final String GET_SIMULATION_DATA = "get_data";
    public static final String CLEAR_DATA = "clear_data";

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
                sleep(SLEEP);

            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(Building building) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(building);
        oos.flush();
        DatagramPacket outputPacket = new DatagramPacket(os.toByteArray(), os.toByteArray().length, address, port);
        socket.send(outputPacket);
    }

    private void switchCommand(Packet packet) throws IOException {
        switch (packet.getCommand()) {
            case Server.GET_SIMULATION_DATA:
                System.out.println("Данные отправлены");
                break;
            case Server.START_SIMULATION:
                building.setWorked(true);
                building.setP(packet.getP());
                building.setT(packet.getT());
                building.getCarWash().setR1(packet.getR1());
                building.getVehicleInspection().setR2(packet.getR2());
                System.out.println("Симуляция запущена");
                break;
            case Server.STOP_SIMULATION:
                building.setWorked(false);
                System.out.println("Симуляция остановлена");
                break;
            case Server.CLEAR_DATA:{
                building.setWorked(false);
                building.getMaster().setFabric(building.getRepairShop());
                building.getRepairShop().clear();
                building.getCarWash().clear();
                building.getVehicleInspection().clear();
                System.out.println("Симуляция очищена");
                break;
            }
            default:
                System.out.println("Команда не распознана");
        }
        System.out.println("\n");
        send(building);
    }

    private void getPacket() throws IOException, ClassNotFoundException {
        byte[] input = new byte[BYTE_LENGTH];
        DatagramPacket inputPacket = new DatagramPacket(input, input.length);
        socket.receive(inputPacket);
        address = inputPacket.getAddress();
        port = inputPacket.getPort();
        InputStream is = new ByteArrayInputStream(input);
        ObjectInputStream ois = new ObjectInputStream(is);
        Packet packet = (Packet)ois.readObject();
        System.out.println("Получил " +  packet.getCommand());
        switchCommand( packet);
    }

    public static void main(String[] args) throws SocketException {
        Server server = new Server();
        server.start();
    }
}
