package tech.bts.counters;

public class Counter {

    private int id;
    private int value;

    public Counter(int id) {
        this.id = id;
        this.value = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void incrementValue(int amount) {
        this.value += amount;
    }
}
