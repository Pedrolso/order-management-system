package repository;

import entities.Order;
import entities.enums.OrderStatus;

import java.util.List;

public interface OrderRepository {

    Order addOrder(Order order);

    Order updateOrder(String code, String newCode);

    Order findByCode(String code);

    List<Order> findByPrefix(String prefix);

    Order deleteByFullCode(String code);


    Order updateStatus(String code, OrderStatus status);

    List<Order> findAll();

}
