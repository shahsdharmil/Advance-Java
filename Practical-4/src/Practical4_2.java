import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Practical4_2 {
    private static String name, enrollno;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, IOException {
        int choice;

        Connection com = DriverManager.getConnection("jdbc:mysql://localhost:3306/dharmil", "root", "root");
        Statement st = com.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = null;

        do {
            System.out.println("1. Select");
            System.out.println("2. Insert");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Run Raw SQL");
            System.out.print("0. Exit \n: ");
            choice = sc.nextInt();

            System.out.print("\033[H\033[2J"); // clear the screen
            System.out.flush();

            if (choice == 1)
                select(rs, st);

            else if (choice == 2)
                insert(rs, st);

            else if (choice == 3)
                update(rs, st);

            else if (choice == 4)
                delete(rs, st);

            else if (choice == 5)
                raw(rs, st);

            else if (choice == 0)
                break;

            else
                System.out.println("Invalid Choice!! Try Again");

            System.out.print("\nPress Enter to continue...");
            System.in.read();
            System.out.print("\033[H\033[2J");
            System.out.flush();

        } while (true);

        sc.close();
    }

    public static void select(ResultSet rs, Statement st) throws SQLException {
        rs = st.executeQuery("select * from dharmil");
        System.out.println("\nNo. \tEnroll No \tName\n");
        int i = 1;
        while (rs.next())
            System.out.println(i++ + " \t" + rs.getString(1) + " \t" + rs.getString(2));

        System.out.println("\n");
    }

    public static void insert(ResultSet rs, Statement st) throws SQLException {
        System.out.print("Enrollment No : ");
        enrollno = sc.next();
        sc.nextLine();
        System.out.print("Name : ");
        name = sc.nextLine();
        st.executeUpdate(String.format("insert into dharmil values (\'" + enrollno + "\', \'" + name + "\')"));

        select(rs, st);
    }

    public static void update(ResultSet rs, Statement st) throws SQLException {
        select(rs, st);

        System.out.print("Update Enrollment No (a) or Name (b)? : ");
        char choice = sc.next().charAt(0);
        sc.nextLine();
        if (choice == 'a') {
            System.out.print("Enter old Enrollment no : ");
            String oldEnrollno = sc.next();
            System.out.print("Enter new Enrollment no : ");
            enrollno = sc.next();

            st.executeUpdate(
                    "update dharmil set enrollment=\"" + enrollno + "\" where enrollment=\"" + oldEnrollno + "\"");
        }

        else if (choice == 'b') {
            System.out.print("Enter old Name : ");
            String oldName = sc.nextLine();
            System.out.print("Enter new Name : ");
            name = sc.nextLine();

            st.executeUpdate(
                    "update dharmil set name=\"" + name + "\" where name=\"" + oldName + "\";");

        }

        else
            System.out.println("Invalid Choice!!");

        select(rs, st);
    }

    public static void delete(ResultSet rs, Statement st) throws SQLException {
        select(rs, st);

        rs = st.executeQuery("select * from dharmil");
        System.out.print("Delete row no : ");
        int no = sc.nextInt();

        rs.relative(no);

        st.executeUpdate("delete from dharmil where enrollment=\"" + rs.getString(1) + "\"");

        select(rs, st);
    }

    public static void raw(ResultSet rs, Statement st) throws SQLException {
        select(rs, st);
        sc.nextLine();

        System.out.println("Enter SQL Statement : ");
        String sql = sc.nextLine();

        if (st.execute(sql)) {
            // Returns true if the first object that the query returns is a ResultSet object
            System.out.println();
            rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        }
        else
            select(rs, st);
    }
}