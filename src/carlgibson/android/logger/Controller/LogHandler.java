package carlgibson.android.logger.Controller;

import android.content.Context;
import carlgibson.android.logger.DAL.LogDataSource;
import carlgibson.android.logger.DAL.SQLLogData;
import carlgibson.android.logger.model.Item;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class LogHandler {

    private List<String> mUnits;
    private LogDataSource logDataSource;
    private DataFilter mFilter;
    private static LogHandler mInstance;

    public static LogHandler getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new LogHandler(context, true);
        }
        return mInstance;
    }

    private LogHandler(Context context,boolean create) {
        //logDataSource = MockLogData.getInstance();
        logDataSource = new SQLLogData(context);
        mUnits = new ArrayList<String>();
        mFilter = new LogDataFilter();
    }

    public List<Topic> getTopics() {
        return logDataSource.getTopics();
    }

    public List<Log> getLogs() {
        return logDataSource.getLogs();
    }

    public List<String> getUnits() {
        return logDataSource.getUnits();
    }

    public void addLogEntry(Log log) {
        logDataSource.addLog(log);
    }

    public List<Log> getDateDescSortedLogs()
    {
        List<Log> logs = getLogs();
        return mFilter.filterLogsByDateDesc(logs);
    }

    public List<Item> getItems(Topic topic)
    {
        return logDataSource.getItems(topic.getId());
    }
}
