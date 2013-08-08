package carlgibson.android.logger.Controller;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    final TextView textView;
    final Calendar c;

    public TimePickerFragment(TextView textView) {
        this.textView = textView;
        c = Calendar.getInstance();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE, minute);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String timeText = dateFormat.format(c.getTime());
        textView.setText(timeText);
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}