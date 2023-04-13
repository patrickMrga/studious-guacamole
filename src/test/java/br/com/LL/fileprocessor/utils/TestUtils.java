package br.com.LL.fileprocessor.utils;

import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.model.Order;
import br.com.LL.fileprocessor.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtils {

    public static Client buildClientAlice() {
        return Client
                .builder()
                .name("Alice Delonghi")
                .userId(1L)
                .build();
    }

    public static Client buildClientBob() {
        return Client
                .builder()
                .name("Robert Johnson III")
                .userId(2L)
                .build();
    }

    public static Client buildClientCecilia() {
        return Client
                .builder()
                .name("Cecilia Jolene Pininfarina")
                .userId(3L)
                .build();
    }
    
    public static List<Order> buildSingleProductOrder1() {
        var products = new ArrayList<Product>();
        products.add(Product
                .builder()
                .productId(1L)
                .value(new BigDecimal("150.0"))
                .build());
        
        var orders = new ArrayList<Order>();
        orders.add(Order
                .builder()
                .date(new Date())
                .orderId(1L)
                .products(products)
                .total(new BigDecimal("150.0"))
                .build());
        
        return orders;
    }

    public static List<Order> buildSingleProductOrder2() {
        var products = new ArrayList<Product>();
        products.add(Product
                .builder()
                .productId(2L)
                .value(new BigDecimal("10.0"))
                .build());

        var orders = new ArrayList<Order>();
        orders.add(Order
                .builder()
                .date(new Date())
                .orderId(2L)
                .products(products)
                .total(new BigDecimal("10.0"))
                .build());
        
        return orders;
    }

    public static ArrayList<Order> buildAnotherSingleProductOrder2() {
        var products = new ArrayList<Product>();
        products.add(Product
                .builder()
                .productId(3L)
                .value(new BigDecimal("750.0"))
                .build());

        var orders = new ArrayList<Order>();
        orders.add(Order
                .builder()
                .date(new Date())
                .orderId(2L)
                .products(products)
                .total(new BigDecimal("750.0"))
                .build());
        
        return orders;
    }

    public static List<Order> buildSingleProductOrder3() {
        var products = new ArrayList<Product>();
        products.add(Product
                .builder()
                .productId(4L)
                .value(new BigDecimal("15.0"))
                .build());

        var orders = new ArrayList<Order>();
        orders.add(Order
                .builder()
                .date(new Date())
                .orderId(3L)
                .products(products)
                .total(new BigDecimal("15.0"))
                .build());

        return orders;
    }

    public static ArrayList<Order> buildAnotherSingleProductOrder3() {
        var products = new ArrayList<Product>();
        products.add(Product
                .builder()
                .productId(5L)
                .value(new BigDecimal("420.0"))
                .build());

        var orders = new ArrayList<Order>();
        orders.add(Order
                .builder()
                .date(new Date())
                .orderId(3L)
                .products(products)
                .total(new BigDecimal("420.0"))
                .build());

        return orders;
    }
}
