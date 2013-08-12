package carlgibson.android.logger.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import carlgibson.android.logger.R;
import carlgibson.android.logger.model.Log;

import java.util.List;

public class LogAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Log> logs;

    public LogAdapter(Context context, List<Log> logs) {
        this.logs = logs;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return logs.size();
    }

    @Override
    public Object getItem(int position) {
        return logs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView = mInflater.inflate(R.layout.logs_list_row, parent, false);
        }

        TextView item = (TextView) convertView.findViewById(R.id.itemRowText);
        TextView qty = (TextView) convertView.findViewById(R.id.qtyRowText);
        TextView date = (TextView) convertView.findViewById(R.id.dateRowText);
        TextView time = (TextView) convertView.findViewById(R.id.timeRowText);

        Log log = logs.get(position);
        item.setText(log.getItem());
        qty.setText(log.getQuantity()+" "+log.getUnits());
        date.setText(log.getFormattedDate("dd MMM yy"));
        time.setText(log.getFormattedDate("HH:mm"));

        return convertView;
    }

    public void swapData(List<Log> newLogs)
    {
        this.logs = newLogs;
        notifyDataSetChanged();
    }

}
