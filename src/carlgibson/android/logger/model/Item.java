package carlgibson.android.logger.model;

public class Item {
    private String name;
    private int defaultQty;
    private Unit defaultUnit;
    private Topic topic;

    public Item(String name, int defaultQty,
                Unit defaultUnit, Topic topic) {
        this.name = name;
        this.defaultQty = defaultQty;
        this.defaultUnit = defaultUnit;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public int getDefaultQty() {
        return defaultQty;
    }

    public Unit getDefaultUnit() {
        return defaultUnit;
    }

    public Topic getTopic() {
        return topic;
    }
}
