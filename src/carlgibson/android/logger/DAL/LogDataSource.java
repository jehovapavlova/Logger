package carlgibson.android.logger.DAL;

import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 04/08/13
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
public interface LogDataSource {

    List<Log> getLogs();
    List<Log> getLogs(Topic topic);
    Log getLog(int id);
    void addLog(Log log);

    List<Topic> getTopics();
    Topic getTopicById(int id);
    void addTopic(Topic topic);

    List<String> getUnits();
    Unit getUnitById(int id);

}
