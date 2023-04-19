package br.com.LL.fileprocessor.utils;

import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.model.Order;
import br.com.LL.fileprocessor.model.Product;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestUtils {

    private final static Date date;

    static {
        try {
            date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse("16-04-2023");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Client buildClientAlice() {
        return new Client(1L, "Alice Delonghi", new ArrayList<>());
    }

    public static Client buildClientBob() {
        return new Client(2L, "Robert Johnson III", new ArrayList<>());
    }

    public static Client buildClientCecilia() {
        return new Client(3L, "Cecilia Jolene Pininfarina", new ArrayList<>());
    }
    
    public static List<Order> buildSingleProductOrder1() {
        var products = new ArrayList<Product>();
        products.add(new Product(1L,new BigDecimal("150.0"))); 
                        
        var orders = new ArrayList<Order>();
        orders.add(new Order(1L, new BigDecimal("150.0"), date, products));
                       
        return orders;
    }

    public static List<Order> buildSingleProductOrder2() {
        var products = new ArrayList<Product>();
        products.add(new Product(2L, new BigDecimal("10.0")));
                
        var orders = new ArrayList<Order>();
        orders.add(new Order(2L, new BigDecimal("10.0"), date, products));
                
        return orders;
    }

    public static ArrayList<Order> buildAnotherSingleProductOrder2() {
        var products = new ArrayList<Product>();
        products.add(new Product(3L, new BigDecimal("750.0")));

        var orders = new ArrayList<Order>();
        orders.add(new Order(2L, new BigDecimal("750.0"), date, products));
        
        return orders;
    }

    public static List<Order> buildSingleProductOrder3() {
        var products = new ArrayList<Product>();
        products.add(new Product(4L, new BigDecimal("15.0")));

        var orders = new ArrayList<Order>();
        orders.add(new Order(3L, new BigDecimal("15.0"), date, products));
        
        return orders;
    }

    public static ArrayList<Order> buildAnotherSingleProductOrder3() {
        var products = new ArrayList<Product>();
        products.add(new Product(5L, new BigDecimal("420.0")));

        var orders = new ArrayList<Order>();
        orders.add(new Order(3L, new BigDecimal("420.0"), date, products));
        
        return orders;
    }

    public static String getEmptyJsonOutput() {
        return "[ ]";
    }
    
    public static String getClientsJsonOutput() {
        return """
                [ {
                  "user_id" : 3,
                  "name" : "Cecilia Jolene Pininfarina",
                  "orders" : [ {
                    "order_id" : 1,
                    "total" : 150.0,
                    "date" : "2023-04-16",
                    "products" : [ {
                      "product_id" : 1,
                      "value" : 150.0
                    } ]
                  } ]
                } ]""";
    }
}
