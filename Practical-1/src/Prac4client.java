import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Prac4client {
    public static void main(String[] args) {
        try {
            // Create a UDP socket
            DatagramSocket socket = new DatagramSocket();
            // Get the server address
            InetAddress serverAddress = InetAddress.getByName("localhost");
            // Create a buffer to hold the data to be sent
            byte[] buffer = "Hello, world!".getBytes();
            // Create a UDP packet with the data and server address/port
            DatagramPacket packet = new DatagramPacket(
                    buffer,
                    buffer.length,
                    serverAddress,
                    8888);
            // Send the UDP packet
            socket.send(packet);
            System.out.println("Sent data: " + new String(packet.getData(), 0,
                    packet.getLength()));
            // Receive the response from the server
            buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            System.out.println(
                    "Received data: " + new String(packet.getData(), 0,
                            packet.getLength()));
            // Close the socket
            socket.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}