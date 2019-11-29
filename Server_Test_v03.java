import java.io.*;
import java.net.*;
import java.util.*;
public class Server_Test_v03 implements Server_Conns{
        
    public static void main(String[] args) throws UnknownHostException {
        int port = 4444;
        String input="";
        String server_input="";
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        try (ServerSocket servSock = new ServerSocket(port,ip)){
            System.out.println("Server is on " +ip+ ":"+port);
            while (true){//(!((server_input=System.console().readLine()).equals("exit")))  {
                Socket socket = servSock.accept();
                conn_list.add(socket);
                System.out.println("New client connected " + socket);
                new Server_Test_Thread_v03(socket).start();
            }}
        catch (IOException ex){
            System.out.println("Server exception");}
      //  catch (UnknownHostException ex){
        //    System.out.println("IP IS TAKEN");}
}}
