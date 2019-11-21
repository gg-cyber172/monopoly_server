public class Server_Test_Thread_v03 extends Thread implements Server_Conns{
    private Socket socket; //Keeps track of it's socket
    public Server_Test_Thread_v03(Socket socket){
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out1 = new PrintWriter(socket.getOutputStream(), true);
            String input="";
            do {  
                input = in.readLine();
                System.out.println(socket.getInetAddress()+" : "+input); 
                if (input.equals("bye")){
                    System.out.println(socket.getInetAddress()+" disconnected");
                    out1.println("Goodbye");}
                else{ 
                    for (Socket out:conn_list){
                        PrintWriter output = new PrintWriter(out.getOutputStream(), true);
                        System.out.println("Attempting to print " +input +" to "+out.getInetAddress()+":"+out.getPort());
                        output.println(input);
                        }}

                }while (!input.equals("bye"));
            conn_list.remove(socket);
            socket.close();
            
        } catch (IOException ex) {
            System.out.println("Server exception: ");
            ex.printStackTrace();
        }
    }
}
