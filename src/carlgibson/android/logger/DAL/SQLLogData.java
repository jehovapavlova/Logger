package carlgibson.android.logger.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import carlgibson.android.logger.R;
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
    private Context mContext;

    public SQLLogData(Context context) {
        mContext = context;
        dbHelper = new DBHelper(mContext);
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

        return topics;
    }

    @Override
    public Topic getTopicById(int id) {
        Topic topic = null;

        for (Topic t : getTopics()) {
            if (t.getId() == id) topic = t;
        }

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

        return items;
    }

    @Override
    public List<Item> getItems(int topicId) {
        List<Item> items = new ArrayList<Item>();
        String[] args = {String.valueOf(topicId)};

        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "topicId = ?", args, null, null, null);
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

        return items;
    }

    @Override
    public Item getItemById(int id) {
        String[] args = {String.valueOf(id)};
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "_id = ?", args, null, null, null);
        cursor.moveToFirst();
        String itemName = cursor.getString(1);
        Integer qty = cursor.getInt(4);
        Topic topic = getTopicById(cursor.getInt(2));
        int unitId = cursor.getInt(3);

        Item item = new Item(itemName, qty, unitId, topic);

        return item;
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

        return logs;
    }

    @Override
    public List<Log> getLogs(int topicId) {
        return null;
    }

    @Override
    public Log getLog(int id) {
        Log log = null;
        String[] args = {String.valueOf(id)};
        Cursor cursor = openReadable().query(dbHelper.LOGS, null, "_id = ?", args, null, null, null);

        if (null != cursor) {
            cursor.moveToFirst();
            log = getLogFromCursorRow(cursor);
        }

        return log;
    }


    @Override
    public boolean addLog(Log log) {
        boolean result = false;
        ContentValues logValues = getLogContentValues(log);
        try {
            mDb = dbHelper.getWritableDatabase();
            mDb.insert(dbHelper.LOGS, null, logValues);

            result = true;
        } catch (Exception ex) {
        }
        return result;
    }



    @Override
    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<Unit>();
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Unit unit = new Unit(cursor.getInt(0), cursor.getString(1));
            units.add(unit);
            cursor.moveToNext();
        }

        return units;
    }

    @Override
    public Unit getUnitById(int id) {
        String[] args = {String.valueOf(id)};
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, "_id = ?", args, null, null, null);
        cursor.moveToFirst();
        Unit unit = new Unit(cursor.getInt(0), cursor.getString(1));

        return unit;
    }

    @Override
    public void addUnit(Unit unit) {
    }

    @Override
    public boolean deleteLog(int id) {
        return deleteFromDb(dbHelper.LOGS, id);
    }

    @Override
    public boolean updateLog(Log log) {
        boolean result = false;
        String[] args = {String.valueOf(log.getId())};
        try {
            mDb = dbHelper.getReadableDatabase();
            mDb.update(dbHelper.LOGS, getLogContentValues(log), "_id = ?", args);
            result = true;
        } catch (Exception ex) {
        }
        return result;
    }

    private boolean deleteFromDb(String tableName, int id) {
        boolean result = false;
        String[] args = {String.valueOf(id)};
        try {
            mDb = dbHelper.getWritableDatabase();
            mDb.delete(tableName, "_id = ?", args);
            result = true;
        } catch (Exception ex) {
        }

        return result;
    }

    private Log getLogFromCursorRow(Cursor cursor) {
        int id = cursor.getInt(0);
        Item item = getItemById(cursor.getInt(2));
        Topic topic = item.getTopic();
        Unit unit = getUnitById(cursor.getInt(3));
        int qty = cursor.getInt(4);
        String desc = cursor.getString(5);
        String dateTime = cursor.getString(1);
        Date date;

        SimpleDateFormat sdf = new SimpleDateFormat(mContext.getString(R.string.date_time_format));
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

    private ContentValues getLogContentValues(Log log) {

        ContentValues values = new ContentValues();
        values.put("time", log.getFormattedDate(mContext.getString(R.string.date_time_format)));
        values.put("itemId", getItemId(log.getItem()));
        values.put("unitId", getUnitId(log.getUnits()));
        values.put("quantity", log.getQuantity());
        values.put("description", log.getDetails());
        return values;
    }

    private int getUnitId(String unitsName) {
        String[] args = {unitsName};
        Cursor cursor = openReadable().query(dbHelper.UNITS, null, "unit = ?", args, null, null, null);
        cursor.moveToFirst();
        int unitId = cursor.getInt(0);

        return unitId;
    }

    private int getItemId(String itemName) {
        String[] args = {itemName};
        Cursor cursor = openReadable().query(dbHelper.ITEMS, null, "item = ?", args, null, null, null);
        cursor.moveToFirst();
        int itemId = cursor.getInt(0);

        return itemId;
    }


}
