import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.ResultSetMetaData;

public class PRac5_a {
    public static void main(String[] args) throws Exception {
        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/dharmil", "root", "root");
        Statement st = com.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet rs = st.executeQuery("select count(*) from dharmil");
        rs.next();
        System.out.println("Total No of Records : " + rs.getInt(1));

        rs = st.executeQuery("select * from dharmil");
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println("Total Columns : " + rsmd.getColumnCount());
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println("Column " + i + " : " + rsmd.getColumnClassName(i));
        }

    }
}