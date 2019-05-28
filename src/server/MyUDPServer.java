package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class MyUDPServer  extends Thread{
    private DatagramSocket socket;
    private byte[] input = new byte[256];
    private byte[] output = new byte[256];

    public static final int PORT = 1488;

    MyUDPServer(){
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){


            try {
                DatagramPacket inputPacket = new DatagramPacket(input, input.length);
                socket.receive(inputPacket);
                InetAddress address = inputPacket.getAddress();
                int port = inputPacket.getPort();
                System.out.println((new String(input)));
                if((new String(input)).startsWith("Саня хуй соси")){
                    System.out.println("ща ответ ебну");
                    output = "ок сосу".getBytes();
                    DatagramPacket outputPacket = new DatagramPacket(output, output.length, address, port);
                    socket.send(outputPacket);
                }
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
