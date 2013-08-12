package carlgibson.android.logger.Controller;

import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.util.*;

public class LogDataFilter implements DataFilter {

    @Override
    public List<Log> filterLogsByDateDesc(List<Log> logs) {

        List<Log> sortedLogs = new ArrayList<Log>(logs);

        Collections.sort(sortedLogs, new Comparator<Log>() {
            @Override
            public int compare(Log lhs, Log rhs) {
                return rhs.getDate().compareTo(lhs.getDate());
                                                                                  }
        });

        return sortedLogs;
    }

    @Override
    public List<Log> filterLogsByTopic(List<Log> logs, Topic topic) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Log> filterLogsByItem(List<Log> logs, String item) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
