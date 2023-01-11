import javax.swing.*;
import java.awt.*;

public class Field extends JButton {

    // Instanzvariablen zu Speicherung der Button-Position
    private int xValue;
    private int yValue;

    // Konstruktor
    Field() {
        this.setOpaque(true);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setForeground(Color.WHITE);
    }

    // Setter-Methoden zum Setzen der Spieler
    public void setKnight() {
        this.setText("R");
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }

    public void setDragon() {
        this.setText("D");
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }

    // Setter-Methoden zum Setzen der x- und y-Werte
    public void setX(int x) {
        this.xValue = x;
    }

    public void setY(int y) {
        this.yValue = y;
    }

    // Setter-Methoden zum Holen der x- und y-Werte
    public int getXvalue() {
        return this.xValue;
    }

    public int getYvalue() {
        return this.yValue;
    }
}
