package carlgibson.android.logger.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private String topic;
	private String item;
	private int quantity;
	private String quantityUnits;
	private Date dateTime;
	private String description;
	
	public Log(String topic, String item, int qty,
               String units, String desc, Date dateTime)
	{
		this.topic = topic;
		this.item =item;
		this.quantity = qty;
		this.quantityUnits = units;
		this.description = desc;
		this.dateTime = dateTime;
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
	
	public Date getDateTime() {
		return dateTime;
	}

    public String getFormattedDate(String format)
    {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(dateTime);
    }

	
	public String getDescription() {
		return description;
	}

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("HH:MM dd-MMM-yy");
        String date =  df.format(dateTime);

        return String.format("%s %s %s %s",item,date,quantity, quantityUnits);
    }
}
