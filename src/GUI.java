import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GUI extends JFrame implements ActionListener {

    // Instanzvariablen
    JPanel panelRight, panelDragon, panelKnight, panelInformation, panelAction;
    JLabel txtKnightPoints, txtDragonPoints, txtInformation, txtResult;
    JButton btnAction;
    Matchfield matchfield;
    Dragon dragon;
    Knight knight;
    boolean turnKnight, turnActive;
    int diceResult;
    ArrayList<Field> fields;

    // Konstruktor
    public GUI() {
        // speichert den Spieler, welcher an der Reihe ist
        turnKnight = true;

        // speichert, ob ein Zug gemacht werden darf bzw. muss
        turnActive = false;

        // erzeugt das Spielfeld
        matchfield = new Matchfield();

        // weist jedem Feld im Spielfeld den ActionListener der Klasse GUI zu
        fields = matchfield.getFields();
        for (Field field : fields) {
            field.addActionListener(this);
        }

        // erzeugt einen Ritter und setzt die aktuelle Position
        knight = new Knight();
        knight.setCurrentField(fields.get(0));

        // erzeugt einen Drachen und setzt die aktuelle Position
        dragon = new Dragon();
        dragon.setCurrentField(fields.get(48));

        // erzeugen der JPanels für die einzelnen Elemente der rechten Seite
        panelRight = new JPanel();
        panelDragon = new JPanel();
        panelKnight = new JPanel();
        panelInformation = new JPanel();
        panelAction = new JPanel();

        // erzeugen der JTextFields für die Spielstärke des Ritters und des Drachens
        txtKnightPoints = new JLabel("Spielstärke: " + knight.getStrength());
        txtDragonPoints = new JLabel("Spielstärke: " + dragon.getStrength());

        // erzeugen des Würfeln-Buttons und Zuweisung des ActionListeners
        btnAction = new JButton("Würfeln");
        btnAction.addActionListener(this);

        // erzeugen des JTextFields für die Anzeige der Informationen
        txtInformation = new JLabel("Der Ritter ist dran!");

        // erzeugen des JTextFields für die Anzeige des Würfelergebnisses
        txtResult = new JLabel("Ergebnis: -");

        // JPanel für die Spielstärke des Ritters konfigurieren
        panelKnight.setLayout(new BorderLayout());
        panelKnight.setPreferredSize(new Dimension(300,100));
        panelKnight.setBorder(new TitledBorder("Ritter"));
        panelKnight.add(txtKnightPoints);

        // JPanel für die Spielstärke des Drachens konfigurieren
        panelDragon.setLayout(new BorderLayout());
        panelDragon.setPreferredSize(new Dimension(300,100));
        panelDragon.setBorder(new TitledBorder("Drache"));
        panelDragon.add(txtDragonPoints);

        // JPanel für die Informationen konfigurieren
        panelInformation.setLayout(new BorderLayout());
        panelInformation.setPreferredSize(new Dimension(300,350));
        panelInformation.setBorder(new TitledBorder("Informationen"));
        panelInformation.add(txtInformation);

        // JPanel für die Aktionen konfigurieren
        panelAction.setLayout(new BorderLayout());
        panelAction.setPreferredSize(new Dimension(300,100));
        panelAction.setBorder(new TitledBorder("Aktion"));
        panelAction.add(btnAction, BorderLayout.PAGE_START);
        panelAction.add(txtResult, BorderLayout.PAGE_END);

        // weist die einzelnen Elemente der rechten Seite zu
        panelRight.setPreferredSize(new Dimension(300, 750));
        panelRight.add(panelKnight);
        panelRight.add(panelDragon);
        panelRight.add(panelInformation);
        panelRight.add(panelAction);

        // weist dem JFrame das Spielfeld und die rechte Seite zu
        this.setLayout(new BorderLayout());
        this.add(matchfield, BorderLayout.WEST);
        this.add(panelRight, BorderLayout.EAST);
    }

    // Methode prüft, ob das ausgewählte neue Feld ein valider Schritt des Spielers ist
    public void makeMove(Field field) {
        // prüft, ob Schritt valide ist
        if (checkStep(field)) {
            setPlayer(field);
            changeStrength(field);
            // prüft, ob Ritter und Drache auf einem Feld stehen
            if (knight.getCurrentField() == dragon.getCurrentField()) {
                endGame(field);
            }
            else {
                // Wechsel des Spielers und Vorbereitung eines neuen Zuges
                this.btnAction.setEnabled(true);
                this.txtResult.setText("Ergebnis: -");
                this.turnActive = false;
                this.turnKnight = !turnKnight;
            }
        }
        else {
            // wenn Feld nicht valide ist, wird ein Hinweis angezeigt
            txtInformation.setText("Feld nicht auswählbar!");
        }
    }

    // Methode generiert eine zufällige Zahl zwischen 1 und 3, als Würfelzahl
    public void rollTheDice() {
        // deaktiviert den Würfel-Button, wenn gewürfelt wurde
        this.btnAction.setEnabled(false);
        // generiert gewürfelte Zahl
        this.diceResult = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        // zeigt die gewürfelte Zahl an
        this.txtResult.setText("Ergebnis: " + diceResult);
        // aktiviert die Möglichkeit, ein neues Feld zu wählen
        this.turnActive = true;
    }

    // Methode prüft, ob das neue Feld im validen Radius des aktuellen Feldes liegt
    public boolean checkStep(Field field) {
        // Speichert die aktuellen Felder der Spieler zwischen
        Field currentFieldKnight = knight.getCurrentField();
        Field currentFieldDragon = dragon.getCurrentField();

        if (turnKnight) {
            // prüft, ob das neue Feld nicht das aktuelle Feld des Spielers oder die initialen Felder sind
            if (field != currentFieldKnight && field != fields.get(0) && field != fields.get(48)) {
                // prüft, ob der Schritt auf der x-Achse valide ist
                if ((field.getXvalue() > currentFieldKnight.getXvalue() + diceResult) || (field.getXvalue() < currentFieldKnight.getXvalue() - diceResult)) {
                    return false;
                // prüft, ob der Schritt auf der y-Achse valide ist
                } else
                    return (field.getYvalue() <= currentFieldKnight.getYvalue() + diceResult) && (field.getYvalue() >= currentFieldKnight.getYvalue() - diceResult);
            }
        }
        else {
            // prüft, ob das neue Feld nicht das aktuelle Feld des Spielers oder die initialen Felder sind
            if (field != currentFieldDragon && field != fields.get(0) && field != fields.get(48)) {
                // prüft, ob der Schritt auf der x-Achse valide ist
                if ((field.getXvalue() > currentFieldDragon.getXvalue() + diceResult) || (field.getXvalue() < currentFieldDragon.getXvalue() - diceResult)) {
                    return false;
                // prüft, ob der Schritt auf der y-Achse valide ist
                } else
                    return (field.getYvalue() <= currentFieldDragon.getYvalue() + diceResult) && (field.getYvalue() >= currentFieldDragon.getYvalue() - diceResult);
            }
        }
        // gibt false zurück, wenn der Schritt nicht valide ist
        return false;
    }

    // Methode ändert die Spielstärke, je nachdem auf welchen Landschaftstyp ein Spieler gesetzt wurde
    public void changeStrength(Field field) {
        if (turnKnight) {
            // prüft, welcher Landschaftstyp besetzt wurde
            if (field.getBackground() == Color.RED) {
                this.knight.reduceStrength();
            }
            else if (field.getBackground() == Color.GREEN) {
                this.knight.increaseStrength();
            }
            // zeigt die aktualisierte Spielstärke an
            this.txtKnightPoints.setText("Spielstärke: " + knight.getStrength());
        }
        else {
            // prüft, welcher Landschaftstyp besetzt wurde
            if (field.getBackground() == Color.RED) {
                this.dragon.increaseStrength();
            }
            else if (field.getBackground() == Color.GREEN) {
                this.dragon.reduceStrength();
            }
            // zeigt die aktualisierte Spielstärke an
            this.txtDragonPoints.setText("Spielstärke: " + dragon.getStrength());
        }
    }

    // Methode setzt einen Spieler auf ein anderes Feld, wenn das neue Feld im validen Radius liegt
    public void setPlayer(Field field) {
        if (turnKnight) {
            // entfernt das alte Symbol
            knight.getCurrentField().setText("");
            // setzt den Spieler auf das neue Feld
            field.setKnight();
            knight.setCurrentField(field);
            txtInformation.setText("Der Drache ist dran!");
        }
        else {
            // entfernt das alte Symbol
            dragon.getCurrentField().setText("");
            // setzt den Spieler auf das neue Feld
            field.setDragon();
            dragon.setCurrentField(field);
            txtInformation.setText("Der Ritter ist dran!");
        }
    }

    // Methode verwaltet das Spielende
    private void endGame(Field field) {
        // setzt das Feld, auf welchem sich beide Symbole treffen, auf ein X
        field.setText("X");
        // setzt das Ergebnis auf den Startwert
        txtResult.setText("Ergebnis: -");
        // prüft, welcher Spieler die höhere Spielstärke hat und gibt den Sieger bzw. ein Unentschieden aus
        if (knight.getStrength() > dragon.getStrength()) {
            txtInformation.setText("Der Ritter gewinnt!");
        }
        else if (knight.getStrength() < dragon.getStrength()) {
            txtInformation.setText("Der Drache gewinnt!");
        }
        else {
            txtInformation.setText("Das Spiel endet unentschieden!");
        }
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // prüft, ob gewürfelt werden soll
        if (e.getSource() == btnAction) {
            rollTheDice();
        }

        // startet das Setzen eines Spielers, wenn gewürfelt wurde
        if (turnActive) {
            for (Field field : fields) {
                if (e.getSource() == field) {
                    makeMove(field);
                }
            }
        }

    }


}
