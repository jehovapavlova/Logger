package carlgibson.android.logger.DAL;

import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockLogData implements LogDataSource {

    private List<Log> mLogs;
    private List<Topic> mTopics;
    private List<String> mUnits;


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
        mUnits = new ArrayList<String>();

        addMockTopics();
        addMockUnits();
//        addMockLogs();
    }

    private void addMockUnits() {
        mUnits.add("Minutes");
        mUnits.add("Hours");
        mUnits.add("Calories");
        mUnits.add("Miles");
        mUnits.add("Metres");
        mUnits.add("Kilometres");
        mUnits.add("Each");
        mUnits.add("Lengths");
    }

    private void addMockTopics() {
        mTopics.add(new Topic("Exercise").addItem("Running").addItem("Swimming")
                .addItem("Gym"));
        mTopics.add(new Topic("Food").addItem("Crisps").addItem("Apple")
                .addItem("Meatballs"));
        mTopics.add(new Topic("Symptoms").addItem("Headache")
                .addItem("Stomach ache").addItem("Rash"));
        mTopics.add(new Topic("Relaxation").addItem("Sleep").addItem("Reading")
                .addItem("Star gazing"));
    }

    @Override
	public List<Topic> getTopics() {
		return mTopics;
	}

    @Override
    public Topic getTopicById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTopic(Topic topic) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
	public List<Log> getLogs() {
          return mLogs;
	}

    @Override
    public List<Log> getLogs(Topic topic) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Log getLog(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addLog(Log log) {
        mLogs.add(log);
    }

    @Override
    public List<String> getUnits() {
            return mUnits;
    }

    @Override
    public Unit getUnitById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private void addMockLogs() {
        for (Topic topic : getTopics())
        {
            for (String item : topic.getItems())
            {
                mLogs.add(new Log(topic.getName(), item,
                                     (int) (Math.random() * 100), "Minutes", "Dummy entry", new Date()));
            }
        }
    }

}
