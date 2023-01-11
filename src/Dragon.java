import java.util.concurrent.ThreadLocalRandom;

public class Dragon {

    // Instanzvariablen
    private final int speed;
    private final int intelligence;
    private int strength;
    Field currentField;

    // Konstruktor
    Dragon() {
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
    public Field getCurrentField() {
        return this.currentField;
    }

    // erhöht die Spielstärke
    public void increaseStrength() {
        this.strength = (int) (this.strength * 1.10);
    }

    // verringert die Spielstärke
    public void reduceStrength() {
        this.strength = (int) (this.strength * 0.9);
    }

    // setzt das aktuelle Feld des Drachens
    public void setCurrentField(Field field) {
        this.currentField = field;
    }

}
