import java.util.concurrent.ThreadLocalRandom;

public class Ritter {

    private int geschwindigkeit;
    private int intelligenz;
    private int spielstaerke;
    Kachel aktKachel;

    Ritter() {
        this.geschwindigkeit = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        this.intelligenz = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        this.spielstaerke = (int) ((2 * this.geschwindigkeit + this.intelligenz) * ThreadLocalRandom.current().nextDouble(0.8, 1.2));;
    }

    public int getSpielstaerke() {
        return this.spielstaerke;
    }

    public void spielstaerkeErhoehen() {
        spielstaerke = (int) (spielstaerke * 1.10);
    }

    public void spielstaerkeVerringern() {
        spielstaerke = (int) (spielstaerke * 0.9);
    }

    public void setAktKachel(Kachel kachel) {
        this.aktKachel = kachel;
    }

    public Kachel getAktKachel() {
        return this.aktKachel;
    }
}
