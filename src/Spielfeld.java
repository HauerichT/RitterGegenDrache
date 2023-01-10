import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Spielfeld extends JPanel {

    private ArrayList<Object> kacheln;

    Spielfeld() {

        this.kacheln = new ArrayList<>();

        this.setLayout(new GridLayout(7,7));
        this.setPreferredSize(new Dimension(700,700));

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {

                if (i == 0 && j == 0) {
                    Kachel kachelInit = new Kachel();
                    kachelInit.setBackground(Color.BLACK);
                    kachelInit.setRitter();
                    kachelInit.setX(i);
                    kachelInit.setY(j);
                    this.add(kachelInit);
                    kacheln.add(kachelInit);
                }
                else if (i == 6 && j == 6) {
                    Kachel kachelInit = new Kachel();
                    kachelInit.setBackground(Color.BLACK);
                    kachelInit.setDrache();
                    kachelInit.setX(i);
                    kachelInit.setY(j);
                    this.add(kachelInit);
                    kacheln.add(kachelInit);
                }
                else {
                    Kachel kachel = new Kachel();
                    int randomNr = (int) (Math.random() * 3);
                    if (randomNr == 0) {
                        kachel.setBackground(Color.RED);
                    }
                    else if (randomNr == 1) {
                        kachel.setBackground(Color.BLUE);
                    }
                    else if (randomNr == 2) {
                        kachel.setBackground(Color.GREEN);
                    }
                    kachel.setX(i);
                    kachel.setY(j);
                    this.add(kachel);
                    kacheln.add(kachel);
                }
            }
        }
    }


    public ArrayList<Object> getKacheln() {
        return this.kacheln;
    }



}
