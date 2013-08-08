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
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 03/08/13
 * Time: 01:01
 * To change this template use File | Settings | File Templates.
 */
public class SQLLogData implements LogDataSource {

    private final DBHelper dbHelper;

    public SQLLogData(Context context) {
        dbHelper = new DBHelper(context);
    }


    @Override
    public List<Topic> getTopics() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Topic getTopicById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addTopic(Topic topic) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Log> getLogs() {
        List<Log> logs = new ArrayList<Log>();
        String sql = "SELECT * FROM LOGS;";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("LOGS",null,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Log log = new Log(
                    getTopicFromItemId(),
                    getItem(id),
                    cursor.getInt(4),
                    getUnitById(cursor.getInt(3)),
                    "desc",
                    new Date());
            logs.add(log);
        }

        return logs;
    }

    @Override
    public List<Log> getLogs(Topic topic) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Log getLog(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addLog(Log log) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> getUnits() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Unit getUnitById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
