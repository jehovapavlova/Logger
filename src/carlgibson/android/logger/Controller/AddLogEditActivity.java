package carlgibson.android.logger.Controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import carlgibson.android.logger.R;

public class AddLogEditActivity extends BaseLogEditActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_log_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addItem:
                onAddItemClick(item);
                return true;
            case R.id.action_editItem:
                onEditItemClick(item);
                return true;
            case R.id.action_addTopic:
                onAddTopicClick(item);
                return true;
            case R.id.action_editTopic:
                onEditTopicClick(item);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveClick(View v) {
        mLogManager.addLogEntry(getLogEntryFromDetails(0));
        finish();
    }

    public void onResume() {
        super.onResume();
    }
}
