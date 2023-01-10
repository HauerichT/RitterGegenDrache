import javax.swing.*;
import java.awt.*;

public class Kachel extends JButton {

    private int xValue;
    private int yValue;

    Kachel() {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

    public void setX(int x) {
        this.xValue = x;
    }

    public void setY(int y) {
        this.yValue = y;
    }

    public int getXvalue() {
        return this.xValue;
    }

    public int getYvalue() {
        return this.yValue;
    }
}
