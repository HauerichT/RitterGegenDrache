import java.util.concurrent.ThreadLocalRandom;

public class Drache {

    private int geschwindigkeit;
    private int intelligenz;
    private int spielstaerke;

    Drache() {
        this.geschwindigkeit = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        this.intelligenz = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        this.spielstaerke = (int) Math.ceil((2 * this.geschwindigkeit + this.intelligenz) * ThreadLocalRandom.current().nextDouble(0.2, 1.2 + 1));
    }

    public int getSpielstaerke() {
        return this.spielstaerke;
    }

    public void spielstaerkeErhoehen() {
        this.spielstaerke = (int) (this.spielstaerke * 1.10);
    }

    public void spielstaerkeVerringern() {
        this.spielstaerke = (int) (this.spielstaerke - (this.spielstaerke * 0.10));
    }
}
