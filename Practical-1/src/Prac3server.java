
// Server 
import java.io.*;
import java.net.*;

public class Prac3server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6664);
            Socket s = ss.accept();
            System.out.println("Connection is stable now");
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
            System.out.println("message= " + str);
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}