package br.com.LL.fileprocessor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({ "order_id", "total", "date", "products"})
public class Order {
    
    @JsonProperty("order_id")
    private long orderId;
    
    private BigDecimal total;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
    
    private List<Product> products;

    public Order() {
    }

    public Order(long orderId, BigDecimal total, Date date, List<Product> products) {
        this.orderId = orderId;
        this.total = total;
        this.date = date;
        this.products = products;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
