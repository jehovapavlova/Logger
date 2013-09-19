package carlgibson.android.logger.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    final TextView textView;
    final Calendar c;
    private String mDateFormat;
    private SimpleDateFormat mSimpleDf;

    public DatePickerFragment(TextView textView) {
        this.textView = textView;
        c = Calendar.getInstance();
        mSimpleDf = new SimpleDateFormat();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String date = textView.getText().toString();
        mDateFormat = getArguments().getString("dateFormat");
        mSimpleDf.applyPattern(mDateFormat);

        try {
            c.setTime(mSimpleDf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        c.set(year, monthOfYear, dayOfMonth);
        String dateText = mSimpleDf.format(c.getTime());
        textView.setText(dateText);
    }
}
