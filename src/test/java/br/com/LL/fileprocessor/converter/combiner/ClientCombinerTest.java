package br.com.LL.fileprocessor.converter.combiner;

import br.com.LL.fileprocessor.utils.TestUtils;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientCombinerTest {
    
    ClientCombiner combiner = new ClientCombiner();
    
    @Test
    public void givenCollectionOfClients_whenSameClientIsRepeated_thenShouldCombineOrdersOfSameId_andReturnOnlyOneClient() {
        var alice1 = TestUtils.buildClientAlice();
        alice1.setOrders(TestUtils.buildSingleProductOrder1());
        var alice2 = TestUtils.buildClientAlice();
        alice2.setOrders(TestUtils.buildSingleProductOrder2());
        var alice3 = TestUtils.buildClientAlice();
        alice3.setOrders(TestUtils.buildAnotherSingleProductOrder2());

        var clients = combiner.joinClientsById(List.of(alice1, alice2, alice3)).stream().toList();
        
        assertEquals(1, clients.size());
        
        var client = clients.get(0);
        assertEquals(2, client.getOrders().size());
        
        var orders = client.getOrders().stream().filter(o -> o.getOrderId() == 1L).toList();
        assertEquals(1, orders.size());
        
        var order1 = orders.get(0);
        assertEquals(1, order1.getProducts().size());
        assertEquals(new BigDecimal("150.0"), order1.getTotal());

        orders = client.getOrders().stream().filter(o -> o.getOrderId() == 2L).toList();
        assertEquals(1, orders.size());
        
        var order2 = orders.get(0);
        assertEquals(2, order2.getProducts().size());
        assertEquals(new BigDecimal("760.0"), order2.getTotal());
    }

    @Test
    public void givenCollectionOfClients_whenMultipleClientsAreRepeated_thenShouldCombineClientsAndOrdersWithSameId() {
        var alice1 = TestUtils.buildClientAlice();
        alice1.setOrders(TestUtils.buildSingleProductOrder2());
        var alice2 = TestUtils.buildClientAlice();
        alice2.setOrders(TestUtils.buildAnotherSingleProductOrder2());
        var bob1 = TestUtils.buildClientBob();
        bob1.setOrders(TestUtils.buildSingleProductOrder3());
        var bob2 = TestUtils.buildClientBob();
        bob2.setOrders(TestUtils.buildAnotherSingleProductOrder3());
        var cecilia1 = TestUtils.buildClientCecilia();
        cecilia1.setOrders(TestUtils.buildSingleProductOrder1());
        
        var clients = combiner.joinClientsById(List.of(alice1, alice2, bob1, bob2, cecilia1)).stream().toList();
        assertEquals(3, clients.size());

        var clientFilter = clients.stream().filter(c -> c.getUserId() == 1L).toList();
        assertEquals(1, clientFilter.size());
        var alice = clientFilter.get(0);
        var aliceOrders = alice.getOrders();
        assertEquals(1, aliceOrders.size());
        var aliceOrder = aliceOrders.get(0);
        assertEquals(new BigDecimal("760.0"), aliceOrder.getTotal());
        assertEquals(2, aliceOrder.getProducts().size());

        clientFilter = clients.stream().filter(c -> c.getUserId() == 2L).toList();
        assertEquals(1, clientFilter.size());
        var bob = clientFilter.get(0);
        var bobOrders = bob.getOrders();
        assertEquals(1, bobOrders.size());
        var bobOrder = bobOrders.get(0);
        assertEquals(new BigDecimal("435.0"), bobOrder.getTotal());
        assertEquals(2, bobOrder.getProducts().size());

        clientFilter = clients.stream().filter(c -> c.getUserId() == 3L).toList();
        assertEquals(1, clientFilter.size());
        var cecilia = clientFilter.get(0);
        var ceciliaOrders = cecilia.getOrders();
        assertEquals(1, ceciliaOrders.size());
        var ceciliaOrder = ceciliaOrders.get(0);
        assertEquals(new BigDecimal("150.0"), ceciliaOrder.getTotal());
        assertEquals(1, ceciliaOrder.getProducts().size());
    }
}
