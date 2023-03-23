import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideSORT {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(12345);
        System.out.println("Server started");
        Socket s = ss.accept();
        PrintWriter p = new PrintWriter(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = in.readLine();
        int n = Integer.parseInt(str);
        System.out.println("Client want to sort " + n + " Numbers");
        String sarr[] = new String[n];
        int arr[] = new int[n];
        int swap, c, d;
        System.out.println("Received numbers::\n");
        for (int i = 0; i < n; i++) {
            sarr[i] = in.readLine();
            arr[i] = Integer.parseInt(sarr[i]);
            System.out.println("no. " + i + "="
                    + arr[i]);
        }

        for (c = 0; c < (n - 1); c++) {
            for (d = 0; d < n - c - 1; d++) {
                if (arr[d] > arr[d + 1]) {
                    swap = arr[d];
                    arr[d] = arr[d + 1];
                    arr[d + 1] = swap;
                }
            }
        }
        System.out.println("\nSorted list of numbers ");

        String sendarr = new String();
        for (c = 0; c < n; c++) {
            sendarr += "\nNum (" + c + ")=" + arr[c];
        }
        System.out.println(sendarr);
        p.println(sendarr);
        p.flush();
        s.close();
    }
}