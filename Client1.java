//package com.kasra.workshop9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client1 {
    public static void main(String[] args) {
        try {
            // Create Socket object for client.
            Socket socket = new Socket("localhost", 1234);


            // Create PrintWriter object for send information to server.
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connected to chat server");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //print results
            StringBuffer output=new StringBuffer();
            String line;
            while ( (line= in.readLine()) !=null){
                if (line.isEmpty()) break;
                output.append(line).append('\n');
            }
            System.out.println(output);



            // Send number to server with PrintWriter object.
            Scanner input = new Scanner(System.in);
            String massage = input.nextLine();






            out.println(encrypt(massage));

            // Create BufferedReader object for receiving result from server.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //print results
            output=new StringBuffer();
            while ( (line= in.readLine()) !=null){
                if (line.isEmpty()) break;
                output.append(line).append('\n');
            }
            System.out.println(output);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //A simple encrypting text
    public static String encrypt(String input){
        StringBuffer temp=new StringBuffer();
        char[] arr=input.toCharArray();
        for(char s: arr){
            temp.append(Character.toChars(s*5+17));
        }
        return temp.toString();

    }
}
