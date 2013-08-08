package carlgibson.android.logger.Controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    final TextView textView;
    final Calendar c;

    public DatePickerFragment(TextView textView) {
        this.textView = textView;
        c = Calendar.getInstance();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        c.set(year, monthOfYear, dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        String dateText = dateFormat.format(c.getTime());
        textView.setText(dateText);
    }
}
