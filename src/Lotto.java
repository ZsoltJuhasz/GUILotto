import java.sql.Connection;

public class Lotto {
    public static void main(String[] args) throws Exception {

        ConnectDatabase connDb = new ConnectDatabase();
        // Connection conn = connDb.getConnection();
        // new LottoForm(); ez azért kell/kellett hogy megjelenítse a formot
        LottoController lottoCtrl = new LottoController(connDb);

        //Ellenőrzés, hogy van-e kapcsolatunk 
        // if (conn != null) {
        //     System.out.println("ok");
        // } else {
        //     System.out.println("nincs");
        // }

        
    }
}
