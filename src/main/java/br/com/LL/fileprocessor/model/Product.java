package br.com.LL.fileprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({ "product_id", "value"})
public class Product {
    
    @JsonProperty("product_id")
    private long productId;
    
    private BigDecimal value;

    public Product() {
    }

    public Product(long productId, BigDecimal value) {
        this.productId = productId;
        this.value = value;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
