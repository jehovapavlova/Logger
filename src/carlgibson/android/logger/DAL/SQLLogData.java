package carlgibson.android.logger.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

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
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<Log>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LOGS",null,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Log log = new Log(
                    getTopicFromItemId(),
                    getItem(),
                    cursor.getInt(4),
                    getUnitById(cursor.getInt(3)).ToString(),
                    "desc",
                    new Date());
            logs.add(log);
        }

        return logs;
    }

    private String getTopicFromItemId() {
        return null;
    }
    private String getItem() {
        return null;
    }
    @Override
    public List<Log> getLogs(Topic topic) {
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
        return null;
    }

    @Override
    public Unit getUnitById(int id) {
        return null;
    }
}
