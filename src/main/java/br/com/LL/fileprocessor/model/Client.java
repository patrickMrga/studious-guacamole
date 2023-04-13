package br.com.LL.fileprocessor.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@JsonPropertyOrder({ "user_id", "name", "orders"})
public class Client {
    
    @Getter
    @Setter
    @JsonProperty("user_id")
    private long userId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<Order> orders;
    
}
