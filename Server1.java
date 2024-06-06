//package com.kasra.workshop9;

// Java implementation of Server side
// It contains two classes : Server and ClientHandler

import java.io.*;
import java.net.*;


// Server class
public class Server1 {
    public static StringBuffer massages = new StringBuffer();

    // counter for clients

    public static void main(String[] args) throws IOException {
        // server is listening on port 1234
        ServerSocket ss = new ServerSocket(1234);

        Socket socket;
        System.out.println("Chat Server is running on port 1234");

        // running infinite loop for getting
        // client request
        while (true) {
            // Accept the incoming request
            socket = ss.accept();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println(Server1.massages);


            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));



            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(socket, in);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);


            // start the thread.
            t.start();


        }
    }
}

class ClientHandler implements Runnable {
    final BufferedReader br;
    Socket s;
    static int i = 1;


    // constructor
    public ClientHandler(Socket s, BufferedReader br) {

        this.br = br;
        this.s = s;

    }

    @Override
    public void run() {

        String received;

        try {
            // receive the string
            received = br.readLine();
            String massage = "user " + i + " : " + decrypt(received);
            Server1.massages.append(massage).append('\n');


            // increment i for new client.
            // i is used for naming only, and can be replaced
            // by any naming scheme
            i++;


            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            out.println(Server1.massages);


        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    public static String decrypt(String input){
        StringBuffer temp=new StringBuffer();
        char[] arr=input.toCharArray();
        for(char s: arr){
            temp.append(Character.toChars((s-17)/5));
        }
        return temp.toString();
    }
}



