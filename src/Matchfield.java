import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Matchfield extends JPanel {

    // Instanzvariablen
    private final ArrayList<Field> fields;

    // Konstruktor
    Matchfield() {
        // initialisiert die ArrayList zur Speicherung der Felder
        this.fields = new ArrayList<>();
        
        // konfiguriert das Layout des Spielfeldes
        this.setLayout(new GridLayout(7,7));
        this.setPreferredSize(new Dimension(700,700));

        // fügt 49 Felder zum Spielfeld hinzu
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // setzt den Ritter auf das erste Feld
                if (i == 0 && j == 0) {
                    Field fieldInit = new Field();
                    fieldInit.setBackground(Color.BLACK);
                    fieldInit.setKnight();
                    fieldInit.setX(i);
                    fieldInit.setY(j);
                    this.add(fieldInit);
                    fields.add(fieldInit);
                }
                // setzt den Drachen auf das letzte Feld
                else if (i == 6 && j == 6) {
                    Field fieldInit = new Field();
                    fieldInit.setBackground(Color.BLACK);
                    fieldInit.setDragon();
                    fieldInit.setX(i);
                    fieldInit.setY(j);
                    this.add(fieldInit);
                    fields.add(fieldInit);
                }
                // erstellt Felder mit zufälligem Landschaftstyp
                else {
                    Field field = new Field();
                    int randomNr = (int) (Math.random() * 3);
                    if (randomNr == 0) {
                        field.setBackground(Color.RED);
                    }
                    else if (randomNr == 1) {
                        field.setBackground(Color.BLUE);
                    }
                    else if (randomNr == 2) {
                        field.setBackground(Color.GREEN);
                    }
                    field.setX(i);
                    field.setY(j);
                    this.add(field);
                    fields.add(field);
                }
            }
        }
    }

    // gibt alle Felder zurück
    public ArrayList<Field> getFields() {
        return this.fields;
    }



}
