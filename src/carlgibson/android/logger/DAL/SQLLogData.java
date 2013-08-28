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
    private SQLiteDatabase mDb;

    public SQLLogData(Context context) {
        dbHelper = new DBHelper(context);
    }

    private SQLiteDatabase openReadable() {
        mDb = dbHelper.getReadableDatabase();
        return mDb;
    }

    @Override
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<Topic>();
        Cursor cursor = openReadable().query("Topics", null, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Topic topic = new Topic(cursor.getInt(0), cursor.getString(1));
            topics.add(topic);
            cursor.moveToNext();
        }
        return topics;
    }

    @Override
    public Topic getTopicById(int id) {
        Topic topic = null;

        for (Topic t: getTopics())
        {
            if (t.getId()==id) topic = t;
        }

        return topic;
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = openReadable().query("Items", null, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            String itemName = cursor.getString(1);
            Integer qty = cursor.getInt(4);
            Topic topic = getTopicById(cursor.getInt(2));
            Unit unit = getUnitById(cursor.getInt(3));

            Item item = new Item(itemName, qty, unit, topic);
            items.add(item);
            cursor.moveToNext();
        }
        return items;
    }

    @Override
    public List<Item> getItems(int topicId) {
        List<Item> items = new ArrayList<Item>();
         Cursor cursor = openReadable().query("Items", null, "topicId = "+topicId, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            String itemName = cursor.getString(1);
            Integer qty = cursor.getInt(4);
            Topic topic = getTopicById(cursor.getInt(2));
            Unit unit = getUnitById(cursor.getInt(3));

            Item item = new Item(itemName, qty, unit, topic);
            items.add(item);
            cursor.moveToNext();
        }
        return items;
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public void addItem(Item item) {
    }

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<Log>();
        Cursor cursor = openReadable().query("LOGS", null, null, null, null, null, null);

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
            cursor.moveToNext();
        }

        return logs;
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
    public void addLog(Log log) {
    }

    @Override
    public List<String> getUnits() {
        List<String> units = new ArrayList<String>();
        Cursor cursor = openReadable().query("Units", null, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            String unitName = cursor.getString(1);
            units.add(unitName);
            cursor.moveToNext();
        }
        return units;
    }

    @Override
    public Unit getUnitById(int id) {
        return null;
    }

    @Override
    public void addUnit(Unit unit) {
    }
}
