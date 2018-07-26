import java.util.Date;

/**
 *
 * @author bauja773
 */
public class Sale {
	
	private int saleID;
	private Date date;
	private String status;

	public Sale(int saleID, Date date, String status) {
		this.saleID = saleID;
		this.date = date;
		this.status = status;
	}

	public int getSaleID() {
		return saleID;
	}

	public Date getDate() {
		return date;
	}

	public String getStatus() {
		return status;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Sale{" + "saleID=" + saleID + ", date=" + date + ", status=" + status + '}';
	}	
	
}
