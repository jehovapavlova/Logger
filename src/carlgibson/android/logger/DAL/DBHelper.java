package carlgibson.android.logger.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 10/07/13
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "logs";
    private static final int DATABASE_VERSION = 1;
    private static final String COLUMN_ID = "_id integer primary key autoincrement";
    public static final String TOPICS = "Topics";
    public static final String ITEMS = "Items";
    public static final String LOGS = "Logs";
    public static final String UNITS = "Units";

    private static final String UNITS_TABLE = "CREATE TABLE "+ UNITS +" ("
            + COLUMN_ID + ", unit text NOT NULL, defaultValue integer NOT NULL)";

    private static final String TOPICS_TABLE = "CREATE TABLE "+TOPICS+" ("
            + COLUMN_ID + ", topic text NOT NULL)";

    private static final String ITEMS_TABLE = new StringBuilder()
            .append("CREATE TABLE " + ITEMS + " (")
            .append(COLUMN_ID)
            .append(", item text NOT NULL")
            .append(", topicId integer NOT NULL")
            .append(", defaultUnitId integer NOT NULL")
            .append(", defaultQuantity integer NOT NULL")
            .append(")")
            .toString();

    private static final String LOGS_TABLE = new StringBuilder()
            .append("CREATE TABLE " + LOGS + " (")
            .append(COLUMN_ID)
            .append(", time text NOT NULL")
            .append(", itemId integer NOT NULL")
            .append(", unitId integer NOT NULL")
            .append(", quantity integer NOT NULL")
            .append(", description text NOT NULL")
            .append(")")
            .toString();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UNITS_TABLE);
        database.execSQL(ITEMS_TABLE);
        database.execSQL(TOPICS_TABLE);
        database.execSQL(LOGS_TABLE);
        fillTopics(database);
        fillUnits(database);
        fillItems(database);
    }

    private void fillTopics(SQLiteDatabase database) {
        ContentValues values = new ContentValues();

        List<String> topics = new ArrayList<String>();
        topics.add("Exercise");
        topics.add("Food");
        topics.add("Symptoms");

        for (String topic: topics)
        {
            values.put("topic",topic);
            database.insert(TOPICS, null, values);
            values.clear();
        }
    }

    private void fillItems(SQLiteDatabase database)
    {
        ContentValues values = new ContentValues();

        List<Object[]> items = new ArrayList<Object[]>();
        items.add(new Object[]{"Running",1,3,5});
        items.add(new Object[]{"Swimming",1,7,20});
        items.add(new Object[]{"Gym",1,0,45});
        items.add(new Object[]{"Sandwich",2,2,300});
        items.add(new Object[]{"Toast",2,4,300});
        items.add(new Object[]{"Salad",2,5,300});
        items.add(new Object[]{"Stomach ache",3,6,30});
        items.add(new Object[]{"Headache",3,1,10});
        items.add(new Object[]{"Hives",3,0,30});

        for (Object[] obj: items)
        {
            values.put("item",(String)obj[0]);
            values.put("topicId",(Integer)obj[1]);
            values.put("defaultUnitId",(Integer)obj[2]);
            values.put("defaultQuantity",(Integer)obj[3]);

            database.insert(ITEMS, null, values);
            values.clear();
        }
    }

    private void fillUnits(SQLiteDatabase database)
    {
        ContentValues values = new ContentValues();

        List<Object[]> units = new ArrayList<Object[]>();
                
        units.add(new Object[]{1,"Minutes", 10});
        units.add(new Object[]{2,"Hours",1});
        units.add(new Object[]{3,"Calories",300});
        units.add(new Object[]{4,"Miles",3});
        units.add(new Object[]{5,"Metres",50});
        units.add(new Object[]{6,"Kilometres",5});
        units.add(new Object[]{7,"Each",1});
        units.add(new Object[]{8,"Lengths",20});

        for (Object[] obj: units)
        {
            values.put("_id",(Integer)obj[0]);
            values.put("unit",(String)obj[1]);
            values.put("defaultValue",(Integer)obj[2]);
            database.insert(UNITS, null, values);
            values.clear();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
