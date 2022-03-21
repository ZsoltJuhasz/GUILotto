import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

    private Connection conn;

    public ConnectDatabase() {
        conn = null;
        connectToDatabase();
    }

    private void connectToDatabase() {
        String url = "jdbc:mariadb://localhost:3306/lotto";

        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // A kapcsolat felépülés ellenőrzéséhez kell
        // if(conn != null){
        // System.out.println("ok");
        // } else{
        // System.out.println("nincs");
        // }
    }

    public void closeConnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

}

