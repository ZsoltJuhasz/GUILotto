import java.util.Vector;
import java.util.Random;
import javax.swing.JCheckBox;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class LottoController {

    private ConnectDatabase connDb;
    private Vector<Integer> numberList; // összes szám
    private Vector<Integer> drawedList; // húzott számok
    private Vector<Integer> chosenList; // kiválasztott számok listája
    private LottoForm lottoFrm;
    private int counter = 0; // számláló ami a kijelölt számokat számlálja

    public LottoController(ConnectDatabase connDb) {
        chosenList = new Vector<>();
        drawedList = new Vector<>();
        numberList = new Vector<>();
        this.connDb = connDb;
        lottoFrm = new LottoForm();
        lottoFrm.exitBtn.addActionListener(event -> exit()); // lambdás kifejezéssel eseménykezelő a kilépéshez
        lottoFrm.drawBtn.addActionListener(event -> drawing()); // lambdás kifejezéssel eseménykezelő a húzáshoz
        fillNumberList();
        numberCheckBoxes();
        lottoFrm.setVisible(true);
    }

    private void fillNumberList() {

        for (int i = 1; i < 91; i++) {
            numberList.add(i);
        }
    }

    private void numberCheckBoxes() { // checkboxok feltétele az ablakra

        for (Integer i = 1; i < 91; i++) {
            JCheckBox box = new JCheckBox(); // létrehozás
            box.setText(i.toString()); // felirat beállítása és String-é alakítása amit csak Integerel lehet int-el nem
            lottoFrm.centerPnl.add(box);

            box.addItemListener(event -> { // itemListener, changeListener, actionListener is van
                JCheckBox check = (JCheckBox) event.getSource(); // ezt éppen kijelölték
                chosenList.add(Integer.parseInt(check.getText())); // listába beletöltjük a kiválasztott checkboxokat és
                                                                   // átalakítjuk Integerré

                counter++;
                if (counter == 5) {
                    lottoFrm.drawBtn.setEnabled(true); // csak akkor aktív a gombom ha megvan az 5 szám
                } else {
                    lottoFrm.drawBtn.setEnabled(false);
                }
            });
        }
    }

    private void drawing() { // számok húzása/sorsolása
        int numbers = 90; // húzható számok 1-90
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int number = rand.nextInt(numbers) + 1; // azért kell a +1, mert 0-ról indul
            numberList.remove(number - 1); // azért kell a -1 mivel az index előtti szám kell nekünk aminek 0-tól indul
                                           // a számozás nem 1-től
            numbers--; // csökkenteni kell a kihúzható számokat 1-el mivel már 1-et kihúztunk
            drawedList.add(number); // a véletlenszerűen kihúszott számot hozzáadjuk a húzott számok nevű Vectorhoz
        }
        showResult(); // eredmény mutatásának meghívása, hogy írja ki a találatokat a labelben
        numbersToDatabase();
    }

    private void showResult() {

        Integer result = 0;
        for (int i = 0; i < chosenList.size(); i++) {
            for (int j = 0; j < drawedList.size(); j++) {
                // összehasonlítjuk a kihúzott és a választott számokat és ha egyezik akkor
                // növeljük a találatokat
                if (chosenList.get(i) == drawedList.get(j)) {
                    result++;
                }
            }
        }
        String resultValue = lottoFrm.resultLbl.getText();
        lottoFrm.resultLbl.setText(resultValue + result.toString());

        for (int i = 0; i < drawedList.size(); i++) {
            String drawValue = lottoFrm.drawLbl.getText();
            String number = String.valueOf(drawedList.get(i));
            lottoFrm.drawLbl.setText(drawValue + number + " ");
        }
    }

    private void numbersToDatabase() {

        Connection conn = connDb.getConnection();
        Statement stmt = null;
        String sqlData = "";
        for (int i = 0; i < drawedList.size(); i++) {

            if (i < drawedList.size() - 1) {
                sqlData += String.valueOf(drawedList.get(i)) + ":"; // String-é kell alakítani a
                                                                    // drawedList(i)-dik tagját

            } else {
                sqlData += String.valueOf(drawedList.get(i));
            }
        }
        // Az ellenőrzéshez kell, hogy kiírassuk a kihúzott számokat :-al a képernyőre
        // System.out.println(sqlData);

        String sql = "INSERT INTO drawed(draw) VALUES('" + sqlData + "')";
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        connDb.closeConnect(); // adatbáizs kapcsolat lezárása
    }

    private void exit() {
        System.exit(0);
    }

}
