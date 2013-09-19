package carlgibson.android.logger.Controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Item;
import carlgibson.android.logger.model.Log;
import carlgibson.android.logger.model.Topic;
import carlgibson.android.logger.model.Unit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Desktop
 * Date: 15/09/13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseLogEditActivity extends FragmentActivity implements OnItemSelectedListener {
    protected LogHandler mLogManager;
    protected Spinner mTopicSpinner;
    protected Spinner mItemSpinner;
    protected Spinner mUnitsSpinner;
    protected DialogFragment mDatePickerFragment;
    protected DialogFragment mTimePickerFragment;
    protected Bundle mDateTimeBundle;
    private final String TAG_SPINNER_VALUE = "Initialised";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_edit);

        mLogManager = LogHandler.getInstance(getApplicationContext());

        setDateFormatBundle();
        setSpinners();
        Item item = (Item) mItemSpinner.getSelectedItem();
        setQtyAndUnits(item.getDefaultUnitId(), item.getDefaultQty());
        setDate(null);
        setTime(null);
    }

    protected void setDateFormatBundle() {
        mDateTimeBundle = new Bundle();
        mDateTimeBundle.putString("dateFormat", getString(R.string.date_format));
        mDateTimeBundle.putString("timeFormat", getString(R.string.time_format));
    }

    protected void setImages() {
    }

    protected void setDate(String date) {
        TextView dateText = (TextView) findViewById(R.id.textDatePicker);
        if (null == date) {
            dateText = formatAndSetDate(dateText, mDateTimeBundle.getString("dateFormat"));
        } else dateText.setText(date);
        mDatePickerFragment = new DatePickerFragment(dateText);
        mDatePickerFragment.setArguments(mDateTimeBundle);
    }

    protected void setTime(String time) {
        TextView timeText = (TextView) findViewById(R.id.textTimePicker);
        if (null == time) {
            timeText = formatAndSetDate(timeText, mDateTimeBundle.getString("timeFormat"));
        } else timeText.setText(time);
        mTimePickerFragment = new TimePickerFragment(timeText);
    }

    protected TextView formatAndSetDate(TextView textView, String format) {
        Calendar c = Calendar.getInstance(Locale.UK);
        SimpleDateFormat df = new SimpleDateFormat(format);
        textView.setText(df.format(c.getTime()));
        return textView;
    }

    protected void setSpinners() {
        setTopicSpinner();
        setItemSpinner();
        setUnitsSpinner();
    }

    protected void setUnitsSpinner() {
        mUnitsSpinner = (Spinner) findViewById(R.id.spinnerUnit);
        ArrayAdapter<Unit> qtyAdapter = new ArrayAdapter<Unit>(this,
                android.R.layout.simple_spinner_item,
                mLogManager.getUnits());
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUnitsSpinner.setAdapter(qtyAdapter);
        mUnitsSpinner.setTag(TAG_SPINNER_VALUE);
    }

    protected void setTopicSpinner() {
        mTopicSpinner = (Spinner) findViewById(R.id.spinnerTopic);
        ArrayAdapter<Topic> topicAdapter = new ArrayAdapter<Topic>(this,
                android.R.layout.simple_spinner_item, mLogManager.getTopics());
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTopicSpinner.setAdapter(topicAdapter);
        mTopicSpinner.setTag(TAG_SPINNER_VALUE);
        mTopicSpinner.setOnItemSelectedListener(this);
    }

    protected void setItemSpinner() {
        mItemSpinner = (Spinner) findViewById(R.id.spinnerItem);
        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        ArrayAdapter<Item> itemAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_spinner_item, mLogManager.getItems(topic));
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItemSpinner.setAdapter(itemAdapter);
        mItemSpinner.setTag(TAG_SPINNER_VALUE);
        mItemSpinner.setOnItemSelectedListener(this);
    }

    protected void setSpinner(int viewId, List<Object> objects) {
        // TODO Create generic spinner setter
        Spinner spinner = (Spinner) findViewById(viewId);
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_spinner_item, objects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    protected void setQtyAndUnits(int selectionIndex, int defaultQty) {
        EditText quantity = (EditText) findViewById(R.id.editTextQty);
        quantity.setText(String.valueOf(defaultQty));
        mUnitsSpinner.setSelection(selectionIndex, false);
    }

    protected Log getLogEntryFromDetails(int id) {

        Topic topic = (Topic) mTopicSpinner.getSelectedItem();
        Item item = (Item) mItemSpinner.getSelectedItem();
        TextView qty = (TextView) findViewById(R.id.editTextQty);
        int quantity = Integer.parseInt(qty.getText().toString());
        Unit units = (Unit) mUnitsSpinner.getSelectedItem();
        TextView desc = (TextView) findViewById(R.id.editTextDetails);
        TextView date = (TextView) findViewById(R.id.textDatePicker);
        String dateString = date.getText().toString();
        TextView time = (TextView) findViewById(R.id.textTimePicker);
        String timeString = time.getText().toString();
        Date dateTime = parseDateTime(dateString, timeString);

        return new Log(id, topic.getName(), item.getName(), quantity, units.getName(), desc.getText().toString(), dateTime);
    }

    protected Date parseDateTime(String date, String time) {
        Date d;
        String dateTime = date + time;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(getString(R.string.date_time_format));
        try {
            d = sdf.parse(dateTime);
        } catch (ParseException ex) {
            d = new Date();
        }
        return d;
    }

    protected void launchNewActivity(Class<?> activityClass) {
        Intent launchIntent = new Intent(this, activityClass);
        startActivity(launchIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String tag = (String) parent.getTag();
        if (null != tag && tag.equalsIgnoreCase(TAG_SPINNER_VALUE)) {
            parent.setTag("");
            return;
        }

        switch (parent.getId()) {
            case R.id.spinnerItem:
                Item item = (Item) mItemSpinner.getSelectedItem();
                setQtyAndUnits(item.getDefaultUnitId() - 1, item.getDefaultQty());
                break;
            case R.id.spinnerTopic:
                setItemSpinner();
                break;
            default:
                Toast.makeText(this, "item spinner:" + R.id.spinnerItem, Toast.LENGTH_SHORT)
                        .show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public abstract void onSaveClick(View v);

    public void onCancelClick(View v) {
        finish();
    }

    public void onAddPictureClick(View v) {
        Toast.makeText(this, "Add picture clicked", 200).show();
        // TODO Add picture selection functionality
    }

    public void onAddItemClick(MenuItem item) {
        launchNewActivity(AddItemActivity.class);
    }

    public void onAddTopicClick(MenuItem item) {
        // TODO Add Topic activity
    }

    public void onEditItemClick(MenuItem item) {
        launchNewActivity(EditItemActivity.class);
    }

    public void onEditTopicClick(MenuItem item) {
        // TODO Edit Topic activity
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
}
