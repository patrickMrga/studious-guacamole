package br.com.LL.fileprocessor.converter.reader;

import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.model.Order;
import br.com.LL.fileprocessor.model.Product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class ClientMapper implements LineToObjectMapper<Client> {
    final static Map<Integer, Field> fields = Map.of(
            1, new Field("userId", 0, 10),
            2, new Field("name", 10, 55),
            3, new Field("orderId", 55, 65),
            4, new Field("productId", 65, 75),
            5, new Field("value", 75, 87),
            6, new Field("date", 87, 95));

    @Override
    public Map<Integer, Field> getFields() {
        return fields;
    }

    @Override
    public Client convert(String line) throws ParseException, NumberFormatException {
        var product = new Product(
                readNumber(line, fields.get(4)),
                readDecimal(line, fields.get(5))
        );

        var products = new ArrayList<Product>();
        products.add(product);
        
        var order = new Order(
                readNumber(line, fields.get(3)),
                readDecimal(line, fields.get(5)),
                readDate(line, fields.get(6)),
                products);
        
        var orders = new ArrayList<Order>();
        orders.add(order);
        
        return new Client(
                readNumber(line, fields.get(1)),
                readString(line, fields.get(2)),
                orders
        );
    }
}
