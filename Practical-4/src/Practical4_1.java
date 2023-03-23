import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Practical4_1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // Class.forName("oracle.jdbc.OracleDriver");
        // used in oracle
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dharmil", "root", "root");

        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        // db is read only , so it cannot be updated
        // scroll insensitive , so it will featch the data at the time of select
        // query,and not the latest

        ResultSet rs = st.executeQuery("Select * from dharmil");
        System.out.println("Table Content: ");
        System.out.println("\nName \tEnrollment Number");

        while (rs.next()) {
            System.out.print(rs.getString(2) + "\t" + rs.getString(1) + "\n");
        }
        System.out.println("\nTable Content in reverse order: ");

        rs.afterLast();
        System.out.println("\nName \tEnrollment Number");
        while (rs.previous()) {
            System.out.print(rs.getString(2) + "\t" + rs.getString(1) + "\n");

        }
        con.close();
    }
}
// Note: Connector for mysql must be added in Referenced Library in Java
// Projects