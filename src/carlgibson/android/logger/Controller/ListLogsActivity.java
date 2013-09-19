package carlgibson.android.logger.Controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Log;

public class ListLogsActivity extends ListActivity {

    protected LogHandler mLogManager;
    private LogAdapter mLogAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_logs);

        mLogManager = LogHandler.getInstance(getApplicationContext());
        mLogAdapter = new LogAdapter(this, mLogManager.getDateDescSortedLogs());
        setListAdapter(mLogAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_logs_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLogAdapter.swapData(mLogManager.getDateDescSortedLogs());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log log = (Log) l.getItemAtPosition(position);
        Intent launchIntent = new Intent(this, LogDetailsActivity.class);
        launchIntent.putExtra("LogId", log.getId());
        startActivity(launchIntent);
    }

    public void onAddButtonClick(View v) {
        launchNewActivity(AddLogEditActivity.class);
    }

    private void launchNewActivity(Class<?> activityClass){
        Intent launchIntent = new Intent(this,activityClass);
        launchIntent.putExtra(String.valueOf(R.string.log_edit_type),
                              LogHandler.LogEditType.Add.toString());
        startActivity(launchIntent);
    }

}
