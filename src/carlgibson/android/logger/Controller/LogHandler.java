package carlgibson.android.logger.Controller;

import carlgibson.android.logger.DAL.LogDataSource;
import carlgibson.android.logger.DAL.MockLogData;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.util.ArrayList;
import java.util.List;

public class LogHandler {

    private List<String> mUnits;
    private LogDataSource logDataSource;
    private DataFilter mFilter;
    private static LogHandler mInstance;

    public static LogHandler getInstance() {
        if (null == mInstance) {
            mInstance = new LogHandler(true);
        }
        return mInstance;
    }

    private LogHandler(boolean create) {

        logDataSource = MockLogData.getInstance();
        mUnits = new ArrayList<String>();
        mFilter = new LogDataFilter();

        fillQuantities();
    }

    private void fillQuantities() {

    }

    public List<Topic> getTopics() {
        return logDataSource.getTopics();
    }

    public List<Log> getLogs() {
        return logDataSource.getLogs();
    }

    public List<String> getQuantities() {
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

}
