import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Prac4server {
    public static void main(String[] args) {
        try {
            // Create a UDP socket on port 8888
            DatagramSocket socket = new DatagramSocket(8888);
            System.out.println("Echo server started on port " +
                    socket.getLocalPort());
            while (true) {
                // Create a buffer to hold incomingdata
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer,
                        buffer.length);
                // Receive a UDP packet
                socket.receive(packet);
                // Extract the data from the packet
                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received data: " + data);
                // Send the data back to the client
                DatagramPacket response = new DatagramPacket(
                        packet.getData(),
                        packet.getLength(),
                        packet.getAddress(),
                        packet.getPort());
                socket.send(response);
                System.out.println("Sent data: " + data);
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}