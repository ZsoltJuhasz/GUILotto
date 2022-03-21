import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class LottoForm extends JFrame {

    //ctrl + d(többször amennyi sort ki akarsz jelölni) több sor kijelölése és átírása
    public JPanel mainPnl = new JPanel();
    public JPanel northPnl = new JPanel();
    public JPanel eastPnl = new JPanel();
    public JPanel southPnl = new JPanel();
    public JPanel westPnl = new JPanel();
    public JPanel centerPnl = new JPanel();
    public JPanel drawPnl = new JPanel();
    public JPanel buttonPnl = new JPanel();
    public JLabel drawLbl = new JLabel("Számok: ");
    public JLabel resulstLbl = new JLabel("Találatok: ");
    public JButton exitBtn = new JButton("Kilépés");
    public JButton drawBtn = new JButton("Húzás");

    public LottoForm() {
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        // x-el bezárhatjuk az ablakot
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // szélesség, hosszúság
        this.setSize(450, 400);
        // elrendezéskezelő beállítás
        this.setLayout(new GridLayout(1, 1));
        // fontos a sorrend mivel itt nem GUI kezelővel csináljuk (ez kerül fel először)
        mainPnl.setLayout(new BorderLayout()); // elrendezéskezelő beállítva
        this.add(mainPnl);

        northPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPnl.add(resulstLbl);
        mainPnl.add(northPnl, BorderLayout.NORTH);

        eastPnl.setSize(10, 400);
        mainPnl.add(eastPnl, BorderLayout.EAST);

        southPnl.setLayout(new GridLayout(1, 2));
        drawPnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        drawPnl.add(drawLbl);
        buttonPnl.setLayout(new FlowLayout(FlowLayout.RIGHT));

        drawBtn.setEnabled(false); //inaktívvá tesszük majd csak 5 szám után lehet aktív
        // sorrend fontos
        buttonPnl.add(drawBtn);
        buttonPnl.add(exitBtn);
        southPnl.add(drawPnl);
        southPnl.add(buttonPnl);
        mainPnl.add(southPnl, BorderLayout.SOUTH);

        westPnl.setSize(10, 400);
        mainPnl.add(westPnl);

        centerPnl.setLayout(new GridLayout(10, 9));
        mainPnl.add(centerPnl, BorderLayout.CENTER);
    }

}
