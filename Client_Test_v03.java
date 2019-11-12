import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client_Test_v03{
    public static void main(String[] args){
        String ip = "127.234.243.1";
        int port = 65000;
        String userInput="";
        String test;
        Scanner input = new Scanner(System.in);
        try (Socket socket = new Socket(ip,port)){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while  (!(userInput.equals("bye"))){
                userInput = System.console().readLine();
                System.out.println("User input= "+userInput);
                out.println(userInput);
                test = in.readLine();
                System.out.println("echoed this: "+test);
                }
        }
        catch (UnknownHostException ex){System.out.println("Server not found");}
        catch (IOException ex){System.out.println("I/O error");}
}}
