import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSideSORT {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 12345);
        PrintWriter p = new PrintWriter(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader ink = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("How many numbers to sort? ");
        int num = Integer.parseInt(ink.readLine());
        p.println(num);
        p.flush();
        System.out.println("Enter " + num + " numbers to sort :");
        String sarr[] = new String[num];
        for (int i = 0; i < num; i++) {
            System.out.print("no. " + (int) (i + 1) + "=");
            sarr[i] = ink.readLine();
            p.println(sarr[i]);
            p.flush();
        }
        String res;
        System.out.println("\nSorted array::\n");
        while ((res = in.readLine()) != null) {
            System.out.println(res);
        }
        s.close();

    }
}
