
import java.math.BigDecimal;
/**
 *
 * @author bauja773
 */
public class SaleItem {
	
	private int quantityPurchased;
	private BigDecimal salePrice;

	public SaleItem(int quantityPurchased, BigDecimal salePrice) {
		this.quantityPurchased = quantityPurchased;
		this.salePrice = salePrice;
	}

	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	
}
