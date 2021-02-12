package net.sublime.warehouse.service.order;

import net.sublime.warehouse.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    List<Order> getAll(int number , int size);

    Order getById(long id);

    void deleteById(long id);

    void save(Order order);
}
