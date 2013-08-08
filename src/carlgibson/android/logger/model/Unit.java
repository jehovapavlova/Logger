package carlgibson.android.logger.model;

public class Unit {
    private final String name;
    private final int defaultValue;

    public Unit(String name, int defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() { return this.name; }
    public int getDefault() { return this.defaultValue; }

    public String ToString() {
        return name;
    }
}
