import java.util.concurrent.ThreadLocalRandom;

public class Knight {

    // Instanzvariablen
    private final int speed;
    private final int intelligence;
    private int strength;
    Field currentField;

    // Konstruktor
    Knight() {
        // setzt die Geschwindigkeit auf eine Zahl zwischen 6 und 8
        this.speed = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        // setzt die Intelligenz auf eine Zahl zwischen 6 und 8
        this.intelligence = ThreadLocalRandom.current().nextInt(6, 8 + 1);
        // berechnet die Spielstärke
        this.strength = (int) ((2 * this.speed + this.intelligence) * ThreadLocalRandom.current().nextDouble(0.8, 1.2));
    }

    // gibt die aktuelle Spielstärke zurück
    public int getStrength() {
        return this.strength;
    }

    // gibt das aktuelle Feld des Drachens zurück
    public void increaseStrength() {
        strength = (int) (strength * 1.10);
    }

    // erhöht die Spielstärke
    public void reduceStrength() {
        strength = (int) (strength * 0.9);
    }

    // verringert die Spielstärke
    public void setCurrentField(Field field) {
        this.currentField = field;
    }

    // setzt das aktuelle Feld des Drachens
    public Field getCurrentField() {
        return this.currentField;
    }
}
