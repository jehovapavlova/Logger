package carlgibson.android.logger.model;

public class Topic {
	private String name;
    private int id;

    public Topic(int id, String name)
	{
		this.name = name;
        this.id = id;
    }
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

    public int getId() {
        return this.id;  //To change body of created methods use File | Settings | File Templates.
    }
}
