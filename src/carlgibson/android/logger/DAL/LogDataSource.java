package carlgibson.android.logger.DAL;

import carlgibson.android.logger.model.Item;
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
    List<Log> getLogs(int topicId);
    Log getLog(int id);
    boolean addLog(Log log);
    boolean deleteLog(int id);

    List<Topic> getTopics();
    Topic getTopicById(int id);
    void addTopic(Topic topic);

    List<Item> getItems();
    List<Item> getItems(int topicId);
    Item getItemById(int id);
    void addItem(Item item);

    List<String> getUnits();
    Unit getUnitById(int id);
    void addUnit(Unit unit);

}
