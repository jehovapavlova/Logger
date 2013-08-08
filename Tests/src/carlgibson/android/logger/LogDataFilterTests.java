package carlgibson.android.logger;

import android.test.AndroidTestCase;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.Controller.LogDataFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 23/05/13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class LogDataFilterTests extends AndroidTestCase {

    LogDataFilter sut;

    @Override
    public void setUp() throws Exception {
        sut = new LogDataFilter();
    }

    @Override
    public void tearDown() throws Exception {

    }

    public void testFilterOne()
    {

        List<Log> logs = new ArrayList<Log>();
        logs.add(new Log(null,null, 1, null, null, new Date(12,12,12)));

        logs.add(new Log(null,null, 1, null, null, new Date(06,06,06)));
        logs.add(new Log(null,null, 1, null, null, new Date(01,01,01)));
        logs.add(new Log(null,null, 1, null, null, new Date(12,12,12)));
        logs.add(new Log(null,null, 1, null, null, new Date(06,06,06)));
        logs.add(new Log(null,null, 1, null, null, new Date(01,01,01)));
        List<Log> sorted = logs;
        sorted = sut.filterLogsByDateDesc(logs);

        for (Log log: sorted)
        {}

        assertEquals(6,sorted.size());
    }

}
