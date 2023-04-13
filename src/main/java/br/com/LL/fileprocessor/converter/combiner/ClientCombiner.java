package br.com.LL.fileprocessor.converter.combiner;

import br.com.LL.fileprocessor.model.Client;
import br.com.LL.fileprocessor.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ClientCombiner {
    
    public Collection<Client> joinClientsById(Collection<Client> clients) {
        log.info("Combining " + clients.size() + " clients");

        Collection<Client> combinedClients = clients.stream().collect(Collectors.toMap(Client::getUserId, Function.identity(), (left, right) -> {
            var orders = Stream.concat(left.getOrders().stream(), right.getOrders().stream()).toList();

            left.setOrders(orders.stream().collect(Collectors.toMap(Order::getOrderId, Function.identity(), (leftOrder, rightOrder) -> {
                leftOrder.getProducts().addAll(rightOrder.getProducts());
                leftOrder.setTotal(leftOrder.getTotal().add(rightOrder.getTotal()));

                return leftOrder;
            })).values().stream().toList());

            return left;
        })).values();

        log.info("Finished combining into " + combinedClients.size() + " clients");
        
        return combinedClients;
    }
}
