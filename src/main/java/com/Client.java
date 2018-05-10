package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by matias on 10/5/18.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1026);
        Socket s = server.accept();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(s.getInputStream()));
        String line = null;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
