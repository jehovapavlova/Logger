package carlgibson.android.logger.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
	private String name;
	private List<String> items;

	public Topic(String name)
	{
		this.name = name;
		this.items = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}

	public List<String> getItems() {
		return items;
	}

	public Topic addItem(String item) {
		items.add(item);
		return this;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
