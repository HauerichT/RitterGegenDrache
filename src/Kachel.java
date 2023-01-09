import javax.swing.*;
import java.awt.*;

public class Kachel extends JButton {

    Kachel() {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setForeground(Color.WHITE);

        int randomNr = (int) (Math.random() * 3);
        if (randomNr == 0) {
            this.setBackground(Color.RED);
        }
        else if (randomNr == 1) {
            this.setBackground(Color.BLUE);
        }
        else if (randomNr == 2) {
            this.setBackground(Color.GREEN);
        }

    }

    Kachel(String s) {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }

    public void setRitter() {
        this.setText("R");
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }

    public void setDrache() {
        this.setText("D");
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }


}
