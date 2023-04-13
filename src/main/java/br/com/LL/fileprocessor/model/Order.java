package br.com.LL.fileprocessor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Builder
@JsonPropertyOrder({ "order_id", "total", "date", "products"})
public class Order {

    @Getter
    @Setter
    @JsonProperty("order_id")
    private long orderId;

    @Getter
    @Setter
    private BigDecimal total;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @Getter
    @Setter
    private List<Product> products;
    
}
