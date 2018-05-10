package com.server;

import java.io.*;
import java.net.*;

/**
 * Created by matias on 6/5/18.
 */
public class Server {

    private static final String ADDRESS = "255.255.255.255";
    private static final Integer PORT1= 1024;
    private static final Integer PORT2= 1026;

    public void readFile(String file) throws IOException, InterruptedException {
        DatagramSocket socket = new DatagramSocket(PORT1);
        DatagramSocket socket2 = new DatagramSocket(PORT2);
        socket.setBroadcast(true);
        socket2.setBroadcast(true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                this.getClass().getResourceAsStream("/" + file)));
        byte[] buf = new byte[1000];
        DatagramPacket dgp = new DatagramPacket(buf, buf.length);
        String line;
        InetAddress address = InetAddress.getByName(ADDRESS);

        while(true) {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                DatagramPacket pck = new DatagramPacket (line.getBytes(),line.getBytes().length,address,PORT1);
                socket.send(pck);
                DatagramPacket pck2 = new DatagramPacket (line.getBytes(),line.getBytes().length,address,PORT2);
                socket2.send(pck2);
                Thread.sleep(3000);

            }
            reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + file)));
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.readFile("mediciones.txt");

    }
}
