package carlgibson.android.logger.Controller;

import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.util.List;

public interface DataFilter {
    public List<Log> filterLogsByDateDesc(List<Log> logs);
    public List<Log> filterLogsByTopic(List<Log> logs, Topic topic);
    public List<Log> filterLogsByItem(List<Log> logs, String item);
}
