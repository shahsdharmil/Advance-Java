import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerSide {

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Server();
    }
}

class Server {
    private ServerSocket ss;
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;
    private Scanner sc;

    Server() throws UnknownHostException, IOException {
        ss = new ServerSocket(9999);
        socket = ss.accept();
        sc = new Scanner(System.in);

        while (true) {
            String str = receive();
            if (str.equals("break"))
                break;
            send();
        }

        // socket.close();
        // ss.close();
        sc.close();

    }

    public void send() throws IOException {
        os = new DataOutputStream(socket.getOutputStream());
        System.out.print("2 : ");
        os.writeUTF(sc.nextLine());
    }

    public String receive() throws IOException {
        is = new DataInputStream(socket.getInputStream());
        String str;

        str = is.readUTF();
        System.out.println("1 : " + str);

        return str;
    }
}