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
    ArrayList<Object> kacheln;

    public GUI() {

        // Ritter
        ritter = new Ritter();

        // Drache
        drache = new Drache();

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

    public void spielzugMachen(Kachel kachel) {
        if (turnRitter) {
            for (int i = 0; i < kacheln.size(); i++) {
                Kachel temp = (Kachel) kacheln.get(i);
                if (Objects.equals(temp.getText(), "R")) {
                    temp.setText("");
                }
            }

            if (kachel.getBackground() == Color.RED) {
                this.ritter.spielstaerkeVerringern();
            }
            else if (kachel.getBackground() == Color.GREEN) {
                this.ritter.spielstaerkeErhoehen();
            }

            kachel.setRitter();
            txtInformationen.setText("Der Drache ist dran!");
            txtRitterPunkte.setText("Spielstärke: " + ritter.getSpielstaerke());
            this.turnRitter = false;

        }
        else {
            for (int i = 0; i < kacheln.size(); i++) {
                Kachel temp = (Kachel) kacheln.get(i);
                if (Objects.equals(temp.getText(), "D")) {
                    temp.setText("");
                }
            }

            if (kachel.getBackground() == Color.RED) {
                this.drache.spielstaerkeErhoehen();
            }
            else if (kachel.getBackground() == Color.GREEN) {
                this.drache.spielstaerkeVerringern();
            }

            kachel.setDrache();
            txtInformationen.setText("Der Ritter ist dran!");
            txtDrachePunkte.setText("Spielstärke: " + drache.getSpielstaerke());
            this.turnRitter = true;

        }
        this.btnAktion.setEnabled(true);
        this.spielzugAktiv = false;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAktion) {
            this.btnAktion.setEnabled(false);
            int ergebnis = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            this.txtErgebnis.setText("Ergebnis: " + ergebnis);
            this.spielzugAktiv = true;
        }

        if (spielzugAktiv) {
            for (int i = 0; i < kacheln.size(); i++) {
                if (e.getSource() == kacheln.get(i)) {
                    Kachel temp = (Kachel) kacheln.get(i);
                    spielzugMachen(temp);
                }
            }
        }

    }


}
