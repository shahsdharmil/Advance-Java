import java.net.URL;

public class Prac2 {
    // Main driver method
    public static void main(String[] args) throws Exception {
        // Making object of URL type
        // URL url = new URL(args[0]);
        // Url taken for consideration as input URL
        URL url = new URL("https://edu.google.com/");
        // Print the string representation of the URL.
        System.out.println("URL is:" + url.toString());
        // Retrieve the protocol of URL
        System.out.println("protocol is: " + url.getProtocol());
        // Retrieve the filename of URL
        System.out.println("file name is: " + url.getFile());
        // Retrieve the hostname of URL
        System.out.println("host is: " + url.getHost());
        // Retrieve the path of URL
        System.out.println("path is: " + url.getPath());
        // Retrieve the port of URL
        System.out.println("port is: " + url.getPort());
        System.out.println("default port is: " + url.getDefaultPort());
    }
}