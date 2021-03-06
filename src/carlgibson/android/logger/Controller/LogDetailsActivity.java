package carlgibson.android.logger.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 26/05/13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class LogDetailsActivity extends Activity {

    private int mLogId;
    private LogHandler mLogHandler;
    private Log mLog;
    static final int UPDATE_REQ_CODE = 1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);

        mLogHandler = LogHandler.getInstance(getApplicationContext());
        mLogId = getIntent().getIntExtra("LogId", -1);
        mLog = mLogHandler.getLog(mLogId);

        setNameAndText(findViewById(R.id.topic_detail), R.string.topic, mLog.getTopic());
        setNameAndText(findViewById(R.id.item_detail), R.string.item, mLog.getItem());
        setNameAndText(findViewById(R.id.quantity_detail), R.string.quantity, mLog.getQuantity() + " " + mLog.getUnits());
        setNameAndText(findViewById(R.id.time_detail), R.string.time, mLog.getFormattedDate(Log.TIMEDATE_FORMAT));
        setNameAndText(findViewById(R.id.details_detail), R.string.details, mLog.getDetails());
    }

    private void setNameAndText(View v, int nameId, String value) {
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView text = (TextView) v.findViewById(R.id.text);
        String s = getResources().getString(nameId);
        name.setText(s);
        text.setText(value);
    }

    public void deleteLogClick(View v) {
        AlertDialog b = new AlertDialog.Builder(this)
                .setTitle("Confirm delete")
                .setMessage("Are you sure?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manageDeleteLog();
                    }
                })
                .create();
        b.show();
    }

    public void manageDeleteLog() {
        LogHandler logAdapter = LogHandler.getInstance(getApplicationContext());
        String toastMessage = (logAdapter.deleteLog(mLogId)) ?
                "Log deleted" :
                "Error deleting log";
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onEditLogClick(View v) {
        Intent launchIntent = new Intent(this, UpdateLogEditActivity.class);
        launchIntent.putExtra(getString(R.string.log_edit_type),
                LogHandler.LogEditType.Update.toString());
        launchIntent.putExtra("LogId", mLogId);
        startActivityForResult(launchIntent,UPDATE_REQ_CODE);
    }

    public void onCancelLogClick(View v) {
       finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        }
    }

}