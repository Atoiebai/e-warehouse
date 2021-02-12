package net.sublime.warehouse.service.order;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.Order;
import net.sublime.warehouse.reposirtory.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAll(int number, int size) {
        Pageable pageable = PageRequest.of(number, size);
        Page<Order> productsResult = orderRepository.findAll(pageable);
        return productsResult.toList();
    }

    @Override
    public Order getById(long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void deleteById(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void save(Order order) {
        order.setTotalPrice(order
                .getProductBundle()
                .stream()
                .mapToDouble(x -> x.getPrice() * x.getCount())
                .sum());
        orderRepository.save(order);
    }
}
