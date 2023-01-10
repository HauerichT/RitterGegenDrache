import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class GUI extends JFrame implements ActionListener {

    JPanel panelRechteSeite, panelDrache, panelRitter, panelInformationen, panelAktion;
    JLabel txtRitterPunkte;
    JLabel txtDrachePunkte;
    JLabel txtInformationen;
    JLabel txtErgebnis;
    JButton btnAktion;
    Spielfeld spielfeld;
    Drache drache;
    Ritter ritter;
    boolean turnRitter;
    boolean spielzugAktiv;
    int diceResult;
    ArrayList<Object> kacheln;

    public GUI() {
        // Turn
        turnRitter = true;

        // Spielzug
        spielzugAktiv = false;

        // Spielfeld
        spielfeld = new Spielfeld();
        kacheln = spielfeld.getKacheln();
        for (int i = 0; i < kacheln.size(); i++) {
            Kachel akt = (Kachel) kacheln.get(i);
            akt.addActionListener(this);
        }

        // Ritter
        ritter = new Ritter();
        ritter.setAktKachel((Kachel) kacheln.get(0));

        // Drache
        drache = new Drache();
        drache.setAktKachel((Kachel) kacheln.get(48));

        // JPanels für die einzelnen Elemente der rechten Seite erzeugen
        panelRechteSeite = new JPanel();
        panelDrache = new JPanel();
        panelRitter = new JPanel();
        panelInformationen = new JPanel();
        panelAktion = new JPanel();

        // JTextField für die Spielstärke des Ritters und Drachen
        txtRitterPunkte = new JLabel("Spielstärke: " + ritter.getSpielstaerke());
        txtDrachePunkte = new JLabel("Spielstärke: " + drache.getSpielstaerke());

        // Würfeln-Button
        btnAktion = new JButton("Würfeln");
        btnAktion.addActionListener(this);

        // JTextField für die Anzeige der Informationen
        txtInformationen = new JLabel("Der Ritter ist dran!");

        // JTextField für die Anzeige des Würfelergebnisses
        txtErgebnis = new JLabel("Ergebnis: -");

        // JPanel für die Spielstärke des Ritters
        panelRitter.setLayout(new BorderLayout());
        panelRitter.setPreferredSize(new Dimension(300,100));
        panelRitter.setBorder(new TitledBorder("Ritter"));
        panelRitter.add(txtRitterPunkte);

        // JPanel für die Spielstärke des Drachens
        panelDrache.setLayout(new BorderLayout());
        panelDrache.setPreferredSize(new Dimension(300,100));
        panelDrache.setBorder(new TitledBorder("Drache"));
        panelDrache.add(txtDrachePunkte);

        // JPanel für die Informationen
        panelInformationen.setLayout(new BorderLayout());
        panelInformationen.setPreferredSize(new Dimension(300,350));
        panelInformationen.setBorder(new TitledBorder("Informationen"));
        panelInformationen.add(txtInformationen);

        // JPanel für die Aktionen
        panelAktion.setLayout(new BorderLayout());
        panelAktion.setPreferredSize(new Dimension(300,100));
        panelAktion.setBorder(new TitledBorder("Aktion"));
        panelAktion.add(btnAktion, BorderLayout.PAGE_START);
        panelAktion.add(txtErgebnis, BorderLayout.PAGE_END);

        panelRechteSeite.setPreferredSize(new Dimension(300, 750));
        panelRechteSeite.add(panelRitter);
        panelRechteSeite.add(panelDrache);
        panelRechteSeite.add(panelInformationen);
        panelRechteSeite.add(panelAktion);

        this.setLayout(new BorderLayout());
        this.add(spielfeld, BorderLayout.WEST);
        this.add(panelRechteSeite, BorderLayout.EAST);
    }

    public void changeStrength(Kachel kachel) {
        if (turnRitter) {
            if (kachel.getBackground() == Color.RED) {
                this.ritter.spielstaerkeVerringern();
            }
            else if (kachel.getBackground() == Color.GREEN) {
                this.ritter.spielstaerkeErhoehen();
            }
            this.txtRitterPunkte.setText("Spielstärke: " + ritter.getSpielstaerke());
        }
        else {
            if (kachel.getBackground() == Color.RED) {
                this.drache.spielstaerkeErhoehen();
            }
            else if (kachel.getBackground() == Color.GREEN) {
                this.drache.spielstaerkeVerringern();
            }
            this.txtDrachePunkte.setText("Spielstärke: " + drache.getSpielstaerke());
        }
    }

    public void setPlayer(Kachel kachel) {
        if (turnRitter) {
            for (int i = 0; i < kacheln.size(); i++) {
                Kachel temp = (Kachel) kacheln.get(i);
                if (Objects.equals(temp.getText(), "R")) {
                    temp.setText("");
                }
            }
            kachel.setRitter();
            ritter.setAktKachel(kachel);
            txtInformationen.setText("Der Drache ist dran!");
        }
        else {
            for (int i = 0; i < kacheln.size(); i++) {
                Kachel temp = (Kachel) kacheln.get(i);
                if (Objects.equals(temp.getText(), "D")) {
                    temp.setText("");
                }
            }
            kachel.setDrache();
            drache.setAktKachel(kachel);
            txtInformationen.setText("Der Ritter ist dran!");
        }
    }

    public void makeMove(Kachel kachel) {
        if (checkStep(kachel)) {
            setPlayer(kachel);
            if (ritter.getAktKachel() == drache.getAktKachel()) {
                endGame(kachel);
            }
            else {
                changeStrength(kachel);
                this.btnAktion.setEnabled(true);
                this.txtErgebnis.setText("Ergebnis: -");
                this.spielzugAktiv = false;
                this.turnRitter = !turnRitter;
            }
        }
        else {
            txtInformationen.setText("Feld nicht auswählbar!");
        }
    }

    private void endGame(Kachel kachel) {
        kachel.setText("X");
        txtErgebnis.setText("Ergebnis: -");
        if (ritter.getSpielstaerke() > drache.getSpielstaerke()) {
            txtInformationen.setText("Der Ritter gewinnt!");
        }
        else if (ritter.getSpielstaerke() < drache.getSpielstaerke()) {
            txtInformationen.setText("Der Drache gewinnt!");
        }
        else {
            txtInformationen.setText("Das Spiel endet unentschieden!");
        }
    }

    public void rollTheDice() {
        this.btnAktion.setEnabled(false);
        this.diceResult = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        this.txtErgebnis.setText("Ergebnis: " + diceResult);
        this.spielzugAktiv = true;
    }

    public boolean checkStep(Kachel kachel) {
        Kachel aktKachelRitter = ritter.getAktKachel();
        Kachel aktKachelDrache = drache.getAktKachel();
        if (turnRitter) {
            if (kachel != aktKachelRitter && kachel != kacheln.get(0) && kachel != kacheln.get(48)) {
                if ((kachel.getXvalue() > aktKachelRitter.getXvalue()+diceResult) || (kachel.getXvalue() < aktKachelRitter.getXvalue()-diceResult)) {
                    return false;
                }
                else if ((kachel.getYvalue() > aktKachelRitter.getYvalue()+diceResult) || (kachel.getYvalue() < aktKachelRitter.getYvalue()-diceResult)) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        else {
            if (kachel != aktKachelDrache && kachel != kacheln.get(0) && kachel != kacheln.get(48)) {
                if ((kachel.getXvalue() > aktKachelDrache.getXvalue()+diceResult) || (kachel.getXvalue() < aktKachelDrache.getXvalue()-diceResult)) {
                    return false;
                }
                else if ((kachel.getYvalue() > aktKachelDrache.getYvalue()+diceResult) || (kachel.getYvalue() < aktKachelDrache.getYvalue()-diceResult)) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAktion) {
            rollTheDice();
        }

        if (spielzugAktiv) {
            for (int i = 0; i < kacheln.size(); i++) {
                if (e.getSource() == kacheln.get(i)) {
                    Kachel temp = (Kachel) kacheln.get(i);
                    makeMove(temp);
                }
            }
        }

    }


}
