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
    private LogAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_logs);

        mLogManager = LogHandler.getInstance(getApplicationContext());
        mAdapter = new LogAdapter(this, mLogManager.getDateDescSortedLogs());
        setListAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_logs_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
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
        mAdapter.swapData(mLogManager.getDateDescSortedLogs());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log log = (Log) l.getItemAtPosition(position);
        Intent launchIntent = new Intent(this, LogDetailsActivity.class);
        launchIntent.putExtra("LogId", log.getId());
        startActivityForResult(launchIntent, 0);
    }

    public void onAddButtonClick(View v) {
        launchNewActivity(AddLogActivity.class);
    }

    private void launchNewActivity(Class<?> activityClass){
        Intent launchIntent = new Intent(this,activityClass);
        startActivity(launchIntent);
    }

}
