package carlgibson.android.logger;

import android.test.AndroidTestCase;
import carlgibson.android.logger.Controller.LogHandler;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.DAL.MockLogData;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 23/05/13
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
public class LogManagerTests extends AndroidTestCase {

    LogHandler sut;

    @Override
    public void setUp() throws Exception {

        Field field = LogHandler.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        field = MockLogData.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        sut = LogHandler.getInstance();
    }

    @Override
    public void tearDown() throws Exception {
        Field field = LogHandler.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);

        field = MockLogData.class.getDeclaredField("mInstance");
        field.setAccessible(true);
        field.set(null,null);
    }

    public void testLogManagerGetLogs() throws Exception {

        List<Log> logs = sut.getDateDescSortedLogs();
        int actual = logs.size();

        assertEquals(0,actual);
    }

    public void testLogManagerLogsAfterAddition() throws Exception {

        List<Log> l1 = sut.getDateDescSortedLogs();
        int before = l1.size();

        sut.addLogEntry(new Log(null,null,1,null,null,new Date()));

        List<Log> l2 = sut.getDateDescSortedLogs();
        int after = l2.size();

        assertEquals(before+1, after);
    }
}
