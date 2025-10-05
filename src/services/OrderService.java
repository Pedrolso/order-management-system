package services;

import entities.Order;
import entities.enums.OrderStatus;
import exceptions.DomainException;
import repository.OrderRepository;

import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //----------------------------------------------------------------------//

    public Order createOrder(Order order) {
        if (orderRepository.findByCode(order.getCode()) != null) {
            throw new DomainException("An order with this code already exists.");
        }
        return orderRepository.addOrder(order);
    }

    public Order updateOrder(String code, String newCode) {
        String normalCode = Order.fixingFormat(code);
        String formattedCode = Order.fixingFormat(newCode);

        if (orderRepository.findByCode(formattedCode) != null) {
            throw new DomainException("An order with this code already exists.");
        }

        if (orderRepository.findByCode(normalCode) == null) {
            throw new DomainException("Order not found.");
        }

        return orderRepository.updateOrder(normalCode, formattedCode);
    }

    public Order findByCode(String code) {
        return orderRepository.findByCode(code);
    }

    public List<Order> findByPrefix(String code) {
        List<Order> result = orderRepository.findByPrefix(code);
        if (result.isEmpty()) {
            throw new DomainException("No orders found with that prefix.");
        }
        return result;
    }

    public Order deleteOrder(String code) {
        Order existing = orderRepository.findByCode(code);
        if (existing == null) {
            throw new DomainException("Code not found. " + code);
        }
        return orderRepository.deleteByFullCode(code);
    }

    //--------------------------------------------------------------------------//

    public Order updateStatus(String code, int opc) {
        Order order = orderRepository.findByCode(code);
        if (order == null) {
            throw new DomainException("Order not found.");
        }

        OrderStatus novoStatus = OrderStatus.fromOption(opc);
        return orderRepository.updateStatus(code, novoStatus);
    }

    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    //-------------------------------------------------------------------------//
    //Shows the list in the console
    public String listFormattedOrders(List<Order> orders, boolean withHistory) {
        if (orders.isEmpty()) {
            return "No orders found.";
        }

        StringBuilder sb = new StringBuilder();
        for (Order o : orders) {
            if (withHistory) {
                sb.append(o.toString());
            } else {
                sb.append(o.listCase3());
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
