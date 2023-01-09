import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Spielfeld extends JPanel {

    private ArrayList<Object> kacheln;
    private GridLayout layout;


    Spielfeld() {

        this.kacheln = new ArrayList<>();

        this.layout = new GridLayout(7,7);
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(700,700));

        for (int i = 0; i < 49; i++) {
            if (i == 0) {
                Kachel kachelInit = new Kachel("init");
                kachelInit.setRitter();
                this.kacheln.add(kachelInit);
            }
            else if (i == 48) {
                Kachel kachelInit = new Kachel("init");
                kachelInit.setDrache();
                this.kacheln.add(kachelInit);
            }
            else {
                Kachel kachel = new Kachel();
                this.kacheln.add(kachel);
            }
            this.add((Component) kacheln.get(i));
        }

    }


    public ArrayList<Object> getKacheln() {
        return this.kacheln;
    }



}
