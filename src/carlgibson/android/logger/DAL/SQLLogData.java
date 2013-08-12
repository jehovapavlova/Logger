package carlgibson.android.logger.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import carlgibson.android.logger.model.Item;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: CarlGibson
 * Date: 03/08/13
 * Time: 01:01
 */
public class SQLLogData implements LogDataSource {

    private final DBHelper dbHelper;

    public SQLLogData(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public List<Topic> getTopics() {
        return null;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Item> getItems(int topicId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Item getItemById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addItem(Item item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<Log>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LOGS",null,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Item item = getItemById(cursor.getInt(2));
            Topic topic = item.getTopic();
            Unit unit = getUnitById(cursor.getInt(3));
            int qty =  cursor.getInt(4);
            String desc = cursor.getString(5);
            String dateTime = cursor.getString(1);
            Date date = null;

            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyyHH:mm");
            try {
                date = sdf.parse(dateTime);
            } catch (ParseException e) {
                date = new Date();
            }

            Log log = new Log(
                    topic.getName(),
                    item.getName(),
                    qty,
                    unit.getName(),
                    desc,
                    date);
            logs.add(log);
        }

        return logs;
    }

    @Override
    public List<Log> getLogs(int topicId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Log getLog(int id) {
        return null;
    }

    @Override
    public void addLog(Log log) {

    }

    @Override
    public List<String> getUnits() {
        return null;
    }

    @Override
    public Unit getUnitById(int id) {
        return null;
    }

    @Override
    public void addUnit(Unit unit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
