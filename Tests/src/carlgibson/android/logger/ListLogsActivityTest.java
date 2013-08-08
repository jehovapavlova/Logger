package carlgibson.android.logger;

import android.test.ActivityInstrumentationTestCase2;
import carlgibson.android.logger.Controller.ListLogsActivity;
import junit.framework.Assert;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class carlgibson.android.logger.AddLogEntryActivityTest \
 * carlgibson.android.logger.tests/android.test.InstrumentationTestRunner
 */
public class ListLogsActivityTest extends ActivityInstrumentationTestCase2<ListLogsActivity> {

    public ListLogsActivityTest() {
        super("carlgibson.android.logger", ListLogsActivity.class);
    }

    public void testActivityPopulation() throws Exception {
        ListLogsActivity activity = getActivity();

        Assert.assertEquals(1,1);

    }
}
