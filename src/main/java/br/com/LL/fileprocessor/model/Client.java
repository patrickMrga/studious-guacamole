package br.com.LL.fileprocessor.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonPropertyOrder({ "user_id", "name", "orders"})
public class Client {
    
    @JsonProperty("user_id")
    private long userId;
    
    private String name;
    
    private List<Order> orders;

    public Client() {
    }

    public Client(long userId, String name, List<Order> orders) {
        this.userId = userId;
        this.name = name;
        this.orders = orders;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
