import java.sql.Connection;
import util.DBConnection;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null) {
                System.out.println("Connected to MySQL successfully!");
            } else {
                System.out.println("Connection failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}