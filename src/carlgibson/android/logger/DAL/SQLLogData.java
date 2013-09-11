package carlgibson.android.logger.DAL;

import android.content.ContentValues;
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

    private void closeDb() {
        if (mDb != null) mDb.close();
    }

    @Override
    public List<Topic> getTopics() {
        List<Topic> topics = new ArrayList<Topic>();
        Cursor cursor = openReadable().query(dbHelper.TOPICS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Topic topic = new Topic(cursor.getInt(0), cursor.getString(1));
            topics.add(topic);
            cursor.moveToNext();
        }
        closeDb();
        return topics;
    }

    @Override
    public Topic getTopicById(int id) {
        Topic topic = null;

        for (Topic t : getTopics()) {
            if (t.getId() == id) topic = t;
        }
        closeDb();
        return topic;
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String itemName = cursor.getString(1);
            Integer qty = cursor.getInt(4);
            Topic topic = getTopicById(cursor.getInt(2));
            int unitId = cursor.getInt(3);

            Item item = new Item(itemName, qty, unitId, topic);
            items.add(item);
            cursor.moveToNext();
        }
        closeDb();
        return items;
    }

    @Override
    public List<Item> getItems(int topicId) {
        List<Item> items = new ArrayList<Item>();
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "topicId = " + topicId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String itemName = cursor.getString(1);
            Integer qty = cursor.getInt(4);
            Topic topic = getTopicById(cursor.getInt(2));
            int unitId = cursor.getInt(3);

            Item item = new Item(itemName, qty, unitId, topic);
            items.add(item);
            cursor.moveToNext();
        }
        closeDb();
        return items;
    }

    @Override
    public Item getItemById(int id) {
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        String itemName = cursor.getString(1);
        Integer qty = cursor.getInt(4);
        Topic topic = getTopicById(cursor.getInt(2));
        int unitId = cursor.getInt(3);

        Item item = new Item(itemName, qty, unitId, topic);
        return item;
    }

    private int getItemId(String itemName) {
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "item = ?", new String[]{itemName}, null, null, null);
        cursor.moveToFirst();
        int itemId = cursor.getInt(0);
        closeDb();
        return itemId;
    }

    @Override
    public void addItem(Item item) {
    }

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<Log>();
        Cursor cursor = openReadable().query(dbHelper.LOGS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            logs.add(getLogFromCursorRow(cursor));

            cursor.moveToNext();
        }
        closeDb();

        return logs;
    }

    @Override
    public List<Log> getLogs(int topicId) {
        return null;
    }

    @Override
    public Log getLog(int id) {
        Log log = null;
        Cursor cursor = openReadable().query(dbHelper.LOGS, null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (null !=cursor){
            cursor.moveToFirst();
            log = getLogFromCursorRow(cursor);
        }
        return log;
    }

    private Log getLogFromCursorRow(Cursor cursor){
        int id = cursor.getInt(0);
        Item item = getItemById(cursor.getInt(2));
        Topic topic = item.getTopic();
        Unit unit = getUnitById(cursor.getInt(3));
        int qty = cursor.getInt(4);
        String desc = cursor.getString(5);
        String dateTime = cursor.getString(1);
        Date date = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyyHH:mm");
        try {
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            date = new Date();
        }

        Log log = new Log(id,
                topic.getName(),
                item.getName(),
                qty,
                unit.getName(),
                desc,
                date);

        return log;
    }


    @Override
    public boolean addLog(Log log) {
        boolean result = false;
        ContentValues values = new ContentValues();
        values.put("time", log.getFormattedDate("dd MMM yyyyHH:mm"));
        values.put("itemId", getItemId(log.getItem()));
        values.put("unitId", getUnitId(log.getUnits()));
        values.put("quantity", log.getQuantity());
        values.put("description", log.getDescription());

        try {
            mDb = dbHelper.getWritableDatabase();
            mDb.insert(dbHelper.LOGS, null, values);
            mDb.close();
            result = true;
        } catch (Exception ex) {

        }
        return result;
    }

    private int getUnitId(String unitsName) {
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, "unit = ?", new String[]{unitsName}, null, null, null);
        cursor.moveToFirst();
        int unitId = cursor.getInt(0);
        closeDb();

        return unitId;
    }


    @Override
    public List<String> getUnits() {
        List<String> units = new ArrayList<String>();
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String unitName = cursor.getString(1);
            units.add(unitName);
            cursor.moveToNext();
        }
        closeDb();
        return units;
    }

    @Override
    public Unit getUnitById(int id) {
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Unit unit = new Unit(cursor.getString(1), cursor.getInt(2));

        return unit;
    }

    @Override
    public void addUnit(Unit unit) {
    }

    @Override
    public boolean deleteLog(int id) {
        boolean result = false;
        try {
            mDb = dbHelper.getWritableDatabase();
            mDb.delete(dbHelper.LOGS, "_id = ?", new String[]{String.valueOf(id)});
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }
}
