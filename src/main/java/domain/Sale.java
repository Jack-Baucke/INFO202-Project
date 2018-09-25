package domain;

import domain.SaleItem;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author bauja773
 */
public class Sale {

    private int saleID;
    private Date date;
    private String status;

    private Customer customer;
    private  ArrayList<SaleItem> items = new ArrayList<>();

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addItem(SaleItem saleItem) {
        items.add(saleItem);
    }
    
    public ArrayList<SaleItem> getItems() {
        return items;
    }
    
    public String printItems() {
        String result = "";
        for (SaleItem item : items) {
            result += item.getQuantityPurchased() + " x " + item.getProduct().getName() + "\n";
        }
        return result;
    }

    public BigDecimal getTotal() {
        BigDecimal total = new BigDecimal(0);
        for (SaleItem item : items) {
            total = total.add(item.getSalePrice());
            System.out.println("getSalePrice:" + item.getSalePrice());
            System.out.println("total=" + total);
        }
        return total;
    }

}
