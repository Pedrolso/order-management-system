package repository;

import entities.Order;
import entities.enums.OrderStatus;

import java.util.*;

public class OrderRepositoryImpl implements OrderRepository {

    private Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public Order addOrder(Order order) {
        orderMap.put(order.getId(), order);
        return order;
    }

    @Override
    public Order updateOrder(String code, String newCode) {
        for (Order order : orderMap.values()) {
            if (order.getCode().equals(code)) {
                order.setCode(newCode);
                return order;
            }
        }
        return null;
    }

    @Override
    public Order findByCode(String code) {
        for (Order order : orderMap.values()) {
            if (order.getCode().equals(code)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findByPrefix(String code) {
        String normalized = Order.fixingFormat(code);
        List<Order> result = new ArrayList<>();

        for (Order order : orderMap.values()) {
            if (order.getCode().startsWith(normalized)) {
                result.add(order);
            }
        }

        return result;
    }

    @Override
    public Order deleteByFullCode(String code) {
        Iterator<Order> iterator = orderMap.values().iterator();
        String normalized = Order.fixingFormat(code);

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getCode().equals(normalized)) {
                iterator.remove();
                return order;
            }
        }
        return null;
    }

    //-------------------------------------------------------------------------------//

    @Override //!!!//
    public Order updateStatus(String code, OrderStatus status) {
        for (Order order : orderMap.values()) {
            if (order.getCode().equals(code)) {
                order.setStatus(status);
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orderMap.values());
    }

}
