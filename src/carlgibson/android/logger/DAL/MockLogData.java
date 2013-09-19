package carlgibson.android.logger.DAL;

import carlgibson.android.logger.model.Item;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockLogData implements LogDataSource {

    private List<Log> mLogs;
    private List<Topic> mTopics;
    private List<Unit> mUnits;


    private static MockLogData mInstance;

    public static MockLogData getInstance()
    {
        if(null==mInstance)
        {
            mInstance = new MockLogData();
        }
        return mInstance;
    }

    private MockLogData()
    {
        mLogs = new ArrayList<Log>();
        mTopics = new ArrayList<Topic>();
        mUnits = new ArrayList<Unit>();

        addMockTopics();
        addMockUnits();
//        addMockLogs();
    }

    private void addMockUnits() {
        mUnits.add(new Unit(1,"Minutes"));
        mUnits.add(new Unit(2,"Hours"));
        mUnits.add(new Unit(3,"Calories"));
        mUnits.add(new Unit(4,"Miles"));
        mUnits.add(new Unit(5,"Metres"));
        mUnits.add(new Unit(6,"Kilometres"));
        mUnits.add(new Unit(7,"Each"));
        mUnits.add(new Unit(8,"Lengths"));
    }

    private void addMockTopics() {
        mTopics.add(new Topic(0, "Exercise"));
        mTopics.add(new Topic(1, "Food"));
        mTopics.add(new Topic(2, "Symptoms"));
        mTopics.add(new Topic(3, "Relaxation"));
    }

    @Override
    public List<Log> getLogs() {
        return mLogs;
    }

    @Override
    public List<Log> getLogs(int topicId) {
        return null;
    }

    @Override
    public Log getLog(int id) {
        return null;
    }

    @Override
    public boolean addLog(Log log) {
        return false;
    }

    @Override
    public boolean deleteLog(int id) {
        return false;
    }

    @Override
    public boolean updateLog(Log log) {
        return false;
    }

    @Override
	public List<Topic> getTopics() {
		return mTopics;
	}

    @Override
    public Topic getTopicById(int id) {
        return null;
    }

    @Override
    public void addTopic(Topic topic) {
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public List<Item> getItems(int topicId) {
        return null;
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public void addItem(Item item) {
    }

    @Override
    public List<Unit> getUnits() {
        return mUnits;
    }

    @Override
    public Unit getUnitById(int id) {
        return null;
    }

    @Override
    public void addUnit(Unit unit) {
    }

    private void addMockLogs() {
        for (Topic topic : getTopics())
        {
                mLogs.add(new Log(0,topic.getName(), "Topics item",
                                     (int) (Math.random() * 100), "Minutes", "Dummy entry", new Date()));
        }
    }

}
