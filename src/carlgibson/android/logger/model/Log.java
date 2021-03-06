package carlgibson.android.logger.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private int id;
    private String topic;
	private String item;
	private int quantity;
	private String quantityUnits;
	private Date date;
	private String details;

    public final static String TIMEDATE_FORMAT = "HH:mm dd-MMM-yy";

    public Log(int id,String topic, String item, int qty,
               String units, String details, Date date)
	{
        this.id = id;
        this.topic = topic;
		this.item =item;
		this.quantity = qty;
		this.quantityUnits = units;
		this.details = details;
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public String getItem() {
		return item;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getUnits() {
		return quantityUnits;
	}
	
	public Date getDate() {
		return date;
	}

    public String getFormattedDate(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

	
	public String getDetails() {
		return details;
	}

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat(TIMEDATE_FORMAT);
        String date =  df.format(this.date);

        return String.format("%s %s %s %s",item,date,quantity, quantityUnits);
    }

    public int getId() {
        return id;
    }
}
