package carlgibson.android.logger.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import carlgibson.android.logger.R;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 26/05/13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class LogDetailsActivity extends Activity {

    private int mLogId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);
        mLogId = getIntent().getIntExtra("LogId", -1);

        Button editButton = (Button) findViewById(R.id.buttonEdit);
        Button deleteButton = (Button) findViewById(R.id.buttonDelete);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteAlert();
            }
        });
    }

    private void showDeleteAlert() {
        AlertDialog b = new AlertDialog.Builder(this)
                .setTitle("Confirm delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manageDeleteLog();
                    }
                })
                .setNegativeButton("Cancel", null).create();
        b.show();
    }

    public void manageDeleteLog() {
        LogHandler logAdapter = LogHandler.getInstance(getApplicationContext());
        String toastMessage = (logAdapter.deleteLog(mLogId)) ?
                "Log deleted" :
                "Error deleting log";
        Toast.makeText(this, toastMessage, 20).show();
        finish();
    }
    ;
}