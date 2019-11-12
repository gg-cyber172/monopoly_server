import java.io.*;
import java.net.*;
import java.util.*;
public class Server_Test_v03 implements Server_Conns{
        
    public static void main(String[] args) throws UnknownHostException {
        int port = 4444;
        String input="";
        String server_input="";
        InetAddress ip = InetAddress.getByName("127.123.232.1");
        try (ServerSocket servSock = new ServerSocket(port,5,ip)){
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
public interface Server_Conns{
       public ArrayList<Socket> conn_list= new ArrayList<Socket>();//[5];
}
public class Server_Test_Thread_v03 extends Thread implements Server_Conns{
    private Socket socket;
   // public ArrayList<Socket> conn_list;
    public Server_Test_Thread_v03(Socket socket) {
        this.socket = socket;
//        conn_list.add(new PrintWriter(socket.getOutputStream(), true));
    }
    public void add_to_conn_list(Socket socket){conn_list.add(socket);}
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out1 = new PrintWriter(socket.getOutputStream(), true);
            //conn_list.add(out1);
 
            String input="";
 
            do {  
                input = in.readLine();
                System.out.println(socket.getInetAddress()+" : "+input);  
                for (Socket out:conn_list){
                    PrintWriter output = new PrintWriter(out.getOutputStream(), true);
                    System.out.println("Attempting to print " +input +" to "+out.getInetAddress()+":"+out.getPort());
                    output.println(input);
                    }
                //out1.println(input);
                }while (!input.equals("bye"));
 
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: ");
            ex.printStackTrace();
        }
    }
}
