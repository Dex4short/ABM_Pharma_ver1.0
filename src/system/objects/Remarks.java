package system.objects;

public class Remarks {
	private int rem_id;
	private Date date;
	private Time time;
	private String details;
	
	public Remarks(int rem_id, Date date, Time time, String details) {
		setRemId(rem_id);
		setDate(date);
		setTime(time);
		setDetails(details);
	}
	public Remarks(int rem_id) {
		setRemId(rem_id);
		setDate(new Date());
		setTime(new Time());
		setDetails("");
	}
	public Remarks() {
		setRemId(-1);
		setDate(new Date());
		setTime(new Time());
		setDetails("");
	}
	public Remarks(String details) {
		setRemId(-1);
		setDate(new Date());
		setTime(new Time());
		setDetails(details);
	}
	@Override
	public String toString() {
		String str = 
			"Remarks( rem_id:" + getRemId() + " )\n" +
			"\t" + getDate() + "\n" +
			"\t" + getTime() + "\n" +
			"\t" + getDetails() + "\n"
		;	
		return str;
	}
	public int getRemId() {
		return rem_id;
	}
	public void setRemId(int rem_id) {
		this.rem_id = rem_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String toStringTitleDetail() {
		return details.substring(0, details.indexOf("\n"));
	}
	public String toStringFullDetails() {
		return 
			"Date:\t" + getDate().toString() + "\n" +
			"Time:\t" + getTime().toString() + "\n" + 
			"Remarks:\t" + getDetails()
		;
	}
}
