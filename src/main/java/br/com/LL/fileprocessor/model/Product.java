package br.com.LL.fileprocessor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@JsonPropertyOrder({ "product_id", "value"})
public class Product {

    @Getter
    @Setter
    @JsonProperty("product_id")
    private long productId;

    @Getter
    @Setter
    private BigDecimal value;
    
}
