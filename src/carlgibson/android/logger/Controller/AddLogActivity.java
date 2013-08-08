package carlgibson.android.logger.Controller;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddLogActivity extends FragmentActivity {
    private LogHandler mLogManager;
    private Spinner mTopicSpinner, mItemSpinner, mQuantitySpinner;
    private DialogFragment mDatePickerFragment, mTimePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);

        mLogManager = LogHandler.getInstance();

        setTopicSpinner();
        setItemSpinner();
        setQuantitySpinner();
        setDate();
        setTime();
        setSaveButton();
        setCancelButton();

        mTopicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                setItemSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void setDate() {
        TextView dateText = (TextView) findViewById(R.id.textDatePicker);
        dateText = FormatAndSetDate(dateText, "dd MMM yyyy");
        mDatePickerFragment = new DatePickerFragment(dateText);
    }

    private void setTime() {
        TextView timeText = (TextView) findViewById(R.id.textTimePicker);
        timeText = FormatAndSetDate(timeText, "HH:mm");
        mTimePickerFragment = new TimePickerFragment(timeText);
    }

    private TextView FormatAndSetDate(TextView textView, String format) {
        Calendar c = Calendar.getInstance(Locale.UK);
        SimpleDateFormat df = new SimpleDateFormat(format);
        textView.setText(df.format(c.getTime()));
        return textView;
    }

    private void setQuantitySpinner() {
        mQuantitySpinner = (Spinner) findViewById(R.id.spinnerQty);
        ArrayAdapter<String> qtyAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                mLogManager.getQuantities());
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mQuantitySpinner.setAdapter(qtyAdapter);
    }

    private void setTopicSpinner() {
        mTopicSpinner = (Spinner) findViewById(R.id.spinnerTopic);
        ArrayAdapter<Topic> topicAdapter = new ArrayAdapter<Topic>(this,
                android.R.layout.simple_spinner_item, mLogManager.getTopics());
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTopicSpinner.setAdapter(topicAdapter);
        mTopicSpinner.setSelection(0);
    }

    private void setItemSpinner() {
        mItemSpinner = (Spinner) findViewById(R.id.spinnerItem);
        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, topic.getItems());
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItemSpinner.setAdapter(itemAdapter);
    }

    private void setSaveButton() {
        final Button saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogManager.addLogEntry(getLogEntryFromDetails());
                finish();
            }

            ;
        });
    }

    private void setCancelButton() {
        final Button cancelButton = (Button) findViewById(R.id.buttonCancel);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

            ;
        });
    }

    private Log getLogEntryFromDetails() {

        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        String item = (String) mItemSpinner.getSelectedItem();
        TextView qty = (TextView) findViewById(R.id.editTextQty);
        int quantity = Integer.parseInt(qty.getText().toString());
        String units = (String) mQuantitySpinner.getSelectedItem();
        TextView desc = (TextView) findViewById(R.id.editTextDetails);
        TextView date = (TextView) findViewById(R.id.textDatePicker);
        String dateString = date.getText().toString();
        TextView time = (TextView) findViewById(R.id.textTimePicker);
        String timeString = time.getText().toString();
        Date dateTime = parseDateTime(dateString, timeString);

        return new Log(topic.getName(), item, quantity, units, desc.getText().toString(), dateTime);
    }

    private Date parseDateTime(String date, String time) {
        Date d = null;
        String dateTime = date + time;
        boolean result = true;
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

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_log_menu, menu);
        return true;
    }

    public void onResume() {
        super.onResume();
        setDate();
        setTime();
    }
}
