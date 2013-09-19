package carlgibson.android.logger.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Log;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 15/09/13
 * Time: 18:56
 * To change this template use File | Settings | File Templates.
 */
public class UpdateLogEditActivity extends BaseLogEditActivity {
    private int mLogId;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mLogId = intent.getIntExtra("LogId", 0);
        setUpLogDetails(mLogId);
    }

    private void setUpLogDetails(int logId) {
        Log log = mLogManager.getLog(logId);
        setSpinnerPosByString(mTopicSpinner, log.getTopic());
        setItemSpinner();
        setSpinnerPosByString(mItemSpinner, log.getItem());
        setSpinnerPosByString(mUnitsSpinner, log.getUnits());
        EditText qty = (EditText) findViewById(R.id.editTextQty);
        qty.setText(String.valueOf(log.getQuantity()));
        setDate(log.getFormattedDate(mDateTimeBundle.getString("dateFormat")));
        setTime(log.getFormattedDate(mDateTimeBundle.getString("timeFormat")));
        EditText details = (EditText) findViewById(R.id.editTextDetails);
        details.setText(log.getDetails());
        setEditButton();
        setImages();
        setTitle(R.string.editLogDetails);
        // TODO sort out event order
    }

    private void setSpinnerPosByString(Spinner spinner, String value) {
        int position = 0;
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Object obj = adapter.getItem(i);
            position = (obj.toString().equals(value) ? i : position);
        }
        spinner.setSelection(position);
    }

    private void setEditButton() {
        Button button = (Button) findViewById(R.id.buttonSave);
        button.setText("Save");
    }

    @Override
    public void onSaveClick(View v) {
        if (!mLogManager.updateLog(getLogEntryFromDetails(mLogId))) {
            Toast.makeText(this, "Error updating log.", Toast.LENGTH_SHORT).show();
        }
        else    {
            setResult(RESULT_OK);
        }
        finish();
    }
}