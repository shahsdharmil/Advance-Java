import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSide {

    public static void main(String[] args) throws UnknownHostException, IOException {
        new Client();
    }
}

class Client {
    private Socket socket;
    private DataInputStream is;
    private DataOutputStream os;
    private Scanner sc;

    Client() throws UnknownHostException, IOException {
        socket = new Socket("127.0.0.1", 9999);
        sc = new Scanner(System.in);

        while (true) {
            send();
            String str = receive();
            if (str.equals("break"))
                break;
        }

        // socket.close();
        sc.close();
    }

    public void send() throws IOException {
        os = new DataOutputStream(socket.getOutputStream());
        System.out.print("1 : ");
        os.writeUTF(sc.nextLine());
    }

    public String receive() throws IOException {
        is = new DataInputStream(socket.getInputStream());
        String str;

        str = is.readUTF();
        System.out.println("2 : " + str);

        return str;
    }
}