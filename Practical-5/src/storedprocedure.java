import java.sql.*;
import java.util.Scanner;

public class storedprocedure {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/dharmil", "root", "root");
        Statement st = com.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "insert into dharmil values(?,?)";
        // Using Callable Statement in Java to Call Stored Procedure
        java.sql.CallableStatement cs = com.prepareCall(sql);
        cs.setString(1, "200");
        cs.setString(2, "A");
        cs.execute();
        System.out.println("Uploaded successfully");
        System.out.println("New Data is");
        ResultSet rs = st.executeQuery("Select * from dharmil");
        System.out.println("\nNo. \tEnroll No \tName\n");
        int i = 1;
        while (rs.next())
            System.out.println(i++ + " \t" + rs.getString(1) + " \t" + rs.getString(2));
        sc.close();
    }
}