package carlgibson.android.logger.Controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Item;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddLogActivity extends FragmentActivity {
    private LogHandler mLogManager;
    private Spinner mTopicSpinner, mItemSpinner, mUnitsSpinner;
    private DialogFragment mDatePickerFragment, mTimePickerFragment;
    private int mLogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);

        mLogManager = LogHandler.getInstance(getApplicationContext());

        setTopicSpinner();
        setItemSpinner();
        setUnitsSpinner();
        setDate(null);
        setTime(null);


        Intent intent = getIntent();
        String editType = intent.getStringExtra(String.valueOf(R.string.log_edit_type));
        mLogId = intent.getIntExtra("LogId", 0);

        if (editType.equals(LogHandler.LogEditType.Update.toString()) && mLogId > 0) {
            setUpLogDetails(mLogId);
            setEditButton();
        }
    }

    private void setUpLogDetails(int logId) {
        Log log = mLogManager.getLog(logId);
        setSpinnerByString(mTopicSpinner, log.getTopic());
        setItemSpinner();
        setUnitsSpinner();
        setSpinnerByString(mItemSpinner, log.getItem());
        setSpinnerByString(mUnitsSpinner, log.getUnits());
        EditText qty = (EditText) findViewById(R.id.editTextQty);
        qty.setText(String.valueOf(log.getQuantity()));
        setDate(log.getFormattedDate("dd MMM yyyy"));
        setTime(log.getFormattedDate("HH:mm"));
        EditText details = (EditText) findViewById(R.id.editTextDetails);
        details.setText(log.getDetails());
        setEditButton();
        setImages();
        setTitle(R.string.editLogDetails);
    }

    private void setImages() {
    }

    private void setEditButton() {
        Button button = (Button) findViewById(R.id.buttonSave);
        button.setText("Save");
    }

    private void setSpinnerByString(Spinner spinner, String value) {
        int position = 0;
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int i = 0;i < adapter.getCount();i++){
            Object obj = adapter.getItem(i);
            position = (obj.toString().equals(value) ? i : position);
        }
        spinner.setSelection(position);
    }

    private void setDate(String date) {
        TextView dateText = (TextView) findViewById(R.id.textDatePicker);
        if (null == date) {
            dateText = formatAndSetDate(dateText, "dd MMM yyyy");
        } else dateText.setText(date);
        mDatePickerFragment = new DatePickerFragment(dateText);
    }

    private void setTime(String time) {
        TextView timeText = (TextView) findViewById(R.id.textTimePicker);
        if (null == time) {
            timeText = formatAndSetDate(timeText, "HH:mm");
        } else timeText.setText(time);
        mTimePickerFragment = new TimePickerFragment(timeText);
    }

    private TextView formatAndSetDate(TextView textView, String format) {
        Calendar c = Calendar.getInstance(Locale.UK);
        SimpleDateFormat df = new SimpleDateFormat(format);
        textView.setText(df.format(c.getTime()));
        return textView;
    }

    private void setQtyAndUnits(int selectionIndex, int defaultQty) {
        EditText quantity = (EditText) findViewById(R.id.editTextQty);
        quantity.setText(String.valueOf(defaultQty));
        mUnitsSpinner.setSelection(selectionIndex);
    }

    private void setUnitsSpinner() {
        mUnitsSpinner = (Spinner) findViewById(R.id.spinnerUnit);
        ArrayAdapter<String> qtyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                mLogManager.getUnits());
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUnitsSpinner.setAdapter(qtyAdapter);
    }

    private void setTopicSpinner() {
        mTopicSpinner = (Spinner) findViewById(R.id.spinnerTopic);
        ArrayAdapter<Topic> topicAdapter = new ArrayAdapter<Topic>(this,
                android.R.layout.simple_spinner_item, mLogManager.getTopics());
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTopicSpinner.setAdapter(topicAdapter);
        mTopicSpinner.setSelection(0);
        mTopicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                setItemSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setItemSpinner() {
        mItemSpinner = (Spinner) findViewById(R.id.spinnerItem);
        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_spinner_item, mLogManager.getItems(topic));
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItemSpinner.setAdapter(itemAdapter);
        mItemSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) mItemSpinner.getSelectedItem();
                setQtyAndUnits(item.getDefaultUnitId(), item.getDefaultQty());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onSaveClick(View v) {
        if (mLogId > 0) {
            mLogManager.updateLog(getLogEntryFromDetails());
        } else {
            mLogManager.addLogEntry(getLogEntryFromDetails());
        }
        finish();
    }

    public void onCancelClick(View v) {
        finish();
    }

    public void onAddPictureClick(View v) {
        Toast.makeText(this, "Add picture clicked", 200).show();
        // TODO Add picture selection functionality
    }

    public void onEditItemClick(MenuItem item) {
        launchNewActivity(EditItemActivity.class);
    }

    public void onAddItemClick(MenuItem item) {
        launchNewActivity(AddItemActivity.class);
    }

    public void onEditTopicClick(MenuItem item) {
        // TODO Edit Topic activity
    }

    public void onAddTopicClick(MenuItem item) {
        // TODO Add Topic activity
    }

    private Log getLogEntryFromDetails() {

        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        Item item = (Item) mItemSpinner.getSelectedItem();
        TextView qty = (TextView) findViewById(R.id.editTextQty);
        int quantity = Integer.parseInt(qty.getText().toString());
        String units = (String) mUnitsSpinner.getSelectedItem();
        TextView desc = (TextView) findViewById(R.id.editTextDetails);
        TextView date = (TextView) findViewById(R.id.textDatePicker);
        String dateString = date.getText().toString();
        TextView time = (TextView) findViewById(R.id.textTimePicker);
        String timeString = time.getText().toString();
        Date dateTime = parseDateTime(dateString, timeString);

        return new Log(mLogId, topic.getName(), item.getName(), quantity, units, desc.getText().toString(), dateTime);
    }

    private Date parseDateTime(String date, String time) {
        Date d;
        String dateTime = date + time;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("dd MMM yyyyHH:mm");
        try {
            d = sdf.parse(dateTime);
        } catch (ParseException ex) {
            d = new Date();
        }
        return d;
    }

    public void onShowTimePickerDialog(View v) {
        mTimePickerFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onShowDatePickerDialog(View v) {
        mDatePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void onShowNumberPickerDialog(View v) {
        if (Build.VERSION.SDK_INT > 11) {
            NumberPicker numberPicker = new NumberPicker(this);
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(100);
        } else {
            // TODO Add functionality to change number picker based on sdk version
        }
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

    private void launchNewActivity(Class<?> activityClass) {
        Intent launchIntent = new Intent(this, activityClass);
        startActivity(launchIntent);
    }

    public void onResume() {
        super.onResume();
        setDate(null);
        setTime(null);
    }
}
