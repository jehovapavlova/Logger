package carlgibson.android.logger.Controller;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import carlgibson.android.logger.R;

public class ListLogsActivity extends ListActivity {

    protected LogHandler mLogManager;
    private LogAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_logs);

        Button addLogButton = (Button) findViewById(R.id.buttonAddNewLog);
        addLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchAddLogsActivity();
            }
        });

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

    private void LaunchAddLogsActivity() {
        Intent launchIntent = new Intent(ListLogsActivity.this,
                AddLogActivity.class);
        startActivityForResult(launchIntent, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.swapData(mLogManager.getDateDescSortedLogs());
        TextView view = (TextView)findViewById(R.id.textViewFilter);
        view.setText("Total logs: " + mAdapter.getCount());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent launchIntent = new Intent(ListLogsActivity.this,
                LogDetailsActivity.class);
        startActivityForResult(launchIntent, 0);
    }

}
