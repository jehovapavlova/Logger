package carlgibson.android.logger.model;

public class Unit {
    private final String name;
    private final int id;

    public Unit( int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() { return this.name; }
    public int getId() { return this.id; }

    @Override
    public String toString() {
        return getName();
    }
}
