package carlgibson.android.logger.model;

public class Item {
    private String name;
    private int defaultQty;
    private int defaultUnitId;
    private Topic topic;

    public Item(String name, int defaultQty,
                int defaultUnitId, Topic topic) {
        this.name = name;
        this.defaultQty = defaultQty;
        this.defaultUnitId = defaultUnitId;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public int getDefaultQty() {
        return defaultQty;
    }

    public int getDefaultUnitId() {
        return defaultUnitId;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
