package domain;

import domain.Product;
import java.math.BigDecimal;
/**
 *
 * @author bauja773
 */
public class SaleItem {
	
	private int quantityPurchased;
	private BigDecimal salePrice;        
        private Product product;

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return sale;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
        private Sale sale;

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
        
        public BigDecimal getItemTotal() {
            return salePrice.multiply(new BigDecimal(quantityPurchased));
        }
	
	
}
