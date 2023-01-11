import javax.swing.*;

public class RitterGegenDracheTest {
    public static void main(String[] args) {
        // erzeugt eine neue GUI des Spiels
        GUI gui = new GUI();
        gui.setTitle("Ritter gegen Drache");
        gui.setSize(1000,700);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
    }
}